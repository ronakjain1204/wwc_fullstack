package com.studentcourseregistration.student.controller;

import com.studentcourseregistration.student.model.Student;
import com.studentcourseregistration.student.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/students")

// Controller class banai hain jo ki HTTP requests ko handle karegi
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // register a student accept the details in json format
    @PostMapping
    // Endpoint banaya hain student register karne ke liye
    public ResponseEntity<Object> RegisterStudent(@RequestBody Student student){
        // Check kar rahe ki students ke naam or course empty naa hoo
        if(student.getName() == null || student.getName().isEmpty() || student.getCourse() == null ||student.getCourse().isEmpty()){
            return new ResponseEntity<>("Name and Course cannot be empty", HttpStatus.BAD_REQUEST);
        }

        // Check kar rahe ki student already exist toh nahi karta same ID se
        if(studentService.existsById(student.getId())){
            return new ResponseEntity<>("Student with ID " + student.getId() + " already exists.", HttpStatus.CONFLICT);
        }

        // if everything is fine then add the student
        studentService.addStudent(student);
        return new ResponseEntity<>("Student registered successfully", HttpStatus.CREATED);
    }
    
    @GetMapping
    // Endpoint banaya hain sabhi students ko get karne ke liye
    public ResponseEntity<List<Student>> getAllStudents() {
        return new ResponseEntity<>(studentService.getAllStudents(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    // Endpoint banaya hain student ko Id ke through get karne ke liye
    public ResponseEntity<Object> getStudentById(@PathVariable int id) {
        return studentService.getStudentById(id)
                .map(student -> new ResponseEntity<Object>(student, HttpStatus.OK))
                .orElse(new ResponseEntity<>("Student not found with ID: " + id, HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    // Endpoint banaya hain student ko Id ke through delete karne ke liye
    public ResponseEntity<Object> deleteStudent(@PathVariable int id) {
        boolean removed = studentService.deleteStudent(id);
        if (removed) {
            return new ResponseEntity<>("Student deleted successfully", HttpStatus.OK); 
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); 
        }
    }
}

