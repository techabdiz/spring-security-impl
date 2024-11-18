package com.deaspider.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.deaspider.dto.PasswordDTO;
import com.deaspider.dto.TokenDTO;
import com.deaspider.dto.UserDTO;
import com.deaspider.exceptions.InvalidTokenException;
import com.deaspider.models.Token;
import com.deaspider.models.User;
import com.deaspider.services.TokenService;
import com.deaspider.services.UserService;
import lombok.AllArgsConstructor;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("user")
@AllArgsConstructor
public class UserController {

    private UserService service;
    private TokenService tokenService;

    @GetMapping("all")
    public ResponseEntity<List<UserDTO>> getAll() {
        return ResponseEntity
            .ok(service.getAll().stream().map(e->new UserDTO(e)).toList());
    }
    

    @GetMapping
    public ResponseEntity<UserDTO> getUser(@RequestParam String username) {
        return ResponseEntity.ok().body(new UserDTO(service.getByUsername(username)));
    }

    @PostMapping
    public ResponseEntity<TokenDTO> save(@RequestBody UserDTO user) {
        User entity = service.save(user);
        Token token = tokenService.issueNewToken((byte)5, entity.getUsername());
        TokenDTO dto = new TokenDTO(token);
        dto.setValue(new UserDTO(entity));
        return ResponseEntity
            .ok(dto);
    }

    @PatchMapping("set-password")
    public ResponseEntity<UserDTO> updatePassword(@RequestBody PasswordDTO dto) { 
        if(!dto.getPassword().equals(dto.getConfirm())) { 
           throw new InvalidTokenException("password do not match"); 
        }
        User user = service.updatePassword(dto.getPassword(), dto.getToken());
        return ResponseEntity
            .ok(new UserDTO(user));
    }
    
}
