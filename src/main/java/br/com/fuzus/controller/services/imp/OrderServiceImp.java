package br.com.fuzus.controller.services.imp;

import br.com.fuzus.controller.db.OrderDAO;
import br.com.fuzus.controller.services.OrderService;
import br.com.fuzus.controller.services.ProductService;
import br.com.fuzus.model.Client;
import br.com.fuzus.model.Order;
import br.com.fuzus.model.OrderProduct;
import br.com.fuzus.model.Status;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class OrderServiceImp implements OrderService {

    private OrderDAO orderDAO;
    private Order order;
    private ProductService productService;

    public OrderServiceImp(ProductService productService) {
        this.orderDAO = new OrderDAO();
        this.order = new Order();
        this.productService = productService;
    }

    @Override
    public List<Order> getAllOrders() {
        return orderDAO.getAllOrders();
    }

    @Override
    public Order detailOrder(Long id) {
        return orderDAO.getDetailedOrder(id);
    }

    @Override
    public Order createOrder(Client client) {
        order.setDate(LocalDateTime.now());
        order.setClient(client);
        order.setStatus(Status.DRAFT);
        var totalPrice = order.getPurchasedProducts().stream().map(OrderProduct::getTotal).mapToDouble(BigDecimal::doubleValue).sum();
        order.setTotalValue(BigDecimal.valueOf(totalPrice));
        var id = orderDAO.createOrder(order);
        order.setId(id);
        return order;
    }

    @Override
    public void confirmOrder(Long id) {
        var findedOrder = orderDAO.getDetailedOrder(id);
        orderDAO.updateStatus(id, Status.CONFIRMED);
        findedOrder.getPurchasedProducts().forEach(x -> productService.stockWriteOff(x.getProductId(), x.getQuantity()));
    }

    @Override
    public void refundOrder(Long id) {
        var findedOrder = orderDAO.getDetailedOrder(id);
        orderDAO.updateStatus(id, Status.REFUNDED);
        findedOrder.getPurchasedProducts().forEach(x -> productService.stockRefund(x.getProductId(), x.getQuantity()));
    }

    @Override
    public void addProduct(OrderProduct orderProduct) {
        var productOptional = order.getPurchasedProducts().stream().filter(x -> x.getProductId().equals(orderProduct.getProductId())).findAny();
        if (productOptional.isPresent()){
            var product = productOptional.get();
            product.setQuantity(product.getQuantity() + orderProduct.getQuantity());
        } else {
            order.getPurchasedProducts().add(orderProduct);
        }
    }
}
