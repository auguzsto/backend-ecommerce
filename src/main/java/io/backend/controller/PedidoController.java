package io.backend.controller;

import io.backend.DTO.PedidoDTO;
import io.backend.interfaces.PedidoServiceImpl;
import io.backend.services.PedidoService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedido")
public class PedidoController implements PedidoServiceImpl {

    @Autowired
    private PedidoService pedidoService;

    @Override
    @GetMapping
    public List<PedidoDTO> all() {
        return pedidoService.all();
    }

    @Override
    @PostMapping
    public void add(@RequestBody PedidoDTO dto) {
        pedidoService.add(dto);
    }

    @Override
    @GetMapping("/{id}")
    public PedidoDTO findById(@PathParam("id") PedidoDTO dto) {
        return pedidoService.findById(dto);
    }



}
