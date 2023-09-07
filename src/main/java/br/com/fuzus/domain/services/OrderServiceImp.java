package br.com.fuzus.domain.services;

import br.com.fuzus.domain.dto.OrderDTO;
import br.com.fuzus.domain.entities.*;
import br.com.fuzus.domain.ports.in.OrderService;
import br.com.fuzus.domain.ports.in.ProductService;
import br.com.fuzus.domain.ports.out.ClientRepository;
import br.com.fuzus.domain.ports.out.OrderRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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

        Order order = new Order();
        order.setClient(client);
        order.setStatus(Status.DRAFT);
        order.getProducts().add(orderProduct);
        order.calculateTotal();

        orderRepository.createOrder(order);
    }

    @Override
    public void addProduct(OrderDTO orderDto) {
        Order order = findById(orderDto.id());
        Optional<OrderProduct> orderProduct = order.getProducts().stream().filter(x -> x.getProductId().equals(orderDto.orderProduct().productId())).findAny();
        if (orderProduct.isPresent()){
            updateQuantity(orderProduct.get(), orderDto.orderProduct().quantity());
        } else {
            addProductToOrder(order, orderDto);
        }
        order.calculateTotal();
        orderRepository.addProduct(order);
    }

    private void updateQuantity(OrderProduct x, Integer quantity) {
        x.setQuantity(quantity);
    }

    private void addProductToOrder(Order order, OrderDTO orderDto) {
        Product product = productService.findProductById(orderDto.id());
        OrderProduct orderProduct = new OrderProduct(product, orderDto.orderProduct().quantity());
        order.getProducts().add(orderProduct);
    }

    @Override
    public void removeProduct(Long orderId, Long productId) {
        orderRepository.removeProduct(orderId, productId);
    }

    @Override
    public List<Order> listAll() {
        return orderRepository.findAll();
    }

    @Override
    public Order findById(Long id) {
        return orderRepository.findById(id);
    }
}
