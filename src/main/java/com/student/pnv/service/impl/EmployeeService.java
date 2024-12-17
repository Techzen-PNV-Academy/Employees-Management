package com.student.pnv.service.impl;

import com.student.pnv.dto.employee.EmployeeSearchRequest;
import com.student.pnv.entity.Employee;
import com.student.pnv.repository.IEmployeeRepository;
import com.student.pnv.service.IEmployeeService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class EmployeeService implements IEmployeeService {
    @Autowired
    IEmployeeRepository employeeRepository;

    @Override
    public Page<Employee> findByAttributes(EmployeeSearchRequest employeeSearchRequest, Pageable pageable) {
        return employeeRepository.findByAttributes(employeeSearchRequest, pageable);
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
    public Employee update(Integer id, Employee updatedData) {
        Employee employee = employeeRepository.findById(id).get();
        employee.setName(updatedData.getName());
        employee.setSalary(updatedData.getSalary());
        employee.setPhone(updatedData.getPhone());
        employee.setGender(updatedData.getGender());
        employee.setId(updatedData.getId());
        employeeRepository.save(employee);
        return employee;
    }

    @Override
    public Void delete(Integer id) {
        employeeRepository.deleteById(id);
        return null;
    }
}