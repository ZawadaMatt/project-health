package com.github.java.demo.repositories;

import com.github.java.demo.domain.Progress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProgressRepository extends JpaRepository <Progress, Long> {
}
