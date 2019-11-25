package com.github.java.demo.controllers;

import com.github.java.demo.domain.Dietician;
import com.github.java.demo.repositories.DieticanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DieticianRegistrationController {
    private final DieticanRepository dieticianRepository;

@Autowired
    public DieticianRegistrationController (DieticanRepository dieticianRepository) {
        this.dieticianRepository = dieticianRepository;
    }

    @GetMapping("/dietic-register")
    public String prepareRegistrationPage(){
    return "WEB-INF/jsp/dietician-registration-page.jsp";
    }

    @PostMapping("/dietic-register")
    public String processRegistrationPage(String password, String email, String licenceNumber, String name, String lastName){
        Dietician dietician= new Dietician();
        dietician.setEmail(email);
        dietician.setPassword(password);
        dietician.setName(name);
        dietician.setLastName(lastName);
        dietician.setLicenceNumber(licenceNumber);
    return "WEB-INF/jsp/home-page.jsp";
    }
}
