package io.backend.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import io.backend.DTO.UserDTO;
import io.backend.entity.User;
import io.backend.interfaces.UserServiceImpl;
import io.backend.repository.UserRepository;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService implements UserServiceImpl{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public List<UserDTO> all(UserDTO dto) {
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example<User> user = Example.of(modelMapper.map(dto, User.class), matcher);
        return userRepository.findAll(user).stream().map(
                u -> modelMapper.map(u, UserDTO.class)
        ).collect(Collectors.toList());
    }

    @Override
    public UserDTO add(UserDTO dto) {
        //Check if e-mail already exists.
        userRepository.findByEmail(dto.getEmail()).map(
            user -> {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuário já cadastrado");
            }
        );
        
        //Create new user.
        User user = new User();
        user.setEmail(dto.getEmail());
        user.setPassword(bCryptPasswordEncoder.encode(dto.getPassword()));
        user.setNumberPhone(dto.getNumberPhone());
        user.setAddress(dto.getAddress());
        user.setVendor(dto.getVendor());
        user.setBasicToken(HttpHeaders.encodeBasicAuth(dto.getEmail(), dto.getPassword(), StandardCharsets.ISO_8859_1));
        user.setCreated_at(LocalDateTime.now());
        userRepository.save(user);
        return modelMapper.map(user, UserDTO.class);

    }

    @Override
    public void delete(UserDTO dto) {
        //Check if user exists.
        User user = userRepository.findById(dto.getId()).orElseThrow(
            () -> new ResponseStatusException(HttpStatus.BAD_REQUEST)
        );

        //Delete user.
        userRepository.delete(user);
    }
    
}
