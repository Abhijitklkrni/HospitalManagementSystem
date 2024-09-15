package org.test.hospitalmanagementsystem.entity;

import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@Data
@MappedSuperclass
public class User {

    public String userName;
    public String userAddress;
    public String userContact;
    public String userEmail;

}
