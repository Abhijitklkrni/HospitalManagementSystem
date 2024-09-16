package org.test.hospitalmanagementsystem.controller;

import org.springframework.web.bind.annotation.*;
import org.test.hospitalmanagementsystem.entity.Hospital;
import org.test.hospitalmanagementsystem.service.HospitalService;

@RestController
@RequestMapping("/api/v1/hospital")
public class HospitalController {

    HospitalService service;

    public HospitalController(HospitalService service) {
        this.service = service;
    }

    @PostMapping("/add")
    public Hospital addHospital(@RequestBody Hospital hospital) {
        return service.addHospital(hospital);
    }

    @GetMapping("/get/{id}")
    public String getHospital(@PathVariable String id) {
        return service.getHospital(id);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteHospital(@PathVariable String id) {
        return service.deleteHospital(id);
    }
}
