package com.springboot.JobPortal.controller;

import com.springboot.JobPortal.dto.TokenDto;
import com.springboot.JobPortal.dto.RegisterReqDto;
import com.springboot.JobPortal.service.AuthService;
import com.springboot.JobPortal.utility.JwtUtility;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final JwtUtility jwtUtility;
    @PostMapping("/register")
    public void register(@Valid @RequestBody RegisterReqDto dto) {
        authService.register(dto);
    }
    @PostMapping("/login")
    public TokenDto login(Principal principal){
        String username = principal.getName();
        String token = jwtUtility.generateToken(username);
        return new TokenDto(username, token);
    }
}