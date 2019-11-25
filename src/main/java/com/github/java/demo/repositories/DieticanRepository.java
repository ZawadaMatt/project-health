package com.github.java.demo.repositories;

import com.github.java.demo.domain.Dietician;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DieticanRepository extends JpaRepository<Dietician, Long> {

}
