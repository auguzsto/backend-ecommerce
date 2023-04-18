package io.backend.controller;

import io.backend.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.backend.DTO.UserDTO;
import io.backend.interfaces.UserServiceImpl;
import io.backend.services.UserService;
import jakarta.websocket.server.PathParam;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController  implements UserServiceImpl{

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public UserDTO findById(@PathParam("id") UserDTO dto) {
        return userService.findById(dto);
    }

    @Override
    @PostMapping
    public UserDTO add(@RequestBody UserDTO dto) {
        return userService.add(dto);
    }

    @Override
    @DeleteMapping("/{id}")
    public void delete(@PathParam("id") UserDTO dto) {
        userService.delete(dto);
    }
    
}
