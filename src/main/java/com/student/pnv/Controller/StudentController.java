package com.student.pnv.Controller;

import com.student.pnv.dto.ApiResponse;
import com.student.pnv.exception.AppException;
import com.student.pnv.exception.ErrorCode;
import com.student.pnv.model.Student;
import com.student.pnv.service.StudentServiceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/students")
public class StudentController {
    private StudentServiceService studentService = new StudentServiceService();

    @GetMapping
    public ResponseEntity<List<Student>> getStudents() {
        return ResponseEntity.ok(studentService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findStudent(@PathVariable UUID id) {
        Student student = studentService.findById(id);
        if (student!=null) {
            return ResponseEntity.ok(ApiResponse.<Student>builder()
                    .data(student)
                    .build());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student not found");
        }
    }

    @PostMapping
    public ResponseEntity<?> addStudent(@RequestBody Student student) {
        Student newStudent = studentService.save(student);
        if (newStudent!=null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(student);
        }
        throw new AppException(ErrorCode.STUDENT_NOT_EXIST);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable("id") UUID id, @RequestBody Student updatedStudent) {
        Student student = studentService.update(id, updatedStudent);
        if(student!=null) {
            return ResponseEntity.ok(student);
        }
        throw new AppException(ErrorCode.STUDENT_NOT_EXIST);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable UUID id) {
        Student student = studentService.findById(id);
        if (student!=null) {
            studentService.delete(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Student deleted successfully");
        }
        throw new AppException(ErrorCode.STUDENT_NOT_EXIST);
    }
}