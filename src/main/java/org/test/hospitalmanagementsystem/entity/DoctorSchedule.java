package org.test.hospitalmanagementsystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.test.hospitalmanagementsystem.model.DocScheduleStatus;
import org.test.hospitalmanagementsystem.model.DoctorScheduleResponse;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class DoctorSchedule extends BaseModel{

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

    public DoctorScheduleResponse toDocScheduleResponse() {
        return DoctorScheduleResponse.builder()
                .scheduleId(scheduleId)
                .doctorId(doctorId)
                .date(date)
                .startTime(startTime)
                .endTime(endTime)
                .status(status)
                .build();
    }
}
