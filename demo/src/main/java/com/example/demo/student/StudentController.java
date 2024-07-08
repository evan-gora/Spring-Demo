package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/student")
public class StudentController {

    private final StudentService STUDENT_SERVICE;

    @Autowired
    public StudentController(StudentService STUDENT_SERVICE) {
        this.STUDENT_SERVICE = STUDENT_SERVICE;
    }

    @GetMapping
    public List<Student> getStudents() {
        return STUDENT_SERVICE.getStudents();
    }

    @PostMapping
    public void registerNewStudent(@RequestBody Student student) {
        STUDENT_SERVICE.addNewStudent(student);
    }

    @DeleteMapping(path = "{studentID}")
    public void deleteStudent(@PathVariable("studentID") Long studentID) {
        STUDENT_SERVICE.deleteStudent(studentID);
    }

    @PutMapping(path = "{studentID}")
    public void updateStudent(@PathVariable("studentID") Long studentID,
                              @RequestParam(required = false) String name,
                              @RequestParam(required = false) String email) {
        STUDENT_SERVICE.updateStudent(studentID, name, email);
    }
}
