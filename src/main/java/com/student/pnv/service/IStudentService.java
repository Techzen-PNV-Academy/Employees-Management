package com.student.pnv.service;

import com.student.pnv.entity.Student;

import java.util.List;
import java.util.UUID;

public interface IStudentService {
    List<Student> findAll();

    Student findById(UUID id);

    Student save(Student student);

    Student update(UUID id, Student student);

    void delete(UUID id);
}
