package com.deaspider.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.ALREADY_REPORTED)
public class DuplicateResourceCreationException extends RuntimeException {
    
    public DuplicateResourceCreationException(String msg) { 
        super(msg);
    }

}
