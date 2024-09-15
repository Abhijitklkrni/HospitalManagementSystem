package org.test.hospitalmanagementsystem.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.test.hospitalmanagementsystem.entity.AppointmentHistory;
import org.test.hospitalmanagementsystem.entity.DoctorSchedule;
import org.test.hospitalmanagementsystem.entity.Slot;
import org.test.hospitalmanagementsystem.model.AppointmentRequest;
import org.test.hospitalmanagementsystem.model.AppointmentResponse;
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

    public List<Slot> getAllSlotsByDateAndDoctorId(String date, Long doctorId) {
        return slotService.getAllSlotsByDateAndDoctorId(date, doctorId);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW,isolation = Isolation.REPEATABLE_READ)
    public AppointmentResponse bookSlot(AppointmentRequest appointmentRequest) {

        try{
            Slot slot = slotService.bookSlot(appointmentRequest.slotId(), appointmentRequest.patientId());
            AppointmentHistory appointment = AppointmentHistory.builder()
                    .hospitalId(appointmentRequest.hospitalId())
                    .patientId(appointmentRequest.patientId())
                    .doctorId(appointmentRequest.doctorId())
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
        List<AppointmentHistory> appointments = appointmentHistoryRepository.getAppointmentsByPatientId(patientId);

        return appointments.stream().map(appointment -> AppointmentResponse.builder()
                .appointmentId(appointment.getAppointmentId())
                .appointmentDate(appointment.getAppointmentDate())
                .appointmentTime(appointment.getStartTime())
                .patientDetails(appointment.getPatientId())
                .doctorDetails(appointment.getDoctorId())
                .appointmentStatus(appointment.getAppointmentStatus())
                .build()).toList();
    }


    public void cancelAppointmentsForSlots(List<Slot> slots, DoctorSchedule doctorSchedule) {
        slots.forEach(slot -> {
            String date = slot.getDate();
            String startTime = slot.getStartTime();
            Long doctorId = slot.getDoctorId();
            //Find the appointment for the slot and cancel it
            Optional<AppointmentHistory> appointment = appointmentHistoryRepository.findByAppointmentDateAndStartTimeAndDoctorId(date, startTime, doctorId);
            appointment.ifPresent(appointmentHistory -> {
                appointmentHistory.setAppointmentStatus("CANCELLED");
                appointmentHistoryRepository.save(appointmentHistory);
            });
        });
        log.info("Appointments cancelled for the slots");
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public AppointmentHistory cancelAppointment(Long appointmentId) {
        Optional<AppointmentHistory> appointment = appointmentHistoryRepository.findById(appointmentId);
        if(appointment.isEmpty()) throw new RuntimeException("Appointment not found");
        appointment.get().setAppointmentStatus("CANCELLED");
        appointmentHistoryRepository.save(appointment.get());

        //Free the slot
        slotService.freeTheSlot(appointment.get().getDoctorId(),appointment.get().getAppointmentDate(),appointment.get().getStartTime());
        return appointment.get();
    }
}
