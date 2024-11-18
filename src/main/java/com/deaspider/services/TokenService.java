package com.deaspider.services;

import java.util.Objects;
import java.util.Optional;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.deaspider.exceptions.UserNotFoundException;
import com.deaspider.models.Token;
import com.deaspider.models.User;
import com.deaspider.repos.TokenRepo;
import com.deaspider.repos.UserRepo;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TokenService {

    private TokenRepo repo;
    private UserRepo userRepo;

    public Token issueNewToken(byte validMins, String username) { 
        String tok = getSaltString();
        while (validToken(tok)) {
            tok = getSaltString();
        } // look till we generate an invalid/fresh token
        User tokenUser = userRepo.findByUsername(username)
            .orElseThrow(()->new UserNotFoundException("user not found with username: "+ username));
        Token token = Token.builder()
            .user(tokenUser)
            .token(tok)
            .validity(validMins)        
        .build();
        token = repo.save(token);
        return token;
    }

    public Optional<Token> findToken(String token) { 
        return repo.findByToken(token);
    }
    
    public boolean validToken(String token) { 
        return repo.findByToken(token)
            .map(t->{
                boolean ret = false;
                if(Objects.nonNull(t) && t.isValid()) { 
                    ret = true;
                }
                return ret;
            }).orElse(false);
    }

    private String getSaltString() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 18) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;
    }
}
