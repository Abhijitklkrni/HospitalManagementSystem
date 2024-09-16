package org.test.hospitalmanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.test.hospitalmanagementsystem.entity.Slot;

import java.util.List;
import java.util.Optional;

@Repository
public interface SlotRepository extends JpaRepository<Slot, Long> {

    Optional<Slot> findBySlotIdAndDoctor_DoctorId(Long slotId, Long doctorId);

    List<Slot> findAllByDateAndDoctor_DoctorId(String date, Long doctorId);

    List<Slot> findAllBySchedule_ScheduleId(long scheduleId);

    Optional<Slot> findByDoctor_DoctorIdAndDateAndStartTime(Long doctorId, String appointmentDate, String startTime);

}
