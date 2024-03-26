package com.example.demo.model;


import jakarta.persistence.*;

@Entity
@Table(name = "semester_course")
public class SemesterCourse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "course_id", referencedColumnName = "id")
    private Course course;
    @Column
    private String section;
    @Column
    private String session;
    @Column
    private String campus;
    @Column(name = "instructor_name")
    private String instructorName;
    @Column
    private String times;
    @Column
    private String takenAndSeats;
    @Column
    private int spacesWaiting;
    @Column
    private String location;

    public SemesterCourse(Course course, String section, String session, String campus, String instructorName, String times, String takenAndSeats, int spacesWaiting, String location) {
        this.course = course;
        this.section = section;
        this.session = session;
        this.campus = campus;
        this.instructorName = instructorName;
        this.times = times;
        this.takenAndSeats = takenAndSeats;
        this.spacesWaiting = spacesWaiting;
        this.location = location;
    }

    public SemesterCourse() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getInstructorName() {
        return instructorName;
    }

    public void setInstructorName(String instructorName) {
        this.instructorName = instructorName;
    }

    public String getTimes() {
        return times;
    }

    public void setTimes(String times) {
        this.times = times;
    }

    public String getTakenAndSeats() {
        return takenAndSeats;
    }

    public void setTakenAndSeats(String takenAndSeats) {
        this.takenAndSeats = takenAndSeats;
    }

    public int getSpacesWaiting() {
        return spacesWaiting;
    }

    public void setSpacesWaiting(int spacesWaiting) {
        this.spacesWaiting = spacesWaiting;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public String getCampus() {
        return campus;
    }

    public void setCampus(String campus) {
        this.campus = campus;
    }
}
