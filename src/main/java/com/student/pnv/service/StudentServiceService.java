package com.student.pnv.service;

import com.student.pnv.model.Student;
import com.student.pnv.repository.IStudentRepository;
import com.student.pnv.repository.StudentRepository;

import java.util.List;
import java.util.UUID;

public class StudentServiceService implements IStudentService {
    private IStudentRepository studentRepository = new StudentRepository();

    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    public Student findById(UUID id) {
        return studentRepository.findById(id);
    }

    public Student save(Student student) {
        return studentRepository.save(student);
    }

    public Student update(UUID id, Student student) {
        return studentRepository.update(id, student);
    }

    public void delete(UUID id) {
        studentRepository.delete(id);
    }
}