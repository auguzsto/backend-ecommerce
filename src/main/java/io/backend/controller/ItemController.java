package io.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.backend.DTO.ItemDTO;
import io.backend.interfaces.ItemServiceImpl;
import io.backend.services.ItemService;
import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("/item")
public class ItemController implements ItemServiceImpl {

    @Autowired
    private ItemService itemService;

	@Override
    @GetMapping
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	public List<ItemDTO> all(ItemDTO dto) {
		return itemService.all(dto);
	}

	@Override
	@GetMapping("/offers")
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	public List<ItemDTO> allOffer(ItemDTO dto) {
		return itemService.allOffer(dto);
	}

	@Override
    @PostMapping
	@CrossOrigin
	public void add(@RequestBody ItemDTO dto) {
		itemService.add(dto);
	}

	@Override
    @DeleteMapping("/{id}")
	@CrossOrigin
	public void delete(@PathParam("id") ItemDTO dto) {
		itemService.delete(dto);
	}
    
}
