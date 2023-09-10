package br.com.fuzus.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private Long id;
    private LocalDateTime date;
    private Client client;
    private BigDecimal totalValue;
    private Status status;
    private List<OrderProduct> purchasedProducts;

    public Order() {
        this.purchasedProducts = new ArrayList<>();
    }

    public Order(Long id, LocalDateTime date, Client client, BigDecimal totalValue, Status status, List<OrderProduct> purchasedProducts) {
        this.id = id;
        this.date = date;
        this.client = client;
        this.totalValue = totalValue;
        this.status = status;
        this.purchasedProducts = purchasedProducts;
    }
    public Order(Long id, LocalDateTime date, Client client, BigDecimal totalValue, Status status) {
        this.id = id;
        this.date = date;
        this.client = client;
        this.totalValue = totalValue;
        this.status = status;
        this.purchasedProducts = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public BigDecimal getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(BigDecimal totalValue) {
        this.totalValue = totalValue;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<OrderProduct> getPurchasedProducts() {
        return purchasedProducts;
    }

    public void setPurchasedProducts(List<OrderProduct> purchasedProducts) {
        this.purchasedProducts = purchasedProducts;
    }
}
