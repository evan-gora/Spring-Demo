package com.example.demo.student;

import jakarta.transaction.Transactional;
import org.hibernate.tool.schema.internal.StandardUniqueKeyExporter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository STUDENT_REPOSITORY;

    @Autowired
    public StudentService(StudentRepository STUDENT_REPOSITORY) {
        this.STUDENT_REPOSITORY = STUDENT_REPOSITORY;
    }

    public List<Student> getStudents() {
        return STUDENT_REPOSITORY.findAll();
    }

    public void addNewStudent(Student student) {
        Optional<Student> studentOptional = STUDENT_REPOSITORY.findStudentByEmail(student.getEmail());
        if (studentOptional.isPresent()) {
            throw new IllegalStateException("email taken");
        }
        STUDENT_REPOSITORY.save(student);
    }

    public void deleteStudent(Long studentID) {
       boolean exists =  STUDENT_REPOSITORY.existsById((studentID));
       if (!exists) {
           throw new IllegalStateException("student with id " + studentID + " does not exist");
       }
       STUDENT_REPOSITORY.deleteById(studentID);
    }

    @Transactional
    public void updateStudent(Long studentID, String name, String email) {
        Student student = STUDENT_REPOSITORY.findById(studentID).orElseThrow(() -> new IllegalStateException(
                "student with id " + studentID + " does not exist"
        ));

        if (name != null && name.isEmpty() && !Objects.equals(student.getName(), name)) {
            student.setName(name);
        }

        if (email != null && email.isEmpty() && !Objects.equals(student.getEmail(), email)) {
            Optional<Student> studentOptional = STUDENT_REPOSITORY.findStudentByEmail(email);
            if (studentOptional.isPresent()) {
                throw new IllegalStateException("email is taken");
            }
            student.setEmail(email);
        }
    }
}
