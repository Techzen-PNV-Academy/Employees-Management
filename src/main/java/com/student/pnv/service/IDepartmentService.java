package com.student.pnv.service;

import com.student.pnv.entity.Department;

import java.util.List;
import java.util.Optional;

public interface IDepartmentService {
    List<Department> findByName(String name);
    Optional<Department> findById(int id);
    Department save(Department department);
    void delete(int id);
}