package com.github.java.demo.domain;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Progress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private LocalDate date;
    @Column
    private Double weight;
    @Column
    private Double height;
    @Column(name = "target_weight")
    private Double targerWeight;
    @Column
    private String commentary;

    @ManyToOne
    private Patient patient;

    public Long getId() {
        return id;
    }


    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Double getTargerWeight() {
        return targerWeight;
    }

    public void setTargerWeight(Double targerWeight) {
        this.targerWeight = targerWeight;
    }

    public String getCommentary() {
        return commentary;
    }

    public void setCommentary(String commentary) {
        this.commentary = commentary;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Progress)) return false;
        Progress progress = (Progress) o;
        return id.equals(progress.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Progress{" +
                "id=" + id +
                ", date=" + date +
                ", weight=" + weight +
                ", height=" + height +
                ", targerWeight=" + targerWeight +
                ", commentary='" + commentary + '\'' +
                ", client=" + patient +
                '}';
    }
}
