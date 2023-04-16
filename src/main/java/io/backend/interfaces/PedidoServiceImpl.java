package io.backend.interfaces;

import io.backend.DTO.PedidoDTO;

import java.util.List;

public interface PedidoServiceImpl {

    public List<PedidoDTO> all();

    public void add(PedidoDTO dto);
}
