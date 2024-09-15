package org.test.hospitalmanagementsystem.controller;

import org.springframework.web.bind.annotation.*;
import org.test.hospitalmanagementsystem.entity.AppointmentHistory;
import org.test.hospitalmanagementsystem.entity.Slot;
import org.test.hospitalmanagementsystem.model.AppointmentRequest;
import org.test.hospitalmanagementsystem.model.AppointmentResponse;
import org.test.hospitalmanagementsystem.service.AppointmentService;
import org.test.hospitalmanagementsystem.service.SlotService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/appointment")
public class AppointmentController {

    AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @PostMapping("/book")
    public AppointmentResponse bookAppointment(@RequestBody AppointmentRequest appointmentRequest) {
        return appointmentService.bookSlot(appointmentRequest);
    }

    @GetMapping("/getSlots")
    public List<Slot> getSlotsForDoctorIdAndDate(@RequestParam String date,@RequestParam Long doctorId) {
        return appointmentService.getAllSlotsByDateAndDoctorId(date, doctorId);
    }

    @GetMapping("/getAppointmentsByPatient")
    public List<AppointmentResponse> getAppointmentsByPatient(@RequestParam Long patientId) {
        return appointmentService.getAppointmentsByPatient(patientId);
    }

    @PostMapping("/cancelAppointment")
    public AppointmentHistory cancelAppointment(@RequestParam Long appointmentId) {
        return appointmentService.cancelAppointment(appointmentId);
    }

}
