package com.student.pnv.repository;

import com.student.pnv.modal.Department;

import java.util.List;

public interface IDepartmentRepository {
    List<Department> findAll();

    Department findById(Integer id);

    Department save(Department department);

    Department update(Department department);

    Department delete(Integer id);
}