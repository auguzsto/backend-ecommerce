package io.backend.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import io.backend.DTO.UserDTO;
import io.backend.entity.User;
import io.backend.interfaces.UserServiceImpl;
import io.backend.repository.UserRepository;

@Service
public class UserService implements UserServiceImpl{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDTO add(UserDTO dto) {
        //Check if username already exists.
        userRepository.findByUsername(dto.getUsername()).map(
            user -> {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuário já cadastrado");
            }
        );
        
        //Create new user.
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPassword(bCryptPasswordEncoder.encode(dto.getPassword()));
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
