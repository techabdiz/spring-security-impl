package com.deaspider.dto;

import java.time.LocalDateTime;

import com.deaspider.models.Token;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TokenDTO {

    private String token;
    private LocalDateTime timestamp;
    private Object value;

    public TokenDTO(Token token) { 
        this.token = token.getToken();
        this.timestamp = token.getCreatedAt();
    }

    public Token toEntity() { 
        return Token.builder()
            .token(token)
        .build();
    }

}
