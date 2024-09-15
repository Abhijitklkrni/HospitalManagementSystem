package org.test.hospitalmanagementsystem.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.test.hospitalmanagementsystem.entity.DoctorSchedule;
import org.test.hospitalmanagementsystem.entity.Slot;
import org.test.hospitalmanagementsystem.model.DocScheduleStatus;
import org.test.hospitalmanagementsystem.model.SLOT_STATUS;
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

    DocScheduleRepository repository;

    public DocScheduleService(DocScheduleRepository repository, SlotService slotService) {
        this.repository = repository;
        this.slotService = slotService;
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
            slot.setScheduleId(doctorSchedule.getScheduleId());
            slot.setDoctorId(doctorSchedule.getDoctorId());
            slot.setDate(doctorSchedule.getDate());
            slot.setStartTime(time.format(formatter));
            slot.setEndTime(time.plusMinutes(SLOT_DURATION_MIN).format(formatter));
            slot.setStatus(SLOT_STATUS.AVAILABLE);
            slots.add(slot);
        }

        slots.forEach(slotService::addSlot);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public DoctorSchedule cancelDocSchedule(DoctorSchedule doctorSchedule) {
        if(doctorSchedule.getStatus()!=DocScheduleStatus.CANCELLED) throw new RuntimeException("Request action as CANCELLED");
        DoctorSchedule saved = repository.getById(doctorSchedule.getScheduleId());
        saved.setStatus(DocScheduleStatus.CANCELLED);
        repository.save(saved);

        slotService.cancelSlotsForSchedule(saved.getScheduleId());
        return saved;
    }

}
