package com.student.pnv.service;

import com.student.pnv.ENUM.GENDER;
import com.student.pnv.dto.employee.EmployeeSearchRequest;
import com.student.pnv.entity.Department;
import com.student.pnv.entity.Employee;
import com.student.pnv.repository.IEmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public interface IEmployeeService {
    @Autowired
    IEmployeeRepository employeeRepository = null;

    List<Employee> findByAttr(String name, GENDER gender, LocalDate dob, Double minSalary, Double maxSalary, Department departmentId);

    List<Employee> findAll();

    Employee findById(Integer id);

    Employee save(Employee employee);

    Employee update(Employee employee);

    Void delete(Integer id);
}