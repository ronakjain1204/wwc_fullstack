package com.studentcourseregistration.student.model;

public class Student {
    private int id;
    private String name;
    private String course;

    public Student(){};
    public Student(int id, String name, String course) {
        this.id = id;
        this.name = name;
        this.course = course;
    }
    
    // Making the getters and setters for the fields
    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getCourse(){
        return course;
    }

    public void setCourse(String course){
        this.course = course;
    }
    
}
