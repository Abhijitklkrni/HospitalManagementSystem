package org.test.hospitalmanagementsystem.service;

import org.springframework.stereotype.Service;
import org.test.hospitalmanagementsystem.entity.Doctor;
import org.test.hospitalmanagementsystem.entity.Patient;
import org.test.hospitalmanagementsystem.entity.Slot;
import org.test.hospitalmanagementsystem.exception.SlotUnavailableException;
import org.test.hospitalmanagementsystem.model.AppointmentRequest;
import org.test.hospitalmanagementsystem.model.AppointmentResponse;
import org.test.hospitalmanagementsystem.model.SLOT_STATUS;
import org.test.hospitalmanagementsystem.repository.SlotRepository;

import java.util.List;
import java.util.Optional;

@Service
public class SlotService {

    SlotRepository repository;

    PatientService patientService;

    DoctorService doctorService;

    public SlotService(SlotRepository repository, PatientService patientService, DoctorService doctorService) {
        this.repository = repository;
        this.patientService = patientService;
        this.doctorService = doctorService;
    }

    public void addSlot(Slot slot) {
        repository.save(slot);
    }

    public List<Slot> getAllSlotsByDateAndDoctorId(String date, Long doctorId) {
        return repository.findAllByDateAndDoctorId(date, doctorId);
    }


    public Slot bookSlot(Long slotId, Long patientId) {
        Optional<Slot> slot = repository.findById(slotId);
        if(slot.get().getStatus() == SLOT_STATUS.BOOKED){
            throw new SlotUnavailableException("Slot not available");
        }
        slot.ifPresentOrElse(slot1 -> {
                    slot1.setPatientId(patientId);
                    slot1.setStatus(SLOT_STATUS.BOOKED);
                    repository.save(slot1);
                },
                () -> {
                    throw new SlotUnavailableException("Slot not available");
                });
        return slot.get();
    }

    public void cancelSlotsForSchedule(long scheduleId) {
        List<Slot> slots = repository.findAllByScheduleId(scheduleId);
        slots.forEach(slot -> {
            slot.setStatus(SLOT_STATUS.CANCELLED);
            slot.setPatientId(-1);
            repository.save(slot);
        });
    }
}
