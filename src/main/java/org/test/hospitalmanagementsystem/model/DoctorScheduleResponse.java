package org.test.hospitalmanagementsystem.model;

import lombok.Builder;
import lombok.Data;
import org.test.hospitalmanagementsystem.entity.Doctor;

@Builder
@Data
public class DoctorScheduleResponse {

    private long scheduleId;

    private Long doctorId;
    private String date;
    private String startTime;
    private String endTime;
    private DocScheduleStatus status;

}
