package com.omar.studentmanagementsys.bean;

public class Student {
    private int id;
    private String name;
    private String marks;
    private String gender;
    private String date_of_birth;
    private String email;

    public Student(String name, String marks, String gender, String date_of_birth, String email) {
        super();
        this.name = name;
        this.marks = marks;
        this.gender = gender;
        this.date_of_birth = date_of_birth;
        this.email = email;
    }

    public Student(int id, String name, String marks, String gender, String date_of_birth, String email) {
        super();
        this.id = id;
        this.name = name;
        this.marks = marks;
        this.gender = gender;
        this.date_of_birth = date_of_birth;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMarks() {
        return marks;
    }

    public void setMarks(String marks) {
        this.marks = marks;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(String date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
