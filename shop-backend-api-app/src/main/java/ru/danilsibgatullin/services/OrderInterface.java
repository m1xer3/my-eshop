package ru.danilsibgatullin.services;

import ru.danilsibgatullin.controllers.dto.LineItem;
import ru.danilsibgatullin.controllers.dto.OrderDto;

import java.math.BigDecimal;
import java.util.List;

public interface OrderInterface {

    List<OrderDto> getAllOrders();

    List<OrderDto> getOrderByUser(String username);

    void addOrder(LineItem lineItem,BigDecimal price);

    OrderDto viewOrder(Long id);

}
