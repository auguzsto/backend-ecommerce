package io.backend.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.backend.DTO.ProductDTO;
import io.backend.interfaces.ProductServiceImpl;
import io.backend.services.ProductService;
import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("/product")
public class ProductController implements ProductServiceImpl {

    @Autowired
    private ProductService productService;

	@Override
    @GetMapping
	public Set<ProductDTO> all() {
		return productService.all();
	}

	@Override
    @PostMapping
	public void add(@RequestBody ProductDTO dto) {
		productService.add(dto);
	}

	@Override
    @DeleteMapping("/{id}")
	public void delete(@PathParam("id") ProductDTO dto) {
		productService.delete(dto);
	}
    
}
