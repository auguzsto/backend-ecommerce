package io.backend.services;

import io.backend.DTO.AuthDTO;
import io.backend.DTO.UserDTO;
import io.backend.interfaces.AuthServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Service
public class AuthService implements AuthServiceImpl {

    @Autowired
    private HttpServletRequest httpServletRequest;

    @Override
    public AuthDTO login(UserDTO dto) throws IllegalArgumentException {
        List<String> list = new ArrayList<String>();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setBasicAuth(dto.getUsername(), dto.getPassword(), StandardCharsets.ISO_8859_1);
        httpHeaders.values().forEach(
                strings -> list.add(strings.get(0))
        );
        AuthDTO auth = new AuthDTO();
        auth.setAuthorization(list.get(0));
        return auth;
    }

    public void logout() throws ServletException {
        httpServletRequest.logout();
    }
}
