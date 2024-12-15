package com.student.pnv.service.impl;

import com.student.pnv.modal.Department;
import com.student.pnv.repository.IDepartmentRepository;
import com.student.pnv.service.IDepartmentService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DepartmentService implements IDepartmentService {
    IDepartmentRepository departmentRepository;

    public Department getDepartment(Integer id) {
        return departmentRepository.findById(id);
    }

    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    public Department saveDepartment(Department department) {
        return departmentRepository.save(department);
    }

    public Department updateDepartment(Department department) {
        return departmentRepository.update(department);
    }

    public Department deleteDepartment(Integer departmentId) {
        return departmentRepository.delete(departmentId);
    }
}