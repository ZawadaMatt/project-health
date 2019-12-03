package com.github.java.demo.repositories;

import com.github.java.demo.domain.Diet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DietRepository extends JpaRepository<Diet, Long> {

}
