package ru.danilsibgatullin.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.danilsibgatullin.controllers.dto.OrderDto;
import ru.danilsibgatullin.interfaces.OrderRepository;
import ru.danilsibgatullin.interfaces.ProductRepository;
import ru.danilsibgatullin.interfaces.UserRepository;
import ru.danilsibgatullin.models.Order;
import ru.danilsibgatullin.models.OrderLineItem;
import ru.danilsibgatullin.models.Product;
import ru.danilsibgatullin.models.User;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService implements OrderInterface{

    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);

    private final OrderRepository orderRepository;

    private final CartService cartService;

    private final UserRepository userRepository;

    private final ProductRepository productRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository,
                        CartService cartService,
                        UserRepository userRepository,
                        ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.cartService = cartService;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    public List<OrderDto> findOrdersByUsername(String username) {
        return orderRepository.findAllByUsername(username).stream()
                .map(order->
                    new OrderDto(order.getId(),
                            order.getTotalPrice(),
                            order.getStatus().name(),
                            order.getOrderDate(),
                            username
                            ))
                .collect(Collectors.toList());

    }

    @Transactional
    public void createOrder(String username) {
        if (cartService.getLineItems().isEmpty()) {
            logger.info("Can't create order for empty Cart");
            return;
        }

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));


        Order order = orderRepository.save(new Order(
                null,
                LocalDateTime.now(),
                Order.OrderStatus.CREATED,
                user,
                new BigDecimal(0)
        ));

        List<OrderLineItem> orderLineItems = cartService.getLineItems()
                .stream()
                .map(li -> new OrderLineItem(
                        null,
                        order,
                        findProductById(li.getProductId()),
                        li.getProductDto().getPrice(),
                        li.getQty(),
                        li.getColor(),
                        li.getMaterial()
                ))
                .collect(Collectors.toList());
        order.setOrderLineItems(orderLineItems);
//       вычисляем общу сумму заказа
        order.setTotalPrice(orderLineItems.stream()
                .map(orderLineItem -> orderLineItem.getProduct().getCost())
                .reduce((x,y)->x.add(y)).get());


        orderRepository.save(order);
    }

    private Product findProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No product with id"));
    }
}
