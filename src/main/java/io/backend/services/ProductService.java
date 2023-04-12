package io.backend.services;

import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import io.backend.DTO.ProductDTO;
import io.backend.entity.Product;
import io.backend.entity.User;
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
	public void add(ProductDTO dto) {
		//Get user && check if user exists.
		User user = userRepository.findById(dto.getIdUser()).orElseThrow(
			() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário inválido.")
		);
		
		//Check if user vendor.
		if(user.getVendor() != 0) {

			Product product = new Product();
			product.setName(dto.getName());
			product.setDescription(dto.getDescription());
			product.setPrice(dto.getPrice());
			product.setPriceOffer(dto.getPriceOffer());
			product.setUser(user);
			productRepository.save(product);
			throw new ResponseStatusException(HttpStatus.ACCEPTED);
			
		}

		//Case user don't vendor.
		throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Vendedor inválido");

	
        
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
