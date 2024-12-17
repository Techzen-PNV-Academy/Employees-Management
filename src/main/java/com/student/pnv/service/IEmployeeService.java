package com.student.pnv.service;

import com.student.pnv.ENUM.GENDER;
import com.student.pnv.dto.employee.EmployeeSearchRequest;
import com.student.pnv.entity.Department;
import com.student.pnv.entity.Employee;
import com.student.pnv.repository.IEmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public interface IEmployeeService {
    @Autowired
    IEmployeeRepository employeeRepository = null;

    Page<Employee> findByAttributes(EmployeeSearchRequest employeeSearchRequest, Pageable pageable);

    List<Employee> findAll();

    Employee findById(Integer id);

    Employee save(Employee employee);

    Employee update(Integer id, Employee updatedData);

    Void delete(Integer id);
}