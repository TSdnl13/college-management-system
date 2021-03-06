package com.collegeapp.management.controller;

import com.collegeapp.management.entity.Student;
import com.collegeapp.management.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/student")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public ResponseEntity<List<Student>> getStudents() {
        return new ResponseEntity<>(studentService.getStudents(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Student> registerNewStudent(@RequestBody Student student) {
        return studentService.addStudent(student)
                .map(savedStudent -> new ResponseEntity<>(savedStudent, HttpStatus.CREATED))
                .orElse(new ResponseEntity<>(HttpStatus.CONFLICT));
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable("id") String id) {
        return studentService.getStudentById(id)
                .map(student -> new ResponseEntity<>(student, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Student> updateStudent(
            @PathVariable("id") String id,
            @RequestBody Student student) {
        return studentService.updateStudent(id, student)
                .map(updatedStudent -> new ResponseEntity<>(updatedStudent, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.CONFLICT));
    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity deleteStudentById(@PathVariable("id") String id) {
        if (studentService.deleteStudentById(id)){
            return new ResponseEntity(HttpStatus.OK);
        }

        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

}
