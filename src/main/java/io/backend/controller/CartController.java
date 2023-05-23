package io.backend.controller;

import io.backend.DTO.CartDTO;
import io.backend.DTO.PedidoDTO;
import io.backend.services.CartServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartServices cartServices;

    @PostMapping
    @CrossOrigin
    public void add(@RequestBody CartDTO dto) {
        cartServices.add(dto);
    }
}
