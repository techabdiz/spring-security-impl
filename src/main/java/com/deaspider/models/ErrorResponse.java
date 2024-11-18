package com.deaspider.models;

import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorResponse {

    private HttpStatus code;
    private String message;
    private LocalDateTime timestamp;
    private String url;
}
