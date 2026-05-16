package com.aquagrid.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse<T> {

    private boolean success;
    private String message;
    private T data;
    private String errorCode;
    private LocalDateTime timestamp = LocalDateTime.now();

    // =========================
    // 4-ARG CONSTRUCTOR (ADDED)
    // =========================
    public ApiResponse(boolean success, String message, T data, String errorCode) {
        this.success = success;
        this.message = message;
        this.data = data;
        this.errorCode = errorCode;
        this.timestamp = LocalDateTime.now();
    }

    // =========================
    // SUCCESS RESPONSE
    // =========================
    public static <T> ApiResponse<T> success(String message, T data) {
        return new ApiResponse<>(
                true,
                message,
                data,
                null,
                LocalDateTime.now()
        );
    }

    // =========================
    // ERROR RESPONSE
    // =========================
    public static <T> ApiResponse<T> error(String message, String errorCode) {
        return new ApiResponse<>(
                false,
                message,
                null,
                errorCode,
                LocalDateTime.now()
        );
    }
}