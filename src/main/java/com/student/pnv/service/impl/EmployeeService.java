package com.student.pnv.service.impl;

import com.student.pnv.ENUM.GENDER;
import com.student.pnv.dto.employee.EmployeeSearchRequest;
import com.student.pnv.entity.Department;
import com.student.pnv.entity.Employee;
import com.student.pnv.repository.IEmployeeRepository;
import com.student.pnv.service.IEmployeeService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class EmployeeService implements IEmployeeService {
    IEmployeeRepository employeeRepository;

    public List<Employee> findByAttr(String name, GENDER gender, LocalDate dob, Double minSalary, Double maxSalary, Department department) {
        return employeeRepository.findByAttr(name, gender, dob, minSalary, maxSalary, department);
    }

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee findById(Integer id) {
        return employeeRepository.findById(id).orElse(null);
    }

    @Override
    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Employee update(Employee employee) {
        if (!employeeRepository.existsById(employee.getId())) {
            throw new Error("Employee not found with ID: " + employee.getId());
        }
        return employeeRepository.save(employee);
    }

    @Override
    public Void delete(Integer id) {
        employeeRepository.deleteById(id);
        return null;
    }
}