package com.deaspider.services;

import java.util.List;
import org.springframework.stereotype.Service;
import com.deaspider.dto.UserDTO;
import com.deaspider.exceptions.DuplicateResourceCreationException;
import com.deaspider.exceptions.InvalidTokenException;
import com.deaspider.exceptions.UserNotFoundException;
import com.deaspider.models.Token;
import com.deaspider.models.User;
import com.deaspider.repos.UserRepo;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {
    
    private TokenService tokenService;
    private UserRepo repo;

    public User getByUsername(String username){ 
        return repo.findByUsername(username)
            .orElseThrow(()-> new UserNotFoundException("user not found with username: " + username));
    }

    public User save(UserDTO dto) { 
        if(repo.existsByUsername(dto.getUsername())) { 
            throw new DuplicateResourceCreationException("user already exists with username: " + dto.getUsername());
        }

        User user = dto.toEntity();
        user = repo.save(user);
        return user;
    }

    public List<User> getAll() { 
        return repo.findAll();
    }

    public User updatePassword(String password, String token) { 
        Token tk = tokenService.findToken(token)
            .orElseThrow(()-> new InvalidTokenException("invalid token"));

        if(!tk.isValid()) { 
            throw new InvalidTokenException("token expired");
        }
        User user = tk.getUser();
        user.setPassword(password);
        repo.save(user);
        return user;    
    }  
}
