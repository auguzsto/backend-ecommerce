package io.backend.services;

import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import io.backend.DTO.ItemDTO;
import io.backend.entity.Item;
import io.backend.interfaces.ItemServiceImpl;
import io.backend.repository.ItemRepository;
import io.backend.repository.UserRepository;

@Service
public class ItemService implements ItemServiceImpl {
    
    @Autowired
    private ItemRepository itemRepository;

	@Autowired
	private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

	@Override
	public Set<ItemDTO> all() {
		//Return list of the products
        return itemRepository.findAll()
        .stream().map(
						item -> modelMapper.map(item, ItemDTO.class)
            ).collect(Collectors.toSet());
	}

	@Override
	@Transactional
	public void add(ItemDTO dto) {
		//Get user && check if user exists.
		userRepository.findById(dto.getIdUser()).map(
				user -> {
					if(user.getVendor() == 0) {
						throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
					}

					Item item = new Item();
					item.setName(dto.getName());
					item.setPrice(dto.getPrice());
					item.setUser(user);
					itemRepository.save(item);
					return ItemDTO.class;
				}
		).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));

	
        
	}

	@Override
	public void delete(ItemDTO dto) {
		//Check if product exists.
		Item item = itemRepository.findById(dto.getId()).orElseThrow(
			() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Produto inválido.")
		);

		//Delete product.
		itemRepository.delete(item);
	}
   
}
