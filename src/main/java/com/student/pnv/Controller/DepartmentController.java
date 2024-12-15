package com.student.pnv.Controller;

import com.student.pnv.dto.ApiResponse;
import com.student.pnv.service.IDepartmentService;
import com.student.pnv.util.JSonResponse;
import com.student.pnv.exception.AppException;
import com.student.pnv.exception.ErrorCode;
import com.student.pnv.modal.Department;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@CrossOrigin(value = "http://localhost:5173")
@RequestMapping("/department")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DepartmentController {
    IDepartmentService departmentService;

    public DepartmentController(IDepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Department>> createDepartment(@RequestBody Department newDepartment) {
        return JSonResponse.created(departmentService.saveDepartment(newDepartment));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Department>>> getDepartments() {
        return JSonResponse.ok(departmentService.getAllDepartments());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Department>> getDepartmentById(@PathVariable Integer id) {
        return JSonResponse.ok(departmentService.getDepartment(id));
    }

    @PutMapping
    public ResponseEntity<ApiResponse<Department>> updateDepartment(@RequestBody Department updatedDepartment) {
        return JSonResponse.ok(departmentService.updateDepartment(updatedDepartment));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Department>> deleteDepartment(@PathVariable int id) {
        return JSonResponse.ok(departmentService.deleteDepartment(id));
    }
}