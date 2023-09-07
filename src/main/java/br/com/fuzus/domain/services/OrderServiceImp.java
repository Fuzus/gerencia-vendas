package br.com.fuzus.domain.services;

import br.com.fuzus.domain.dto.OrderDTO;
import br.com.fuzus.domain.dto.OrderProductDTO;
import br.com.fuzus.domain.entities.*;
import br.com.fuzus.domain.ports.in.OrderService;
import br.com.fuzus.domain.ports.in.ProductService;
import br.com.fuzus.domain.ports.out.ClientRepository;
import br.com.fuzus.domain.ports.out.OrderRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class OrderServiceImp implements OrderService {

    private final OrderRepository orderRepository;
    private final ClientRepository clientRepository;
    private final ProductService productService;

    public OrderServiceImp(OrderRepository orderRepository, ClientRepository clientRepository, ProductService productService) {
        this.orderRepository = orderRepository;
        this.clientRepository = clientRepository;
        this.productService = productService;
    }

    @Override
    public void createOrder(OrderDTO orderDto) {
        Client client = clientRepository.findClientById(orderDto.clientId());
        Product product = this.productService.findProductById(orderDto.orderProduct().productId());

        OrderProduct orderProduct = new OrderProduct();
        orderProduct.setProductId(product.getId());
        orderProduct.setPrice(product.getPrice());
        orderProduct.setQuantity(orderDto.orderProduct().quantity());
        orderProduct.setTotalPrice(product.getPrice().multiply(BigDecimal.valueOf(orderDto.orderProduct().quantity())));

        Order order = new Order();
        order.setClient(client);
        order.setDate(LocalDateTime.now());
        order.setStatus(Status.CREATED);
        order.getProducts().add(orderProduct);
        order.setTotal(BigDecimal.valueOf(
                order.getProducts().stream().map(OrderProduct::getTotalPrice).mapToDouble(BigDecimal::doubleValue
                ).sum()));

        orderRepository.createOrder(order);
    }

    @Override
    public void addProduct(OrderDTO orderDto) {
        Order order = orderRepository.findById(orderDto.id());

    }

    @Override
    public void removeProduct(Long id) {

    }

    @Override
    public List<Order> listAll() {
        return null;
    }

    @Override
    public Order findById(Long id) {
        return null;
    }
}
