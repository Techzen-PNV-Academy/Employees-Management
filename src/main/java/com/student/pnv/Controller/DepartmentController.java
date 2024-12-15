package com.student.pnv.Controller;

import com.student.pnv.dto.JSonResponse;
import com.student.pnv.exception.AppException;
import com.student.pnv.exception.ErrorCode;
import com.student.pnv.model.Department;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(value = "http://localhost:5173")
@RequestMapping("/department")
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DepartmentController {
    private List<Department> departments = new ArrayList<>(
            Arrays.asList(
                    new Department(1, "Quản lý"),
                    new Department(2, "Kế toán"),
                    new Department(3, "Sale Marketing"),
                    new Department(4, "Sản xuất")
            )
    );

    @GetMapping
    public ResponseEntity<?> getDepartment() {
        return JSonResponse.ok(departments);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getDepartmentById(@PathVariable("id") int id) {
        return departments.stream()
                .filter(d -> d.getId() == id)
                .findFirst()
                .map(JSonResponse::ok)
                .orElseThrow(() -> new AppException(ErrorCode.DEPARTMENT_NOT_EXIST));
    }

    @PostMapping
    public ResponseEntity<?> addDepart(@RequestBody Department department) {
        department.setId((int) (Math.random()*100000000));
        departments.add(department);
        return JSonResponse.created(department);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateDepart(@PathVariable("id") int id, @RequestBody Department department) {
        return departments.stream()
                .filter(d -> d.getId() == id)
                .findFirst()
                .map(d -> {
                    d.setName(department.getName());
                    return JSonResponse.ok(d);
                })
                .orElseThrow(() -> new AppException(ErrorCode.DEPARTMENT_NOT_EXIST));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDepart(@PathVariable("id") int id) {
        return departments.stream()
                .filter(d -> d.getId() == id)
                .findFirst()
                .map(d -> {
                    departments.remove(d);
                    return JSonResponse.noContent();
                })
                .orElseThrow(() -> new AppException(ErrorCode.DEPARTMENT_NOT_EXIST));
    }
}