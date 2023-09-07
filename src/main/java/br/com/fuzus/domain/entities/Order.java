package br.com.fuzus.domain.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Order implements Serializable {

    private Long id;
    private LocalDateTime date;
    private Client client;
    private BigDecimal total;
    private Status status;
    private final List<OrderProduct> products;

    public Order() {
        this.products = new ArrayList<>();
    }

    public Order(Long id, LocalDateTime date, Client client, Status status, List<OrderProduct> products) {
        this.id = id;
        this.date = date;
        this.client = client;
        this.status = status;
        this.products = products;
        calculateTotal();
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

    public BigDecimal getTotal() {
        return total;
    }

    public void calculateTotal() {
        this.total = BigDecimal.valueOf(
                getProducts()
                        .stream()
                        .map(OrderProduct::getTotalPrice)
                        .mapToDouble(BigDecimal::doubleValue)
                        .sum()
        );
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<OrderProduct> getProducts() {
        return products;
    }
}
