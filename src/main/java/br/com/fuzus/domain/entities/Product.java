package br.com.fuzus.domain.entities;

import br.com.fuzus.domain.dto.ProductDTO;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

public class Product implements Serializable {

    private Long id;
    private String description;
    private BigDecimal price;
    private Integer stock;

    public Product(){}

    public Product(Long id, String description, BigDecimal price, Integer quantity) {
        this.id = id;
        this.description = description;
        this.price = price;
        this.stock = quantity;
    }

    public  Product(ProductDTO dto) {
        this.id = dto.id();
        this.price = dto.price();
        this.description = dto.description();
        this.stock = dto.stock();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
