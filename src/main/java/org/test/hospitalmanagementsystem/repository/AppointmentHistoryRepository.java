package org.test.hospitalmanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.test.hospitalmanagementsystem.entity.AppointmentHistory;

import java.util.List;

public interface AppointmentHistoryRepository extends JpaRepository<AppointmentHistory, Long> {

    List<AppointmentHistory> getAppointmentsByPatient_PatientId(Long patientId);

    List<AppointmentHistory> findByAppointmentDateAndStartTimeAndDoctor_DoctorId(String date, String startTime, Long doctorId);
}
