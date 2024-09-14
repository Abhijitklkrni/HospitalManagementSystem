package org.test.hospitalmanagementsystem.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Doctor extends User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int doctorId;

    @ManyToOne
    @JoinColumn(name="hospitalId", nullable=false)
    public Hospital hospital;

    public String doctorSpecialization;
    public String doctorQualification;
    public String doctorExperience;


}
