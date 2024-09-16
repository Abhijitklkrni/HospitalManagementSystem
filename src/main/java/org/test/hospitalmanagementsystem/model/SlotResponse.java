package org.test.hospitalmanagementsystem.model;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class SlotResponse {

    private long slotId;
    private String startTime;
    private String endTime;
    private String date;

    @Enumerated(EnumType.STRING)
    private SlotStatus status;

    private Long doctorId;


}
