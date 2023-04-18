package io.backend.interfaces;

import io.backend.DTO.UserDTO;

import java.util.List;

public interface UserServiceImpl {

    public List<UserDTO> all(UserDTO dto);
    
    UserDTO add(UserDTO dto);

    void delete(UserDTO dto);
    
}
