package org.test.hospitalmanagementsystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Patient extends BaseModel{

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private long patientId;
    private String patientGender;
    private String patientName;
    private String patientAddress;
    private String patientPhone;
    private String patientEmail;

    //Referenced by foreign key constraint from Hospital on field hospitalId
    @ManyToOne
    @JoinColumn(name = "hospitalId")
    private Hospital hospital;

}
