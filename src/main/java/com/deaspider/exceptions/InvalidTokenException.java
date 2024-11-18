package com.deaspider.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class InvalidTokenException extends RuntimeException {

    public InvalidTokenException(String msg) { 
        super(msg);
    }

}
