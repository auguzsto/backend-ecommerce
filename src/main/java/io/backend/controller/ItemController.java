package io.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	public List<ItemDTO> all(ItemDTO dto) {
		return itemService.all(dto);
	}

	@Override
    @PostMapping
	public void add(@RequestBody ItemDTO dto) {
		itemService.add(dto);
	}

	@Override
    @DeleteMapping("/{id}")
	public void delete(@PathParam("id") ItemDTO dto) {
		itemService.delete(dto);
	}
    
}
