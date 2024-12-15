package com.student.pnv.Controller;

import com.student.pnv.constant.GENDER;
import com.student.pnv.dto.JSonResponse;
import com.student.pnv.exception.AppException;
import com.student.pnv.exception.ErrorCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.student.pnv.model.Employee;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/employees")
@CrossOrigin("http://localhost:5173")
public class EmployeeController {
    private List<Employee> employees = new ArrayList<>(
            Arrays.asList(
                    new Employee(UUID.randomUUID(), "Nguyen Van Duc", LocalDate.of(1990, 5, 15), GENDER.FEMALE, 1500.0, "0901234567"),
                    new Employee(UUID.randomUUID(), "Tran Duc Phat", LocalDate.of(1995, 8, 20),GENDER.MALE , 2000.0, "0902234567"),
                    new Employee(UUID.randomUUID(), "Le Van Viet", LocalDate.of(1992, 12, 10), GENDER.MALE , 1800.0, "0903234567"),
                    new Employee(UUID.randomUUID(), "Nguyen Nghia", LocalDate.of(1997, 3, 25), GENDER.MALE , 2200.0, "0904234567"),
                    new Employee(UUID.randomUUID(), "A Tan", LocalDate.of(1998, 7, 5), GENDER.FEMALE , 1600.0, "0905234567")
            )
    );
    @GetMapping
    public ResponseEntity<?> getEmployees() {
        return JSonResponse.ok(employees);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getEmployeeByID(@PathVariable("id") UUID id) {
        return employees.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst()
                .map(JSonResponse::ok)
                .orElseThrow(() -> new AppException(ErrorCode.EMPLOYEE_NOT_EXIST));
    }

    @PostMapping
    public ResponseEntity<?> addEmployees(@RequestBody Employee NewEmployees) {
        NewEmployees.setId(UUID.randomUUID());
        employees.add(NewEmployees);
        return JSonResponse.created(NewEmployees);
    };

    @PutMapping
    public ResponseEntity<?> updateEmployee(@PathVariable("id") UUID id, @RequestBody Employee employee) {
        return employees.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst()
                .map(e -> {
                    e.setName(employee.getName());
                    e.setDob(employee.getDob());
                    e.setGender(employee.getGender());
                    e.setSalary(employee.getSalary());
                    e.setPhone(employee.getPhone());

                    return JSonResponse.ok(e);
                })
                .orElseThrow(() -> new AppException(ErrorCode.EMPLOYEE_NOT_EXIST));
    }

    @DeleteMapping
    public ResponseEntity<?> deleteEmployee(@PathVariable("id") UUID id) {
        return employees.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst()
                .map(e -> {
                    employees.remove(e);
                    return JSonResponse.noContent();
                })
                .orElseThrow(() -> new AppException(ErrorCode.EMPLOYEE_NOT_EXIST));
    }
}
