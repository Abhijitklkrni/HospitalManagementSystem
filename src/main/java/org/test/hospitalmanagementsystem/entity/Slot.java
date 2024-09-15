package org.test.hospitalmanagementsystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.test.hospitalmanagementsystem.model.SLOT_STATUS;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Slot {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    public long slotId;
    public String startTime;
    public String endTime;
    public String date;

    @Enumerated(EnumType.STRING)
    public SLOT_STATUS status;

    public long doctorId;

    public long patientId;

}
