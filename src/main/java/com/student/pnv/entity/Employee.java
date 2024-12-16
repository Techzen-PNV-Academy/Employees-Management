package com.student.pnv.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Builder
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    Integer id;
    String name;
    LocalDate dob;
    Gender gender;
    double salary;
    String phone;

    public enum Gender {
        MALE, FEMALE, OTHER;
    }

    @ManyToOne
    Department department;
}
