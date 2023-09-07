package br.com.fuzus.domain.services;

import br.com.fuzus.domain.dto.ProductDTO;
import br.com.fuzus.domain.entities.Product;
import br.com.fuzus.domain.ports.in.ProductService;
import br.com.fuzus.domain.ports.out.ProductRepository;

import java.util.List;

public class ProductServiceImp implements ProductService {

    private final ProductRepository repository;

    public ProductServiceImp(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public Product createProduct(ProductDTO productDTO) {
        Product product = new Product(productDTO);
        return this.repository.save(product);
    }

    @Override
    public List<Product> listProducts() {
        return this.repository.findAll();
    }

    @Override
    public Product findProductById(Long id) {
        return this.repository.findById(id);
    }
}
