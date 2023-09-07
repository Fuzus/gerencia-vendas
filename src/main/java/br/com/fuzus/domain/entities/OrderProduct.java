package br.com.fuzus.domain.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

public class OrderProduct implements Serializable {
    private Long productId;

    private Integer quantity;

    private BigDecimal price;

    private BigDecimal totalPrice;

    public OrderProduct(){}

    public OrderProduct(Long productId, Integer quantity, BigDecimal price, BigDecimal totalPrice) {
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
        setTotalPrice();
    }

    public OrderProduct(Product product, Integer quantity) {
        this.productId = product.getId();
        this.price = product.getPrice();
        this.quantity = quantity;
        setTotalPrice();
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
        setTotalPrice();
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    private void setTotalPrice() {
        this.totalPrice = this.price.multiply(BigDecimal.valueOf(this.quantity));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderProduct that = (OrderProduct) o;
        return Objects.equals(productId, that.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId);
    }
}
