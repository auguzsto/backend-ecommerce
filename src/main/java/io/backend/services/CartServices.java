package io.backend.services;

import io.backend.DTO.CartDTO;
import io.backend.DTO.PedidoDTO;
import io.backend.entity.Cart;
import io.backend.entity.Pedido;
import io.backend.entity.User;
import io.backend.repository.CartRepository;
import io.backend.repository.PedidoRepository;
import io.backend.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class CartServices {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Transactional
    public void add(CartDTO dto) {
        userRepository.findById(dto.getIdUser()).map(
                user -> {
                    List<Pedido> pedidos = pedidoRepository.findAllById(dto.getIdPedido());
                    Cart cart = new Cart();
                    cart.setPedido(pedidos);
                    cart.setUser(user);
                    cartRepository.save(cart);
                    return cart;
                }
        ).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuário não encontrado")
        );

    }

}
