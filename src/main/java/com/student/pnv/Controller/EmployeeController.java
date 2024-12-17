package com.student.pnv.Controller;

import com.student.pnv.dto.ApiResponse;
import com.student.pnv.dto.employee.EmployeeSearchRequest;
import com.student.pnv.service.IEmployeeService;
import com.student.pnv.util.JSonResponse;
import jakarta.persistence.EntityNotFoundException;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.student.pnv.entity.Employee;
import org.springframework.data.domain.Pageable;
import java.util.List;

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

    @GetMapping("/search")
    public ResponseEntity<?> getAllEmployees(
            @RequestParam(required = false) Integer id,
            EmployeeSearchRequest employeeSearchRequest,
            @PageableDefault(size = 5, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {

        if (id != null) {
            return ResponseEntity.ok(employeeService.findById(id));
        }

        Page<Employee> employees = employeeService.findByAttributes(employeeSearchRequest, pageable);
        return ResponseEntity.ok(employees);
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
    public ResponseEntity<ApiResponse<Employee>> updateEmployee(@PathVariable Integer id,@RequestBody Employee employee) {
        try {
            Employee updatedEmployee = employeeService.update(id, employee);
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