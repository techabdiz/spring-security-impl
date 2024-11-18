package com.deaspider.exceptions;

import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import com.deaspider.models.ErrorResponse;

@ControllerAdvice
public class Handler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUserNotFound(UserNotFoundException ex,
        WebRequest req) { 
        return ResponseEntity
            .badRequest()
                .body(ErrorResponse
                    .builder()
                        .message(ex.getMessage())
                        .url(req.getContextPath())
                        .timestamp(LocalDateTime.now())
                        .code(HttpStatus.NOT_FOUND)
                    .build());
    }


    @ExceptionHandler(DuplicateResourceCreationException.class)
    public ResponseEntity<ErrorResponse> duplicateCreation(DuplicateResourceCreationException ex,
        WebRequest req) { 
        return ResponseEntity
            .badRequest()
                .body(ErrorResponse
                    .builder()
                        .message(ex.getMessage())
                        .url(req.getContextPath())
                        .timestamp(LocalDateTime.now())
                        .code(HttpStatus.ALREADY_REPORTED)
                    .build());
    }


    @ExceptionHandler(InvalidTokenException.class)
    public ResponseEntity<ErrorResponse> invalidToke (InvalidTokenException ex,
        WebRequest req) { 
        return ResponseEntity
            .badRequest()
                .body(ErrorResponse
                    .builder()
                        .message(ex.getMessage())
                        .url(req.getContextPath())
                        .timestamp(LocalDateTime.now())
                        .code(HttpStatus.BAD_REQUEST)
                    .build());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> invalidToke (Exception ex,
        WebRequest req) { 
        return ResponseEntity
            .badRequest()
                .body(ErrorResponse
                    .builder()
                        .message(ex.getMessage())
                        .url(req.getContextPath())
                        .timestamp(LocalDateTime.now())
                        .code(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build());
    }


}
