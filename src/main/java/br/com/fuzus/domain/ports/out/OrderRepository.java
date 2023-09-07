package br.com.fuzus.domain.ports.out;

import br.com.fuzus.domain.entities.Order;
import br.com.fuzus.domain.entities.OrderProduct;
import br.com.fuzus.domain.entities.Status;

import java.util.List;

public interface OrderRepository {

    Order createOrder(Order order);

    List<Order> findAll();

    Order findById(Long id);

    Order addProduct(Order order);

    void removeProduct(Long orderId, Long productId);

    void changeStatus(Long orderId, Status status);

}
