package com.student.pnv.service;

import com.student.pnv.entity.Department;
import com.student.pnv.repository.IEmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public interface IDepartmentService {
    @Autowired
    IEmployeeRepository departermentRepository = null;

    List<Department> findAll();

    Optional<Department> findById(int id);

    Department save(Department department);

    Department updateDepartment(int id, Department updatedData);

    void delete(int id);
}