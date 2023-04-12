package io.backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
public class Product {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Campo nome deve ser preenchido.")
    private String name;

    private String description;

    @NotNull(message = "Campo price deve ser preenchido.")
    private Double price;

    private Double priceOffer;
    
    @ManyToOne
    private User user;
}
