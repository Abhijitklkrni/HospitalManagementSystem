package org.test.hospitalmanagementsystem.model;

import lombok.Builder;
import lombok.Data;
import org.test.hospitalmanagementsystem.entity.Doctor;
import org.test.hospitalmanagementsystem.entity.Patient;

@Data
@Builder
public class AppointmentResponse {

    public Long appointmentId;
    public String appointmentDate;
    public String appointmentTime;
    public Long patientDetails;
    public Long doctorDetails;
    public String appointmentStatus;

}
