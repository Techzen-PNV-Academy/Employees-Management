package com.student.pnv.service;

import com.student.pnv.dto.employee.EmployeeSearchRequest;
import com.student.pnv.modal.Employee;

import java.util.List;
import java.util.UUID;

public interface IEmployeeService {
    List<Employee> findByAttributes(EmployeeSearchRequest employeeSearchRequest);

    Employee findById(UUID id);

    Employee save(Employee employee);

    Employee update(Employee employee);

    Employee delete(UUID id);
}