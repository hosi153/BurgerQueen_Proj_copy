package com.example.burgerqueen_proj.security;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TokenAPiController {

    @GetMapping("/api/token/generate")
    public ResponseEntity getToken(){

        return new ResponseEntity(HttpStatus.OK);
    }

}
