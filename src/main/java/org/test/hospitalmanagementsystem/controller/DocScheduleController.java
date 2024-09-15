package org.test.hospitalmanagementsystem.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.test.hospitalmanagementsystem.entity.DoctorSchedule;
import org.test.hospitalmanagementsystem.model.DoctorScheduleResponse;
import org.test.hospitalmanagementsystem.service.DocScheduleService;
import org.test.hospitalmanagementsystem.service.DoctorService;

@RestController
@RequestMapping("/api/v1/doctor-schedule")
public class DocScheduleController {

    DocScheduleService service;

    public DocScheduleController(DocScheduleService service) {
        this.service = service;
    }

    @PostMapping("/add")
    public String addDocSchedule(@RequestBody DoctorSchedule doctorSchedule) {
        return service.addDocSchedule(doctorSchedule);
    }

    @PostMapping("/cancel")
    public DoctorScheduleResponse cancelDocSchedule(@RequestBody DoctorSchedule doctorSchedule) {
        return service.cancelDocSchedule(doctorSchedule);
    }
}
