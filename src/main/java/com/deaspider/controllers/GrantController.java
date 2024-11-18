package com.deaspider.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.deaspider.dto.GrantsDTO;
import com.deaspider.models.Grants;
import com.deaspider.services.GrantService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("grant")
@AllArgsConstructor
public class GrantController {
    
    private GrantService service;

    @PutMapping
    public ResponseEntity<GrantsDTO> create(@RequestParam String Grants)  { 
        Grants created = service.create(Grants);
        return ResponseEntity
            .ok(new GrantsDTO(created));
    }

    @PatchMapping
    public ResponseEntity<GrantsDTO> link(@RequestParam String  grant, @RequestParam String role) { 
        Grants linked = service.link(grant, role);
        return ResponseEntity
            .ok(new GrantsDTO(linked));
    }

    @GetMapping    
    public ResponseEntity<List<GrantsDTO>> getAll() { 
        return ResponseEntity
            .ok(service.getAll().stream().map(r->new GrantsDTO(r)).toList());
    }
}
