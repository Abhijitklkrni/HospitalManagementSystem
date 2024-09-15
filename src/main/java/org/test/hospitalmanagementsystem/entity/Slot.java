package org.test.hospitalmanagementsystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.test.hospitalmanagementsystem.model.SLOT_STATUS;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Slot {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private long slotId;
    private String startTime;
    private String endTime;
    private String date;

    @Enumerated(EnumType.STRING)
    private SLOT_STATUS status;

    //Referenced by foreign key constraint from Doctor on field doctorId
    private long doctorId;

    //Referenced by foreign key constraint from Patient on field patientId
    private long patientId;

    //Referenced by foreign key constraint from DoctorSchedule on field scheduleId
    private long scheduleId;

}
