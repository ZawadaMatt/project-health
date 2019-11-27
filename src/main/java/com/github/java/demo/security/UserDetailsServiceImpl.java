package com.github.java.demo.security;

import com.github.java.demo.domain.Dietician;
import com.github.java.demo.domain.Patient;
import com.github.java.demo.repositories.DieticanRepository;
import com.github.java.demo.repositories.PatientsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.transaction.Transactional;

@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {


    private DieticanRepository dieticanRepository;
    private PatientsRepository patientsRepository;


    @Autowired
    public UserDetailsServiceImpl(DieticanRepository dieticanRepository, PatientsRepository patientsRepository) {
        this.dieticanRepository = dieticanRepository;
        this.patientsRepository = patientsRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Dietician dietician = dieticanRepository.findByEmail(username);
        if (dietician != null) {
            return new UserDetailsImpl(dietician);
        }

        Patient patient = patientsRepository.findPatientByEmail(username);
        if (patient != null) {
            return new UserDetailsImpl(patient);
        }
        throw new UsernameNotFoundException(username);
    }
}
