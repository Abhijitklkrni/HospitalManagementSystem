package org.test.hospitalmanagementsystem.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentHistory {

    @Id
    @GeneratedValue
    private Long appointmentId;
    private Long hospitalId;

    private Long patientId;

    private Long doctorId;

    private String appointmentDate;

    private String appointmentStatus;

    private String startTime;

    private String endTime;

  }