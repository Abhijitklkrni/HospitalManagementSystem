package org.test.hospitalmanagementsystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Doctor{

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    public long doctorId;

    public long hospitalId;

    public String doctorName;
    public String doctorAddress;
    public String doctorPhone;
    public String doctorEmail;
    public String doctorSpecialization;
    public String doctorQualification;
    public String doctorExperience;


}
