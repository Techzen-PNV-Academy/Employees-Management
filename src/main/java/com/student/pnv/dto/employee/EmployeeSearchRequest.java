package com.student.pnv.dto.employee;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.format.annotation.DateTimeFormat;
import com.student.pnv.entity.Employee;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EmployeeSearchRequest {
    String name;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    LocalDate dobFrom;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    LocalDate dobTo;
    Employee.Gender gender;
    String salary;
    String phone;
    Integer departmentId;

    public enum Gender {
        MALE, FEMALE, OTHER;
    }
}