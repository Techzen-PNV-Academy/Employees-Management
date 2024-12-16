package com.student.pnv.service.impl;

import com.student.pnv.entity.Department;
import com.student.pnv.repository.IDepartmentRepository;
import com.student.pnv.service.IDepartmentService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DepartmentService implements IDepartmentService {
    IDepartmentRepository departmentRepository;

    @Override
    public List<Department> findByName(String name) {
        return departmentRepository.findByName(name);
    }

    @Override
    public Optional<Department> findById(int id) {
        return departmentRepository.findById(id);
    }

    @Override
    public Department save(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public void delete(int id) {
        departmentRepository.deleteById(id); // use deleteById method to delete by ID
    }
}