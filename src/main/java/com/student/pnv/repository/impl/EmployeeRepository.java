package com.student.pnv.repository.impl;

import com.student.pnv.ENUM.GENDER;
import com.student.pnv.dto.employee.EmployeeSearchRequest;
import com.student.pnv.exception.AppException;
import com.student.pnv.exception.ErrorCode;
import com.student.pnv.modal.Employee;
import com.student.pnv.repository.IEmployeeRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class EmployeeRepository implements IEmployeeRepository {
    private List<Employee> employees = new ArrayList<>(
            Arrays.asList(
                    new Employee(UUID.randomUUID(), "Nguyen Van Duc", LocalDate.of(1990, 5, 15), Employee.Gender.FEMALE, 1500.0, "0901234567",2),
                    new Employee(UUID.randomUUID(), "Tran Duc Phat", LocalDate.of(1995, 8, 20),Employee.Gender.MALE , 2000.0, "0902234567",1),
                    new Employee(UUID.randomUUID(), "Le Van Viet", LocalDate.of(1992, 12, 10), Employee.Gender.FEMALE , 1800.0, "0903234567",3),
                    new Employee(UUID.randomUUID(), "Nguyen Nghia", LocalDate.of(1997, 3, 25), Employee.Gender.MALE , 2200.0, "0904234567",4),
                    new Employee(UUID.randomUUID(), "A Tan", LocalDate.of(1998, 7, 5), Employee.Gender.FEMALE , 1600.0, "0905234567",1)
            )
    );

    public List<Employee> findByAttributes(EmployeeSearchRequest employeeSearchRequest) {
        return employees.stream()
                .filter(e -> employeeSearchRequest.getName() == null || e.getName().toLowerCase().contains(employeeSearchRequest.getName().toLowerCase()))
                .filter(e -> employeeSearchRequest.getDobFrom() == null || !e.getDob().isBefore(employeeSearchRequest.getDobFrom()))
                .filter(e -> employeeSearchRequest.getDobTo() == null || !e.getDob().isAfter(employeeSearchRequest.getDobTo()))
                .filter(e -> employeeSearchRequest.getGender() == null || e.getGender().equals(employeeSearchRequest.getGender()))
                .filter(e -> employeeSearchRequest.getPhone() == null || e.getPhone().contains(employeeSearchRequest.getPhone()))
                .filter(e -> employeeSearchRequest.getDepartmentId() == null ||  e.getDepartmentId().equals(employeeSearchRequest.getDepartmentId()))
                .filter(e -> {
                    if (employeeSearchRequest.getSalaryRange() == null) return true;
                    return switch (employeeSearchRequest.getSalaryRange()) {
                        case "1-5" -> e.getSalary() < 5000000;
                        case "5-10" -> e.getSalary() >= 5000000 && e.getSalary() < 10000000;
                        case "10-20" -> e.getSalary() >= 10000000 && e.getSalary() <= 20000000;
                        case "gt20" -> e.getSalary() > 20000000;
                        default -> true;
                    };
                })
                .collect(Collectors.toList());
    }

    public List<Employee> findAll() {
        return employees;
    }

    public Employee findById(UUID id) {
        for (Employee emp : employees) {
            if (emp.getId() == id) {
                return emp;
            }
        }
        return null;
    }

    public Employee save(Employee employee) {
        employees.add(employee);
        return employee;
    }

    public Employee update(Employee employee) {
        for (Employee emp : employees) {
            if (emp.getId().equals(employee.getId())) {
                emp.setName(employee.getName());
                emp.setGender(employee.getGender());
                emp.setSalary(employee.getSalary());
                emp.setPhone(employee.getPhone());
                emp.setDob(employee.getDob());
                emp.setDepartmentId(employee.getDepartmentId());

                return emp;
            }
        }
        throw new AppException(ErrorCode.EMPLOYEE_NOT_EXIST);
    }

    public Employee delete(UUID id) {
        for (Employee emp : employees) {
            if (emp.getId().equals(id)) {
                employees.remove(emp);
                return emp;
            }
        }
        throw new AppException(ErrorCode.EMPLOYEE_NOT_EXIST);
    }
}