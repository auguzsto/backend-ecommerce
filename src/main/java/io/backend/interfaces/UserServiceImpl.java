package io.backend.interfaces;

import io.backend.DTO.UserDTO;

public interface UserServiceImpl {
    
    void add(UserDTO dto);

    void delete(UserDTO dto);
    
}
