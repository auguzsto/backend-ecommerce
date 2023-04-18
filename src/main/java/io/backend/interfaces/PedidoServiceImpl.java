package io.backend.interfaces;

import io.backend.DTO.PedidoDTO;
import io.backend.DTO.UserDTO;

import java.util.List;
import java.util.UUID;

public interface PedidoServiceImpl {

    public List<PedidoDTO> all();

    public void add(PedidoDTO dto);

    public PedidoDTO findById(PedidoDTO dto);

}
