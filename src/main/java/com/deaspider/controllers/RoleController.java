package com.deaspider.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.deaspider.dto.RoleDTO;
import com.deaspider.models.Role;
import com.deaspider.services.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("role")
@AllArgsConstructor
public class RoleController {

    private RoleService service;

    @PutMapping
    public ResponseEntity<RoleDTO> create(@RequestParam String role)  { 
        Role created = service.create(role);
        return ResponseEntity
            .ok(new RoleDTO(created));
    }

    @PatchMapping
    public ResponseEntity<RoleDTO> link(@RequestParam String  role, @RequestParam String user) { 
        Role linked = service.link(role, user);
        return ResponseEntity
            .ok(new RoleDTO(linked));
    }

    @GetMapping    
    public ResponseEntity<List<RoleDTO>> getAll() { 
        return ResponseEntity
            .ok(service.getAll().stream().map(r->new RoleDTO(r)).toList());
    }
}
