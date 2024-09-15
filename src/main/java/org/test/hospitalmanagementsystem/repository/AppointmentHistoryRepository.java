package org.test.hospitalmanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.test.hospitalmanagementsystem.entity.AppointmentHistory;
import org.test.hospitalmanagementsystem.model.AppointmentResponse;

import java.util.List;

public interface AppointmentHistoryRepository extends JpaRepository<AppointmentHistory, Long> {

    List<AppointmentHistory> getAppointmentsByPatientId(Long patientId);
}
