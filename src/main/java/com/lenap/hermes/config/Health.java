package com.lenap.hermes.config;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Health {

    @GetMapping("/health")
    public ResponseEntity<?> healt() {
        return ResponseEntity.ok("Hermes API OK");
    }
}
