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
public class Doctor extends BaseModel{

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private long doctorId;

    //Referenced by foreign key constraint from Hospital on field hospitalId
    @ManyToOne
    @JoinColumn(name = "hospitalId")
    private Hospital hospital;

    private String doctorName;
    private String doctorAddress;
    private String doctorPhone;
    private String doctorEmail;
    private String doctorSpecialization;
    private String doctorQualification;
    private String doctorExperience;


}
