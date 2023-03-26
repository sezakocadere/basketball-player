package com.basketball.basketball.error;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ApiError {
    private final String message;
    private final HttpStatus status;
    private final String time;
}
