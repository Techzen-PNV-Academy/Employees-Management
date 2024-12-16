package com.student.pnv.Controller;

import com.student.pnv.dto.ApiResponse;
import com.student.pnv.exception.AppException;
import com.student.pnv.exception.ErrorCode;
import com.student.pnv.service.IDepartmentService;
import com.student.pnv.util.JSonResponse;
import com.student.pnv.entity.Department;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departments")
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DepartmentController {
    IDepartmentService departmentService;

    @GetMapping
    public ResponseEntity getAll(){
        return JSonResponse.ok(
                departmentService
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity <?> getById (@PathVariable("id") int id ) {
        return departmentService.findById(id)
                .map(JSonResponse::ok)
                .orElseThrow(()-> new AppException(ErrorCode.DEPARTMENT_NOT_EXIST));
    }

    @PostMapping
    public ResponseEntity<?> addDepart(@RequestBody Department department) {
        department.setId((int) (Math.random()*100000000));
        departmentService.save(department);
        return JSonResponse.created(department);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateDepart(@PathVariable("id") int id, @RequestBody Department department) {
        return departmentService.findById(id)
                .map(d -> {
                    d.setName(department.getName());
                    return JSonResponse.ok(d);
                })
                .orElseThrow(() -> new AppException(ErrorCode.DEPARTMENT_NOT_EXIST));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDepart(@PathVariable("id") int id) {
        return departmentService.findById(id)
                .map(d -> {
                    departmentService.delete(id);
                    return JSonResponse.noContent();
                })
                .orElseThrow(() -> new AppException(ErrorCode.DEPARTMENT_NOT_EXIST));
    }

}