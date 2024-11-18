package com.deaspider.dto;

import lombok.Data;

@Data
public class PasswordDTO {

    private String token;
    private String password;
    private String confirm;
    
}
