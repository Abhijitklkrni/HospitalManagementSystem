package org.test.hospitalmanagementsystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.test.hospitalmanagementsystem.model.SlotStatus;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Slot extends BaseModel{

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private long slotId;
    private String startTime;
    private String endTime;
    private String date;

    @Enumerated(EnumType.STRING)
    private SlotStatus status;

    //Referenced by foreign key constraint from Doctor on field doctorId
    @ManyToOne
    @JoinColumn(name = "doctorId")
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name = "patientId")
    //Referenced by foreign key constraint from Patient on field patientId
    private Patient patient;

    //Referenced by foreign key constraint from DoctorSchedule on field scheduleId
    @ManyToOne
    @JoinColumn(name = "scheduleId")
    private DoctorSchedule schedule;

}
