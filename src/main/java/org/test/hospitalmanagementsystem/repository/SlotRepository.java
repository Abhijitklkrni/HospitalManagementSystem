package org.test.hospitalmanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.test.hospitalmanagementsystem.entity.Slot;

import java.util.List;
import java.util.Optional;

@Repository
public interface SlotRepository extends JpaRepository<Slot, Long> {

    Optional<Slot> findBySlotIdAndDoctorId(Long slotId, Long doctorId);

    List<Slot> findAllByDateAndDoctorId(String date, Long doctorId);
}
