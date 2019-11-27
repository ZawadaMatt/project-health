package com.github.java.demo.domain;

import com.github.java.demo.security.User;
import com.github.java.demo.security.UserRole;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Dietician implements User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String password;
    @Column
    private String email;
    @Column(name = "licence_number")
    private String licenceNumber;
    @Column
    private String name;
    @Column(name = "last_name")
    private String lastName;

    @OneToMany(mappedBy = "mainDoctor")
    Set<Patient> patientSet = new HashSet<>();

    public Long getId() {
        return id;
    }

    public String getPassword () {
        return password;
    }

    public void setPassword (String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLicenceNumber() {
        return licenceNumber;
    }

    public void setLicenceNumber(String licenceNumber) {
        this.licenceNumber = licenceNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Set<Patient> getPatientSet() {
        return patientSet;
    }

    public void setPatientSet(Set<Patient> patientSet) {
        this.patientSet = patientSet;
    }

    @Override
    public UserRole getRole() {
        return UserRole.DIETETIAN;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Dietician)) return false;
        Dietician dietician = (Dietician) o;
        return id.equals(dietician.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Dietician{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", licenceNumber='" + licenceNumber + '\'' +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", clientSet=" + patientSet +
                '}';
    }
}