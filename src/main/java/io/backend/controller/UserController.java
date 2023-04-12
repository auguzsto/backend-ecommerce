package io.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.backend.DTO.UserDTO;
import io.backend.interfaces.UserServiceImpl;
import io.backend.services.UserService;
import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("/user")
public class UserController  implements UserServiceImpl{

    @Autowired
    private UserService userService;

    @Override
    @PostMapping
    public void add(@RequestBody UserDTO dto) {
        userService.add(dto);
    }

    @Override
    @DeleteMapping("/{id}")
    public void delete(@PathParam("id") UserDTO dto) {
        userService.delete(dto);
    }
    
}
