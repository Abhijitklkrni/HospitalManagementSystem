package org.test.hospitalmanagementsystem.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Builder
public class Hospital extends BaseModel{

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private long hospitalId;
    private String hospitalName;
    private String hospitalAddress;
    private String hospitalContact;
    private String hospitalEmail;

}
