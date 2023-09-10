package br.com.fuzus.model;

import java.math.BigDecimal;

public class OrderProduct {

    private Long productId;

    private String productDescription;
    private Integer quantity;
    private BigDecimal price;
    private BigDecimal total;

    public OrderProduct(){}

    public OrderProduct(Product product, Integer quantity){
        this.productId = product.getId();
        this.productDescription = product.getDescription();
        this.price = product.getPrice();
        this.quantity = quantity;
        setTotal();
    }
    public OrderProduct(Long productId, String productDescription, Integer quantity, BigDecimal price) {
        this.productId = productId;
        this.productDescription = productDescription;
        this.quantity = quantity;
        this.price = price;
        setTotal();
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
        if (price != null) {
            setTotal();
        }
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
        if (this.quantity != null){
            setTotal();
        }
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal() {
       this.total = this.price.multiply(new BigDecimal(this.quantity));
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "OrderProduct{" +
                "productId=" + productId +
                ", quantity=" + quantity +
                ", price=" + price +
                ", total=" + total +
                '}';
    }
}
