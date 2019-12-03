package com.github.java.demo.domain;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Diet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @Column
    private LocalDate startdate;
    @Column
    private LocalDate currentDay;
    @OneToMany
    Set<Meal> mealsList = new HashSet<>();

    @OneToOne// (mappedBy = "Diet")
    Patient patient;

    public Diet () {
    }

    public Diet (String name, LocalDate startdate, LocalDate currentDay, Set<Meal> mealsList) {
        this.name = name;
        this.startdate = startdate;
        this.currentDay = currentDay;
        this.mealsList = mealsList;
    }

    public Long getId () {
        return id;
    }

    public void setId (Long id) {
        this.id = id;
    }

    public String getName () {
        return name;
    }

    public void setName (String name) {
        this.name = name;
    }

    public LocalDate getStartdate () {
        return startdate;
    }

    public void setStartdate (LocalDate startdate) {
        this.startdate = startdate;
    }

    public LocalDate getCurrentDay () {
        return currentDay;
    }

    public void setCurrentDay (LocalDate currentDay) {
        this.currentDay = currentDay;
    }

    public Set<Meal> getMealsList () {
        return mealsList;
    }

    public void setMealsList (Set<Meal> mealsList) {
        this.mealsList = mealsList;
    }

    public Patient getPatient () {
        return patient;
    }

    public void setPatient (Patient patient) {
        this.patient = patient;
    }

    @Override
    public boolean equals (Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Diet diet = (Diet) o;
        return id.equals(diet.id) &&
                name.equals(diet.name) &&
                startdate.equals(diet.startdate) &&
                currentDay.equals(diet.currentDay) &&
                mealsList.equals(diet.mealsList);
    }

    @Override
    public int hashCode () {
        return Objects.hash(id, name, startdate, currentDay, mealsList);
    }


    @Override
    public String toString () {
        return "Diet{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", startdate=" + startdate +
                ", currentDay=" + currentDay +
                ", mealsList=" + mealsList +
                '}';
    }
}
