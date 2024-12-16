package com.student.pnv.Controller;

import com.student.pnv.ENUM.GENDER;
import com.student.pnv.dto.ApiResponse;
import com.student.pnv.dto.employee.EmployeeSearchRequest;
import com.student.pnv.entity.Department;
import com.student.pnv.service.IEmployeeService;
import com.student.pnv.util.JSonResponse;
import jakarta.persistence.EntityNotFoundException;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.student.pnv.entity.Employee;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
@RestController
@RequestMapping("/employees")
@CrossOrigin("http://localhost:5173")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class EmployeeController {
    IEmployeeService employeeService;

    public EmployeeController(IEmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/filter")
    public ResponseEntity<?> getEmployees(String name, GENDER gender, LocalDate dob, Double minSalary, Double maxSalary, Department department_id) {
        return JSonResponse.ok(employeeService.findByAttr(name, gender, dob, minSalary, maxSalary, department_id));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Employee>>> getEmployees(){
        return JSonResponse.ok(employeeService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Employee>> findEmployee(@PathVariable Integer id) {
        Employee employee = employeeService.findById(id);
        if (employee == null) {
            return JSonResponse.notFound("Employee not found with ID: " + id);
        }
        return JSonResponse.ok(employee);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Employee>> addEmployees(@RequestBody Employee NewEmployees) {
        return JSonResponse.created(employeeService.save(NewEmployees));
    };
    
    @PutMapping
    public ResponseEntity<ApiResponse<Employee>> updateEmployee(@RequestBody Employee employee) {
        try {
            Employee updatedEmployee = employeeService.update(employee);
            return JSonResponse.ok(updatedEmployee);
        } catch (EntityNotFoundException e) {
            return JSonResponse.notFound(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteEmployees(@PathVariable Integer id) {
        JSonResponse.ok(employeeService.delete(id));
        return JSonResponse.noContent();
    }
}