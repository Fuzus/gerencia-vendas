package br.com.fuzus.domain.ports.in;

import br.com.fuzus.domain.dto.ProductDTO;
import br.com.fuzus.domain.entities.Product;

import java.util.List;

public interface ProductService {
    Product createProduct(ProductDTO productDTO);
    List<Product> listProducts();

    Product findProductById(Long id);
}
