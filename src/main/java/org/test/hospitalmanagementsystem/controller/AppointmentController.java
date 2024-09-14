package org.test.hospitalmanagementsystem.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/appointment")
public class AppointmentController {

    @RequestMapping("/get")
    public String getAppointment() {
        return "Get appointment";
    }

    @RequestMapping("/add")
    public String addAppointment() {
        return "Add appointment";
    }

    @RequestMapping("/update")
    public String updateAppointment() {
        return "Update appointment";
    }

    @RequestMapping("/delete")
    public String deleteAppointment() {
        return "Delete appointment";
    }

}
