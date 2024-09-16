package org.test.hospitalmanagementsystem.service;

import org.springframework.stereotype.Service;
import org.test.hospitalmanagementsystem.entity.Patient;
import org.test.hospitalmanagementsystem.repository.PatientRepository;

@Service
public class PatientService {

    public PatientRepository repository;

    public PatientService(PatientRepository repository) {
        this.repository = repository;
    }
    public Patient addPatient(Patient patient) {
        return repository.save(patient);

    }

    public Patient getPatient(Long s) {
        return repository.findById(s).orElse(null);
    }
}
