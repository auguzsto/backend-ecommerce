package io.backend.controller;

import io.backend.DTO.AuthDTO;
import io.backend.DTO.UserDTO;

import io.backend.interfaces.AuthServiceImpl;
import io.backend.services.AuthService;
import jakarta.servlet.ServletException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/auth")
public class AuthController implements AuthServiceImpl {

    @Autowired
    private AuthService authService;

    @Override
    @PostMapping
    public AuthDTO login(@RequestBody UserDTO dto) throws IllegalArgumentException {
        return authService.login(dto);
    }

    @PostMapping("/logout")
    public void logout() throws ServletException {
        authService.logout();
    }
}
