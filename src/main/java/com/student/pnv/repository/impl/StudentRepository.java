package com.student.pnv.repository.impl;

import com.student.pnv.entity.Student;
import com.student.pnv.repository.IStudentRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class StudentRepository implements IStudentRepository {
    private List<Student> students = new ArrayList<>(
            Arrays.asList(
                    new Student(UUID.randomUUID(), "Nguyen Van Duc", "Da Nang", "Da Nang", "Viet Nam"),
                    new Student(UUID.randomUUID(), "A Tan", "200 Le Huu Trac", "Da Nang", "US"),
                    new Student(UUID.randomUUID(),"Tran Duc Phat", "Minh Long", "Quang Ngai", "Viet Nam"),
                    new Student(UUID.randomUUID(),"Tran Nhat", "Ha Boi", "Quang Ngai", "Quang Ngai"),
                    new Student(UUID.randomUUID(),"Tran Duc Hung", "Thon 1", "Thiep Xuyen", "France"),
                    new Student(UUID.randomUUID(),"Phat Tran", "Hai Chau", "Duc Ai", "UAE"),
                    new Student(UUID.randomUUID(),"Tran Phat", "Son Tra", "Quang Ngai", "Japan"),
                    new Student(UUID.randomUUID(),"Phat Dep Trai", "Long Hiep", "Quang Ngai", "China")
            )
    );
    public List<Student> findAll() {
        return students;
    }

    public Student findById(UUID id) {
        for (Student student : students) {
            if (student.getId().equals(id)) {
                return student;
            }
        }
        return null;
    }

    public Student save(Student student){
        student.setId(UUID.randomUUID());
        students.add(student);
        return student;
    }

    public Student update(UUID id, Student updatedStudent) {
        for (Student student : students) {
            if (student.getId().equals(id)) {
                student.setName(updatedStudent.getName());
                student.setAddress(updatedStudent.getAddress());
                student.setCity(updatedStudent.getCity());
                student.setCountry(updatedStudent.getCountry());
                return student;
            }
        }
        return null;
    }

    public void delete(UUID id) {
        for (Student student : students) {
            if (student.getId().equals(id)) {
                students.remove(student);
                return;
            }
        }
    }
}