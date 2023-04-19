package io.backend.interfaces;
import java.util.List;
import java.util.Set;

import io.backend.DTO.ItemDTO;

public interface ItemServiceImpl {
    
    List<ItemDTO> all(ItemDTO dto);

    void add(ItemDTO dto);

    void delete(ItemDTO dto);

}
