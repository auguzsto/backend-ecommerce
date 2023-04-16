package io.backend.services;

import io.backend.DTO.PedidoDTO;
import io.backend.entity.Pedido;
import io.backend.entity.Product;
import io.backend.interfaces.PedidoServiceImpl;
import io.backend.repository.PedidoRepository;
import io.backend.repository.ProductRepository;
import io.backend.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PedidoService implements PedidoServiceImpl {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<PedidoDTO> all() {
        return pedidoRepository.findAll().stream().map(
                pedido -> modelMapper.map(pedido, PedidoDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void add(PedidoDTO dto) {
        //Check if user are already.
        userRepository.findById(dto.getIdUser()).map(
                user -> {
                    //Creating order.
                    List<UUID> idProduct = new ArrayList<UUID>();
                    idProduct.addAll(dto.getIdProduct());
                    List<Product> products = productRepository.findAllById(idProduct);
                    Pedido pedido = new Pedido();
                    pedido.setProduct(products);
                    pedido.setCreatedOrder(LocalDateTime.now());
                    pedido.setUser(user);
                    pedidoRepository.save(pedido);
                    idProduct.clear();
                    return pedido;
                }
                )
                //Case error.
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuário inválido")
                );
    }
}
