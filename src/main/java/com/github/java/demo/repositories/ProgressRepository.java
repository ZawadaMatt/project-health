package com.github.java.demo.repositories;

import com.github.java.demo.domain.Patient;
import com.github.java.demo.domain.Progress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProgressRepository extends JpaRepository <Progress, Long> {

    List<Progress> findProgressesByPatient(Patient patient);
}
