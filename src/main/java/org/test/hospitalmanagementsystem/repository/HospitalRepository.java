package org.test.hospitalmanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.test.hospitalmanagementsystem.entity.Hospital;

@Repository
public interface HospitalRepository extends JpaRepository<Hospital, Long>{


}
