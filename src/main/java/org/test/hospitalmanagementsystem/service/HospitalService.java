package org.test.hospitalmanagementsystem.service;

import org.springframework.stereotype.Service;
import org.test.hospitalmanagementsystem.entity.Hospital;
import org.test.hospitalmanagementsystem.repository.HospitalRepository;

@Service
public class HospitalService {

    HospitalRepository repository;

    public HospitalService(HospitalRepository repository) {
        this.repository = repository;
    }
    public Hospital addHospital(Hospital hospital) {
        return repository.save(hospital);
    }

    public String getHospital(String id) {
        return repository.findById(Long.parseLong(id)).get().getHospitalName();
    }

    public String deleteHospital(String id) {
        repository.deleteById(Long.parseLong(id));
        return "Hospital deleted successfully";
    }
}
