package com.student.pnv.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor()
@NoArgsConstructor
public enum ErrorCode {
    STUDENT_NOT_EXIST(22222, "Student not found", HttpStatus.NOT_FOUND),
    TEACHER_NOT_EXIST(40402, "Teacher not found", HttpStatus.NOT_FOUND),
    EMPLOYEE_NOT_EXIST(40401, "Employee does not exist", HttpStatus.NOT_FOUND);
    ;

    Integer code;
    String message;
    HttpStatus status;
}
