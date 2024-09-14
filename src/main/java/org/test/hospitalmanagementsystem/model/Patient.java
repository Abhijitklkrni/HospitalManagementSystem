package org.test.hospitalmanagementsystem.model;

import jakarta.persistence.*;

@Entity
public class Patient extends User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int patientId;
    public String patientGender;

    @ManyToOne
    @JoinColumn(name="hospitalId", nullable=false)
    public Hospital hospital;

}
