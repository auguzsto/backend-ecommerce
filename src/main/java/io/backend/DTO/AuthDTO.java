package io.backend.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class AuthDTO {
    private UUID id;
    private String username;
    private Integer vendor;
}
