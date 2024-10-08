package org.test.hospitalmanagementsystem.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.test.hospitalmanagementsystem.entity.*;
import org.test.hospitalmanagementsystem.model.AppointmentRequest;
import org.test.hospitalmanagementsystem.model.AppointmentResponse;
import org.test.hospitalmanagementsystem.model.SlotResponse;
import org.test.hospitalmanagementsystem.repository.AppointmentHistoryRepository;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class AppointmentService {

    SlotService slotService;

    AppointmentHistoryRepository appointmentHistoryRepository;

    public AppointmentService(SlotService slotService, AppointmentHistoryRepository appointmentHistoryRepository) {
        this.slotService = slotService;
        this.appointmentHistoryRepository = appointmentHistoryRepository;
    }

    public List<SlotResponse> getAllSlotsByDateAndDoctorId(String date, Long doctorId) {
         return slotService.getAllSlotsByDateAndDoctorId(date, doctorId).stream().map(slot -> SlotResponse.builder()
                .slotId(slot.getSlotId())
                .date(slot.getDate())
                .startTime(slot.getStartTime())
                .endTime(slot.getEndTime())
                .status(slot.getStatus())
                .doctorId(slot.getDoctor().getDoctorId())
                .build()).toList();
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW,isolation = Isolation.REPEATABLE_READ)
    public AppointmentResponse bookSlot(AppointmentRequest appointmentRequest) {

        try{
            Slot slot = slotService.bookSlot(appointmentRequest.slotId(), appointmentRequest.patientId());
            AppointmentHistory appointment = AppointmentHistory.builder()
                    .hospital(Hospital.builder().hospitalId(appointmentRequest.hospitalId()).build())
                    .patient(Patient.builder().patientId(appointmentRequest.patientId()).build())
                    .doctor(Doctor.builder().doctorId(appointmentRequest.doctorId()).build())
                    .appointmentStatus("BOOKED")
                    .appointmentDate(slot.getDate())
                    .startTime(slot.getStartTime())
                    .endTime(slot.getEndTime())
                    .build();

            AppointmentHistory saved = appointmentHistoryRepository.save(appointment);
            return AppointmentResponse.builder()
                    .appointmentId(saved.getAppointmentId())
                    .appointmentDate(slot.getDate())
                    .appointmentTime(slot.getStartTime())
                    .patientDetails(appointmentRequest.patientId())
                    .doctorDetails(appointmentRequest.doctorId())
                    .appointmentStatus(saved.getAppointmentStatus())
                    .build();
        }catch (Exception e){
            throw new RuntimeException("Slot not available");
        }

    }

    public List<AppointmentResponse> getAppointmentsByPatient(Long patientId) {
        List<AppointmentHistory> appointments = appointmentHistoryRepository.getAppointmentsByPatient_PatientId(patientId);

        return appointments.stream().map(appointment -> AppointmentResponse.builder()
                .appointmentId(appointment.getAppointmentId())
                .appointmentDate(appointment.getAppointmentDate())
                .appointmentTime(appointment.getStartTime())
                .patientDetails(appointment.getPatient().getPatientId())
                .doctorDetails(appointment.getDoctor().getDoctorId())
                .appointmentStatus(appointment.getAppointmentStatus())
                .build()).toList();
    }


    public void cancelAppointmentsForSlots(List<Slot> slots, DoctorSchedule doctorSchedule) {
        slots.forEach(slot -> {
            String date = slot.getDate();
            String startTime = slot.getStartTime();
            Doctor doctorId = slot.getDoctor();
            //Find the appointment for the slot and cancel it
            List<AppointmentHistory> appointment = appointmentHistoryRepository.findByAppointmentDateAndStartTimeAndDoctor_DoctorId(date, startTime, doctorId.getDoctorId());
            appointment.forEach(appointmentHistory -> {
                appointmentHistory.setAppointmentStatus("CANCELLED");
                appointmentHistoryRepository.save(appointmentHistory);
            });
        });
        log.info("Appointments cancelled for the slots");
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public AppointmentResponse cancelAppointment(Long appointmentId) {
        Optional<AppointmentHistory> appointment = appointmentHistoryRepository.findById(appointmentId);
        if(appointment.isEmpty()) throw new RuntimeException("Appointment not found");
        appointment.get().setAppointmentStatus("CANCELLED");
        appointmentHistoryRepository.save(appointment.get());

        //Free the slot
        slotService.freeTheSlot(appointment.get().getDoctor().getDoctorId(),appointment.get().getAppointmentDate(),appointment.get().getStartTime());


        return transformToAppointmentResponse(appointment.get());
    }


    public AppointmentResponse transformToAppointmentResponse(AppointmentHistory appointmentHistory) {
        return AppointmentResponse.builder()
                .appointmentId(appointmentHistory.getAppointmentId())
                .appointmentDate(appointmentHistory.getAppointmentDate())
                .appointmentTime(appointmentHistory.getStartTime())
                .patientDetails(appointmentHistory.getPatient().getPatientId())
                .doctorDetails(appointmentHistory.getDoctor().getDoctorId())
                .appointmentStatus(appointmentHistory.getAppointmentStatus())
                .build();
    }
}
