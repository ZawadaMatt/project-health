package com.github.java.demo.repositories;

import com.github.java.demo.domain.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PatientsRepository extends JpaRepository<Patient, Long> {

    Patient findPatientByEmail(String email);
}
