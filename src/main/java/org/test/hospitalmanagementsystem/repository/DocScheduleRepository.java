package org.test.hospitalmanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.test.hospitalmanagementsystem.entity.DoctorSchedule;

@Repository
public interface DocScheduleRepository extends JpaRepository<DoctorSchedule, Long> {
}
