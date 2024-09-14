package org.test.hospitalmanagementsystem.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Slot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int slotId;
    public String slotTime;
    public String slotDate;
    public String slotStatus;

    @OneToOne
    @JoinColumn(name="doctorId", nullable=false)
    public Doctor slotDoctor;

    @OneToOne
    @JoinColumn(name="patientId", nullable=false)
    public Patient slotPatient;


}
