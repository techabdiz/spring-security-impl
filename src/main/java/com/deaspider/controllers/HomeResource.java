package com.deaspider.controllers;

import org.springframework.web.bind.annotation.RestController;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("home")
public class HomeResource {

    @GetMapping
    public ResponseEntity<Map<String, String>> home() {
        return ResponseEntity
            .ok().body(Map.of("message", "welcome to the springsec play app"));
    }

    @GetMapping("admin")
    public ResponseEntity<Map<String, String>> admin() {
        return ResponseEntity
            .ok().body(Map.of("message", "welcome dear admin the springsec play app"));
    }

    @GetMapping("user")
    public ResponseEntity<Map<String, String>> user() {
        return ResponseEntity
            .ok().body(Map.of("message", "welcome dear user the springsec play app"));
    }

    @GetMapping("clerk")
    public ResponseEntity<Map<String, String>> clerk() {
        return ResponseEntity
            .ok().body(Map.of("message", "welcome dear user the springsec play app"));
    }
}
