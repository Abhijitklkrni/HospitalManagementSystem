package org.test.hospitalmanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.test.hospitalmanagementsystem.entity.AppointmentHistory;

public interface AppointmentHistoryRepository extends JpaRepository<AppointmentHistory, Long> {

}
