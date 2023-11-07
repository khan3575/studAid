package com.example.studaid;

import java.sql.Date;

public class Student {
    final Integer id;
    final String year;
    private String course;
    final String firstName;
    final String lastName;
    final Date dateOfBirth;

    public String getGender() {
        return gender;
    }

    final String gender;

    private final String status;
    private String image;

    public Integer getId() {
        return id;
    }

    public String getYear() {
        return year;
    }

    public String getCourse() {
        return course;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public String getStatus() {
        return status;
    }

    public String getImage() {
        return image;
    }



    public Student(Integer id, String year, String course, String firstName, String lastName, String gender, Date dateOfBirth, String status, String image){
        this.id = id;
        this.year = year;
        this.course = course;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.status = status;
        this.image = image;

    }

}
