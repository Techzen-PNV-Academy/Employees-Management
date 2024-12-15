package com.student.pnv.util;
import com.student.pnv.dto.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class JSonResponse {
    public static <T> ResponseEntity<ApiResponse<T>> ok(T t){
        return ResponseEntity.ok(ApiResponse.<T>builder().data(t).build());
    }
    public static <T> ResponseEntity<ApiResponse<T>> created(T t){
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.<T>builder().data(t).build());
    }
    public static <T> ResponseEntity<ApiResponse<T>> noContent(){
        return ResponseEntity.notFound().build();
    }
}