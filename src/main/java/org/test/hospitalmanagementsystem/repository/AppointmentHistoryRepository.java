package org.test.hospitalmanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.test.hospitalmanagementsystem.entity.AppointmentHistory;
import org.test.hospitalmanagementsystem.model.AppointmentResponse;

import java.util.List;
import java.util.Optional;

public interface AppointmentHistoryRepository extends JpaRepository<AppointmentHistory, Long> {

    List<AppointmentHistory> getAppointmentsByPatientId(Long patientId);

    Optional<AppointmentHistory> findByAppointmentDateAndStartTimeAndDoctorId(String date, String startTime, Long doctorId);
}
