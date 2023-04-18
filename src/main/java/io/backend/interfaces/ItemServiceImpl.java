package io.backend.interfaces;
import java.util.Set;

import io.backend.DTO.ItemDTO;

public interface ItemServiceImpl {
    
    Set<ItemDTO> all();

    void add(ItemDTO dto);

    void delete(ItemDTO dto);

}
