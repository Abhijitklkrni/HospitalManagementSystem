package org.test.hospitalmanagementsystem.entity;

import jakarta.persistence.*;
import lombok.*;

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
    @ManyToOne
    @JoinColumn(name="hospitalId")
    private Hospital hospital;

    //Referenced by foreign key constraint from Patient on field patientId
    @ManyToOne
    @JoinColumn(name="patientId")
    private Patient patient;

    //Referenced by foreign key constraint from Doctor on field doctorId
    @ManyToOne
    @JoinColumn(name="doctorId")
    private Doctor doctor;

    private String appointmentDate;

    private String appointmentStatus;

    private String startTime;

    private String endTime;

  }