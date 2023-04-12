package io.backend.interfaces;

import io.backend.DTO.AuthDTO;
import io.backend.DTO.UserDTO;
import org.springframework.web.bind.annotation.RequestBody;

public interface AuthServiceImpl {
    AuthDTO login(@RequestBody UserDTO dto);
}
