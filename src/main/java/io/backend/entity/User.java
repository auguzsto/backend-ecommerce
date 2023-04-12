package io.backend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
public class User {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Campo usuário deve ser preenchido")
    @Column(unique = true)
    private String username;

    @NotNull(message = "Campo senha deve ser preenchido")
    private String password;

    private Integer vendor;
    
}
