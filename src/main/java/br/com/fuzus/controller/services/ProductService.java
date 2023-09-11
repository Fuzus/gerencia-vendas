package br.com.fuzus.controller.services;

import br.com.fuzus.model.Product;

import java.util.List;

public interface ProductService {
    List<Product> getProducts();

    Product stockWriteOff(Long productId, Integer quantity);

    Product stockRefund(Long productId, Integer quantity);
}
