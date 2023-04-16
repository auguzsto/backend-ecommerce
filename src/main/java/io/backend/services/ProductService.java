package io.backend.services;

import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import io.backend.DTO.ProductDTO;
import io.backend.entity.Product;
import io.backend.interfaces.ProductServiceImpl;
import io.backend.repository.ProductRepository;
import io.backend.repository.UserRepository;

@Service
public class ProductService implements ProductServiceImpl {
    
    @Autowired
    private ProductRepository productRepository;

	@Autowired
	private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

	@Override
	public Set<ProductDTO> all() {
		//Return list of the products
        return productRepository.findAll()
        .stream().map(
            product -> modelMapper.map(product, ProductDTO.class)
            ).collect(Collectors.toSet());
	}

	@Override
	@Transactional
	public void add(ProductDTO dto) {
		//Get user && check if user exists.
		userRepository.findById(dto.getIdUser()).map(
				user -> {
					if(user.getVendor() == 0) {
						throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
					}

					Product product = new Product();
					product.setName(dto.getName());
					product.setPrice(dto.getPrice());
					product.setUser(user);
					productRepository.save(product);
					return ProductDTO.class;
				}
		).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));

	
        
	}

	@Override
	public void delete(ProductDTO dto) {
		//Check if product exists.
		Product product = productRepository.findById(dto.getId()).orElseThrow(
			() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Produto inválido.")
		);

		//Delete product.
		productRepository.delete(product);
	}
   
}
