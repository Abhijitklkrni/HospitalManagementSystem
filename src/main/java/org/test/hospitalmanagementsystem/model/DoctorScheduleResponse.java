package org.test.hospitalmanagementsystem.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class DoctorScheduleResponse {

    private long scheduleId;

    private long doctorId;
    private String date;
    private String startTime;
    private String endTime;
    private DocScheduleStatus status;

}
