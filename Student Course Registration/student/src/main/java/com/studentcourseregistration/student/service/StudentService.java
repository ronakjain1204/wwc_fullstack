package com.studentcourseregistration.student.service;

import com.studentcourseregistration.student.model.Student;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional; 

import org.springframework.stereotype.Service;

@Service

// Service class banai hain jo ki business logic ko handel karegi
public class StudentService {
    private List<Student> students = new ArrayList<>();

    //Method to get all the students
    public List<Student> getAllStudents() {
        return students; 
    }

    // Method to get the student by the Id
    public Optional<Student> getStudentById(int id) {
        return students.stream()
                .filter(student -> student.getId() == id)
                .findFirst();
    }

    // Method to add a new student
    public void addStudent(Student student) {
        students.add(student); 
    }

    // Method to delete a student by Id
    public boolean deleteStudent(int id) {
        return students.removeIf(student -> student.getId() == id);
    }

    //Method to check if student exists by Id
    public boolean existsById(int id) {
        return students.stream().anyMatch(student -> student.getId() == id);
    }
}