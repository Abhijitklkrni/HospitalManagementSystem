package org.test.hospitalmanagementsystem.model;


public record AppointmentRequest(Long hospitalId,Long patientId, Long doctorId, Long slotId) {

}
