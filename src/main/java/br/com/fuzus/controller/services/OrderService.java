package br.com.fuzus.controller.services;

import br.com.fuzus.model.Client;
import br.com.fuzus.model.Order;
import br.com.fuzus.model.OrderProduct;

import java.util.List;

public interface OrderService {
    List<Order> getAllOrders();
    Order detailOrder(Long id);

    Order createOrder(Client client);

    void confirmOrder(Long id);
    void refundOrder(Long id);
    void addProduct(OrderProduct orderProduct);
}
