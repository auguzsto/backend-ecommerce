package io.backend.DTO;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
    
    private UUID id;
    private String email;
    private String password;
    private String address;
    private String numberPhone;
    private Integer vendor;
    private String created_at;
    private String updated_at;
    private String last_login;

    
}
