package io.backend.services;

import io.backend.DTO.AuthDTO;
import io.backend.DTO.UserDTO;
import io.backend.interfaces.AuthServiceImpl;
import io.backend.repository.UserRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthService implements AuthServiceImpl {

    @Autowired
    private HttpServletRequest httpServletRequest;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public AuthDTO login(UserDTO dto) {
        String generatorToken = HttpHeaders.encodeBasicAuth(dto.getUsername(),
                dto.getPassword(),
                StandardCharsets.ISO_8859_1);
        return userRepository.findByBasicToken(generatorToken).map(
                user -> {
                    AuthDTO auth = new AuthDTO();
                    auth.setId(user.getId());
                    auth.setUsername(user.getUsername());
                    auth.setVendor(user.getVendor());
                    auth.setBasicToken(user.getBasicToken());
                    return auth;
                }
        ).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuário ou senha inválidos")
        );
    }

    public AuthDTO checkToken(String basicToken) {
        return userRepository.findByBasicToken(basicToken).map(
                user -> modelMapper.map(user, AuthDTO.class)
        ).orElseThrow();
    }

    public void logout() throws ServletException {
        httpServletRequest.logout();
    }
}
