package org.test.hospitalmanagementsystem.service;

import org.springframework.stereotype.Service;
import org.test.hospitalmanagementsystem.entity.Doctor;
import org.test.hospitalmanagementsystem.repository.DoctorRepository;

import javax.print.Doc;

@Service
public class DoctorService {

    DoctorRepository repository;

    public DoctorService(DoctorRepository repository) {
        this.repository = repository;
    }
    public Doctor addDoctor(Doctor doctor) {
        return repository.save(doctor);
    }

    public Doctor getDoctor(Long id) {
        return repository.findById(id).orElse(null);
    }
}
