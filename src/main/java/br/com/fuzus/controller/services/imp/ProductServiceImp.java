package br.com.fuzus.controller.services.imp;

import br.com.fuzus.controller.db.ProductDAO;
import br.com.fuzus.controller.services.ProductService;
import br.com.fuzus.model.Product;

import java.util.List;

public class ProductServiceImp implements ProductService {

    private ProductDAO productDAO;

    public ProductServiceImp() {
        this.productDAO = new ProductDAO();
    }

    @Override
    public List<Product> getProducts() {
        return productDAO.getAllProducts();
    }

    @Override
    public Product stockWriteOff(Long productId, Integer quantity) {
        return changeStock(productId, quantity * -1);
    }

    @Override
    public Product stockRefund(Long productId, Integer quantity) {
        return changeStock(productId, quantity);
    }

    private Product changeStock(Long productId, Integer quantity) {
        var product = productDAO.getProductById(productId);
        product.setStock(product.getStock() + quantity);
        productDAO.changeStock(product);
        return product;
    }
}
