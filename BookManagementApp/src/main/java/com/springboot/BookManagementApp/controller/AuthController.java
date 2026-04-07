package com.springboot.BookManagementApp.controller;

import com.springboot.BookManagementApp.dto.AuthorSignUpDto;
import com.springboot.BookManagementApp.model.Author;
import com.springboot.BookManagementApp.service.AuthorService;
import com.springboot.BookManagementApp.utility.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final JwtUtil jwtUtility;
    private final AuthorService authorService;

    @GetMapping("/login")
    public ResponseEntity<?> login(Principal principal){
        String loggedInUser = principal.getName();
        Map<String,String> map = new HashMap<>();
        map.put("token",jwtUtility.generateToken(loggedInUser));
        return ResponseEntity.status(HttpStatus.OK).body(map);
    }

    @PostMapping("/author/signup")
    public ResponseEntity<?> authorSignUp(@RequestBody AuthorSignUpDto authorSignUpDto){

        authorService.authorSignUp(authorSignUpDto);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }





}
