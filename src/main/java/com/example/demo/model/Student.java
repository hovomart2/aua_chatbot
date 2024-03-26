package com.example.demo.model;

import jakarta.persistence.*;

@Table(name = "student")
@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "aua_id")
    private String auaId;

    @Column
    private String major;

    @Column(name = "admission_year")
    private int admissionYear;

    public Student(String firstName, String lastName, String auaId, String major, int admissionYear) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.auaId = auaId;
        this.major = major;
        this.admissionYear = admissionYear;
    }

    public Student() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAuaId() {
        return auaId;
    }

    public void setAuaId(String auaId) {
        this.auaId = auaId;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public int getAdmissionYear() {
        return admissionYear;
    }

    public void setAdmissionYear(int admissionYear) {
        this.admissionYear = admissionYear;
    }
}
