package com.github.java.demo.domain;


import com.github.java.demo.security.User;
import com.github.java.demo.security.UserRole;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity(name = "patients")
public class Patient implements User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String password;
    @Column
    private String email;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column
    private String name;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "date_of_birth")
    private LocalDate birthDate;
    @Column(name = "gender")
    private String gender;
    @Column
    private String affections;
    @Column
    private LocalDate createAt;
    @Column
    private boolean isActive;

    @ManyToOne
    private Dietician mainDoctor;

    @OneToMany//(mappedBy = "patient")
    private Set<Progress> progressSet = new HashSet<>();

   @OneToOne
   Diet diet;

    public Diet getDiet () {
        return diet;
    }

    public void setDiet (Diet diet) {
        this.diet = diet;
    }

    public Patient() {
    }

    public Patient(String password, String email, String phoneNumber, String name, String lastName, LocalDate birthDate, String gender, String affections, Set<Progress> progressSet) {
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.name = name;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.gender = gender;
        this.affections = affections;
        this.createAt = LocalDate.now();
        this.isActive = true;
        this.progressSet = progressSet;
    }

    public LocalDate getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDate createAt) {
        this.createAt = createAt;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAffections() {
        return affections;
    }

    public void setAffections(String affections) {
        this.affections = affections;
    }

    public Dietician getMainDoctor() {
        return mainDoctor;
    }

    public void setMainDoctor(Dietician mainDoctor) {
        this.mainDoctor = mainDoctor;
    }

    public Set<Progress> getProgressSet() {
        return progressSet;
    }

    public void setProgressSet(Set<Progress> progressSet) {
        this.progressSet = progressSet;
    }


    @Override
    public UserRole getRole() {
        return UserRole.PATIENT;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Patient)) return false;
        Patient patient = (Patient) o;
        return id.equals(patient.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDate=" + birthDate +
                ", gender='" + gender + '\'' +
                ", affections='" + affections + '\'' +
                ", mainDoctor=" + mainDoctor +
                ", progressSet=" + progressSet +
                '}';
    }
}
