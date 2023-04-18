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
    private List<ItemDTO> item;
    private List<UUID> idItem;
    private LocalDateTime createdOrder;
    private Double total;
    private UUID idUser;
}
