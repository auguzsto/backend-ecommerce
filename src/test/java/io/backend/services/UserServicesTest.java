package io.backend.services;

import io.backend.DTO.UserDTO;
import io.backend.entity.User;
import io.backend.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@SpringBootTest
public class UserServicesTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bcryptPasswordEncoder;

    @Test
    public void addTest() {
        UserDTO user = new UserDTO();
        user.setId(UUID.randomUUID());
        user.setPassword(bcryptPasswordEncoder.encode("a551357asda87"));
        user.setAddress("Rua");
        user.setCreated_at(LocalDateTime.now().toString());
        user.setVendor(0);
        user.setEmail("test@test");
    }

    @Test
    public void allTest() {
        userRepository.findAll();
    }

    @Test
    public void delete() {

    }
}
