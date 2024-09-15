package org.test.hospitalmanagementsystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.test.hospitalmanagementsystem.model.DocScheduleStatus;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class DoctorSchedule {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private long scheduleId;

    //Referenced by foreign key constraint from Doctor on field doctorId
    private long doctorId;
    private String date;
    private String startTime;
    private String endTime;

    @Enumerated(EnumType.STRING)
    private DocScheduleStatus status;
}
