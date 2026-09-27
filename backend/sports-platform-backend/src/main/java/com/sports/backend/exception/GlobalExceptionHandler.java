package com.sports.backend.exception;

import com.sports.backend.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;
/**
 * JSON
 *  ↓
 * @RequestBody
 *  ↓
 * ClubRequest Object
 *  ↓
 * @Valid
 *  ↓
 * @NotBlank / @Size ...
 *  ↓
 * ❌ Validation failed
 *  ↓
 * MethodArgumentNotValidException
 *  ↓
 * @RestControllerAdvice
 *  ↓
 * GlobalExceptionHandler
 *  ↓
 * handleValidationErrors()
 *  ↓
 * ErrorResponse
 *  ↓
 * HTTP Response
 *  ↓
 * Frontend / Postman
 *@Valid لا يتحقق من JSON نفسه مباشرة، بل Spring يحول الـ JSON إلى ClubRequest ثم يقوم بالـ validation على الـ object.
 * **/


@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationErrors(
            MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult()
                .getFieldErrors()
                .forEach(error ->
                        errors.put(
                                error.getField(),
                                error.getDefaultMessage()
                        )
                );

        ErrorResponse response = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                "Validation failed",
                errors
        );

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(response);
    }


    @ExceptionHandler(ClubNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleClubNotFound(
            ClubNotFoundException ex) {

        ErrorResponse response = new ErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage(),
                null
        );

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(response);

//        return ResponseEntity
//                .status(HttpStatus.NOT_FOUND)
//                .body(ex.getMessage());


    }

    @ExceptionHandler(InvalidSearchException.class)
    public ResponseEntity<ErrorResponse> handleInvalidSearch(InvalidSearchException ex){
        ErrorResponse response = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                ex.getMessage(),
                null
        );
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(response);

    }



}
