package com.deaspider.dto;

import com.deaspider.models.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private String name;
    private String email;
    private String mobile;
    private String username;

    public UserDTO(User user) { 
        this.name = user.getName();
        this.email = user.getEmail();
        this.mobile = user.getMobile();
        this.username = user.getUsername();
    }

    public User toEntity() { 
        return User
        .builder()
            .name(this.name)
            .email(this.email)
            .mobile(this.mobile)
            .username(this.username)
        .build();
    }
}
