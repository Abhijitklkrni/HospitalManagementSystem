package org.test.hospitalmanagementsystem.controller;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.test.hospitalmanagementsystem.entity.Doctor;
import org.test.hospitalmanagementsystem.service.DoctorService;

@RestController
@RequestMapping("/api/v1/doctor")
public class DoctorController {
    public DoctorService service;

    public DoctorController(DoctorService service) {
        this.service = service;
    }

    @PostMapping("/add")
    public Doctor addDoctor(@RequestBody Doctor doctor) {
        return service.addDoctor(doctor);
    }

    @GetMapping("/get/{id}")
    public String getDoctor(@PathVariable String id) {
        return "Get doctor";
    }

}
