package org.test.hospitalmanagementsystem.service;

import org.springframework.stereotype.Service;
import org.test.hospitalmanagementsystem.entity.Patient;
import org.test.hospitalmanagementsystem.entity.Slot;
import org.test.hospitalmanagementsystem.exception.SlotUnavailableException;
import org.test.hospitalmanagementsystem.model.SlotStatus;
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
        return repository.findAllByDateAndDoctor_DoctorId(date, doctorId);
    }


    public Slot bookSlot(Long slotId, Long patientId) {
        Optional<Slot> slot = repository.findById(slotId);
        if(slot.get().getStatus() == SlotStatus.BOOKED){
            throw new SlotUnavailableException("Slot not available");
        }
        slot.ifPresentOrElse(slot1 -> {
                    slot1.setPatient(Patient.builder().patientId(patientId).build());
                    slot1.setStatus(SlotStatus.BOOKED);
                    repository.save(slot1);
                },
                () -> {
                    throw new SlotUnavailableException("Slot not available");
                });
        return slot.get();
    }

    public List<Slot> cancelSlotsForSchedule(long scheduleId) {
        List<Slot> slots = repository.findAllBySchedule_ScheduleId(scheduleId);
        slots.forEach(slot -> {
            slot.setStatus(SlotStatus.CANCELLED);
            slot.setPatient(null);
            repository.save(slot);
        });

        return slots;
    }

    public void freeTheSlot(Long doctorId, String appointmentDate, String startTime) {

        Optional<Slot> slot = repository.findByDoctor_DoctorIdAndDateAndStartTime(doctorId, appointmentDate, startTime);
        slot.ifPresentOrElse(slot1 -> {
                    slot1.setStatus(SlotStatus.AVAILABLE);
                    slot1.setPatient(null);
                    repository.save(slot1);
                },
                () -> {
                    throw new SlotUnavailableException("Slot not available");
                });
    }
}
