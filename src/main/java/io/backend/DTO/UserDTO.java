package io.backend.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
    
    private Long id;
    private String username;
    private String password;
    private Integer vendor;
    
}
