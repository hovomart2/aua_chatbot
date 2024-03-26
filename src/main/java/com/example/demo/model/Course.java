package com.example.demo.model;


import jakarta.persistence.*;
import java.util.Set;

@Table(name = "course")
@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "course_name")
    private String courseName;
    @Column(name = "course_code")
    private String courseCode;
    @Column
    private int credits;
    @Column(length = 10000)
    private String description;
    @ManyToMany
    @JoinTable(
            name = "course_prerequisite",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "prerequisite_id")
    )
    private Set<Course> prerequisites;
    @OneToOne(mappedBy = "course")
    private SemesterCourse semesterCourse;

    public Course(String courseName, String courseCode, String description) {
        this.courseName = courseName;
        this.courseCode = courseCode;
        this.description = description;
    }

    public Course(String courseName, String courseCode, int credits, String description) {
        this.courseName = courseName;
        this.courseCode = courseCode;
        this.credits = credits;
        this.description = description;
    }

    public Course() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Course> getPrerequisites() {
        return prerequisites;
    }

    public void setPrerequisites(Set<Course> prerequisites) {
        this.prerequisites = prerequisites;
    }

    public SemesterCourse getSemesterCourse() {
        return semesterCourse;
    }

    public void setSemesterCourse(SemesterCourse semesterCourse) {
        this.semesterCourse = semesterCourse;
    }
}
