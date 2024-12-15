package com.student.pnv.Controller;

import com.student.pnv.dto.ApiResponse;
import com.student.pnv.dto.employee.EmployeeSearchRequest;
import com.student.pnv.service.IEmployeeService;
import com.student.pnv.util.JSonResponse;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.student.pnv.modal.Employee;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/employees")
@CrossOrigin("http://localhost:5173")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class EmployeeController {

    IEmployeeService employeeService;

    public EmployeeController(IEmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Employee>>> getEmployees(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dobFrom,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dobTo,
            @RequestParam(required = false) Employee.Gender gender,
            @RequestParam(required = false) String salaryRange,
            @RequestParam(required = false) String phone,
            @RequestParam(required = false) Integer departmentId
    ) {
        EmployeeSearchRequest request = new EmployeeSearchRequest(name, dobFrom, dobTo, gender, salaryRange, phone, departmentId);
        return JSonResponse.ok(employeeService.findByAttributes(request));
    };

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Employee>> getEmployees(@PathVariable UUID id) {
        return JSonResponse.ok(employeeService.findById(id));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Employee>> addEmployees(@RequestBody Employee NewEmployees) {
        return JSonResponse.created(employeeService.save(NewEmployees));
    };

    @PutMapping
    public ResponseEntity<ApiResponse<Employee>> updateEmployees(@RequestBody Employee NewEmployees) {
        return JSonResponse.ok(employeeService.update(NewEmployees));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Employee>> deleteEmployees(@PathVariable UUID id) {
        return JSonResponse.ok(employeeService.delete(id));
    }
}