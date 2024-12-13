package com.student.pnv.Controller;

import com.student.pnv.constant.GENDER;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<List<Employee>> getEmployees() {
        return ResponseEntity.ok(employees);
    };

    @PostMapping
    public ResponseEntity<Employee> addEmployees(@RequestBody Employee NewEmployees) {
        employees.add(NewEmployees);
        return ResponseEntity.status(HttpStatus.CREATED).body(NewEmployees);
    };

    @PutMapping
    public ResponseEntity<Employee> updateEmployees(@RequestBody Employee NewEmployees) {
        for (Employee emp : employees) {
            if (emp.getId().equals(NewEmployees.getId())) {
                emp.setName(NewEmployees.getName());
                emp.setGender(NewEmployees.getGender());
                emp.setSalary(NewEmployees.getSalary());
                emp.setPhone(NewEmployees.getPhone());
                emp.setDob(NewEmployees.getDob());

                return ResponseEntity.status(HttpStatus.CREATED).body(emp);
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @DeleteMapping
    public ResponseEntity<Employee> deleteEmployees(@RequestBody Employee NewEmployees) {
        for (Employee emp : employees) {
            if (emp.getId().equals(NewEmployees.getId())) {
                employees.remove(emp);

                return ResponseEntity.status(HttpStatus.OK).body(emp);
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}
