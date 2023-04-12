package io.backend.interfaces;

import io.backend.DTO.UserDTO;

public interface UserServiceImpl {
    
    UserDTO add(UserDTO dto);

    void delete(UserDTO dto);
    
}
