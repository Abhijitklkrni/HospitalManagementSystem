package org.test.hospitalmanagementsystem.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.test.hospitalmanagementsystem.entity.DoctorSchedule;
import org.test.hospitalmanagementsystem.entity.Slot;
import org.test.hospitalmanagementsystem.model.DocScheduleStatus;
import org.test.hospitalmanagementsystem.model.DoctorScheduleResponse;
import org.test.hospitalmanagementsystem.model.SlotStatus;
import org.test.hospitalmanagementsystem.repository.DocScheduleRepository;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class DocScheduleService {

    public static final int SLOT_DURATION_MIN = 30;
    SlotService slotService;

    AppointmentService appointmentHistoryService;

    DocScheduleRepository repository;

    public DocScheduleService(SlotService slotService, AppointmentService appointmentHistoryService, DocScheduleRepository repository) {
        this.slotService = slotService;
        this.appointmentHistoryService = appointmentHistoryService;
        this.repository = repository;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public String addDocSchedule(DoctorSchedule doctorSchedule) {
        DoctorSchedule saved = repository.save(doctorSchedule);
        if (saved.getStatus() != DocScheduleStatus.AVAILABLE)
            return "Doctor Schedule saved successfully, Slots not generated as doctor is not available on this day";
        generateAndSaveSlots(saved);
        return "Doctor Schedule and Slots for the date added successfully";
    }


    private void generateAndSaveSlots(DoctorSchedule doctorSchedule) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime startTime = LocalTime.parse(doctorSchedule.getStartTime(), formatter);
        LocalTime endTime = LocalTime.parse(doctorSchedule.getEndTime(), formatter);
        List<Slot> slots = new ArrayList<>();

        for (LocalTime time = startTime; time.isBefore(endTime); time = time.plusMinutes(SLOT_DURATION_MIN)) {
            Slot slot = new Slot();
            slot.setSchedule(DoctorSchedule.builder().scheduleId(doctorSchedule.getScheduleId()).build());
            slot.setDoctor(doctorSchedule.getDoctor());
            slot.setDate(doctorSchedule.getDate());
            slot.setStartTime(time.format(formatter));
            slot.setEndTime(time.plusMinutes(SLOT_DURATION_MIN).format(formatter));
            slot.setStatus(SlotStatus.AVAILABLE);
            slots.add(slot);
        }

        slots.forEach(slotService::addSlot);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public DoctorScheduleResponse cancelDocSchedule(DoctorSchedule doctorSchedule) {
        if(doctorSchedule.getStatus()!=DocScheduleStatus.CANCELLED) throw new RuntimeException("Request action should be CANCELLED");
        DoctorSchedule saved = repository.getById(doctorSchedule.getScheduleId());
        saved.setStatus(DocScheduleStatus.CANCELLED);
        repository.save(saved);

        List<Slot> slots = slotService.cancelSlotsForSchedule(saved.getScheduleId());

        //Find the AppointmentHistory for the slots and cancel them
        appointmentHistoryService.cancelAppointmentsForSlots(slots,doctorSchedule);
        return saved.toDocScheduleResponse();

    }

}
