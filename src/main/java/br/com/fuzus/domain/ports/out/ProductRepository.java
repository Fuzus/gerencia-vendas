package br.com.fuzus.domain.ports.out;

import br.com.fuzus.domain.dto.ProductDTO;
import br.com.fuzus.domain.entities.Product;

import java.util.List;

public interface ProductRepository {

    Product save(Product product);

    List<Product> findAll();

    Product findById(Long id);
}
