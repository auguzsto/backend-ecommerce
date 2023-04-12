package io.backend.DTO;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
    
    private UUID id;
    private String username;
    private String password;
    private Integer vendor;
    
}
