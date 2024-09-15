package org.test.hospitalmanagementsystem.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.test.hospitalmanagementsystem.entity.Patient;
import org.test.hospitalmanagementsystem.service.PatientService;

@RestController
@RequestMapping("/api/v1/patient")
public class PatientController {

    PatientService service;

    public PatientController(PatientService service) {
        this.service = service;
    }

    @PostMapping("/add")
    public Patient addPatient(@RequestBody Patient patient) {
        return service.addPatient(patient);
    }




}
