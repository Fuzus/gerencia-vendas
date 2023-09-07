package br.com.fuzus.domain.ports.in;

import br.com.fuzus.domain.dto.OrderDTO;
import br.com.fuzus.domain.dto.OrderProductDTO;
import br.com.fuzus.domain.entities.Order;

import java.util.List;

public interface OrderService {
    void createOrder(OrderDTO orderDto);

    void addProduct(OrderDTO OrderDto);
    void removeProduct(Long orderId, Long productId);

    List<Order> listAll();

    Order findById(Long id);

    void openOrder(OrderDTO orderDTO);

    void refoundOrder(OrderDTO orderDTO);

}
