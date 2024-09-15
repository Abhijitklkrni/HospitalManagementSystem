package org.test.hospitalmanagementsystem.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentHistory extends BaseModel{

    @Id
    @GeneratedValue
    private Long appointmentId;

    //Referenced by foreign key constraint from Hospital on field hospitalId
    private Long hospitalId;

    //Referenced by foreign key constraint from Patient on field patientId
    private Long patientId;

    //Referenced by foreign key constraint from Doctor on field doctorId
    private Long doctorId;

    private String appointmentDate;

    private String appointmentStatus;

    private String startTime;

    private String endTime;

  }