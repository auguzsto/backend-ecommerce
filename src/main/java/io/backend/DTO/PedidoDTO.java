package io.backend.DTO;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class PedidoDTO {

    private UUID id;
    private List<ProductDTO> product;
    private List<UUID> idProduct;
    private LocalDateTime createdOrder;
    private UUID idUser;
}
