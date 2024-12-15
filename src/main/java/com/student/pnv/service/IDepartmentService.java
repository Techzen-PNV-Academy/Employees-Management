package com.student.pnv.service;

import com.student.pnv.modal.Department;

import java.util.List;

public interface IDepartmentService {

    Department getDepartment(Integer id);

    List<Department> getAllDepartments();

    Department saveDepartment(Department department);

    Department updateDepartment(Department department);

    Department deleteDepartment(Integer departmentId);
}