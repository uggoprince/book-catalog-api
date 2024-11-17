package com.book_catalog.exception;

import com.book_catalog.dto.response.ApiResponse;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ApiResponse<Object>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = ex.getBindingResult().getFieldErrors().stream()
                .collect(Collectors.toMap(
                        error -> error.getField(),
                        error -> error.getDefaultMessage()
                ));
        ApiResponse response = new ApiResponse();
        response.setMessage("Some fields are not valid.");
        response.setSuccess(false);
        response.setError(errors);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse<Object>> handleResourceNotFoundException(ResourceNotFoundException ex) {
        ApiResponse<Object> response = ApiResponse.builder().message(ex.getMessage()).build();
        response.setMessage(ex.getMessage());
        response.setSuccess(false);
        response.setError(null);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ApiResponse<String>> handleBadRequestException(BadRequestException ex) {
        ApiResponse<String> response = new ApiResponse();
        response.setMessage(ex.getMessage());
        response.setSuccess(false);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidDateFormatException.class)
    public ResponseEntity<ApiResponse<Map<String, String>>> handleInvalidDateFormatException(InvalidDateFormatException ex) {
        ApiResponse response = new ApiResponse();
        response.setMessage("Some fields are not valid.");
        response.setSuccess(false);
        Map<String, String> errors = new HashMap<>();
        errors.put(ex.getFieldName(), ex.getErrorMessage());
        response.setError(errors);
        return new ResponseEntity<ApiResponse<Map<String, String>>>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiResponse<String>> handleInvalidDateFormat(HttpMessageNotReadableException ex) {
        if (ex.getCause() instanceof InvalidFormatException) {
            InvalidFormatException ife = (InvalidFormatException) ex.getCause();
            if (ife.getPath().stream().anyMatch(p -> p.getFieldName().equals("date"))) {
                Map<String, String> errorDetails = new HashMap<>();
                errorDetails.put("date", "Invalid date format");
                ApiResponse<String> response = ApiResponse.<String>builder()
                        .message("Invalid date format. Please use 'dd/MM/yyyy'.")
                        .success(false)
                        .error(errorDetails)
                        .build();
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
        }
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("error", ex.getMessage());
        ApiResponse<String> response = ApiResponse.<String>builder()
                .message("Invalid request format.")
                .success(false)
                .error(errorMap)
                .build();
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<String>> handleGenericException(Exception ex) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("error", ex.getMessage());  // Put the exception message into the map

        ApiResponse<String> response = ApiResponse.<String>builder()
                .message("Internal Server Error")
                .success(false)
                .error(errorMap)  // Use the map for error
                .build();

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
