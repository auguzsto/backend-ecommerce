package io.backend.interfaces;
import java.util.Set;

import io.backend.DTO.ProductDTO;

public interface ProductServiceImpl {
    
    Set<ProductDTO> all();

    void add(ProductDTO dto);

    void delete(ProductDTO dto);

}
