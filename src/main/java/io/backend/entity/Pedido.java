package io.backend.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Data
public class Pedido {

    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToMany
    private List<Product> product;

    private LocalDateTime createdOrder;

    @ManyToOne
    private User user;

}
