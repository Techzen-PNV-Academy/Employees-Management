package com.student.pnv.modal;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.student.pnv.ENUM.GENDER;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Employee {
    UUID id;
    String name;
    LocalDate dob;
    Gender gender;
    double salary;
    String phone;
    Integer departmentId;

    public enum Gender {
        MALE, FEMALE, OTHER;
    }
}
