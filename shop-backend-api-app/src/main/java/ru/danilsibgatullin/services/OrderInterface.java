package ru.danilsibgatullin.services;

import ru.danilsibgatullin.controllers.dto.LineItem;
import ru.danilsibgatullin.controllers.dto.OrderDto;
import ru.danilsibgatullin.models.Order;

import java.math.BigDecimal;
import java.util.List;

public interface OrderInterface {


    List<OrderDto> findOrdersByUsername(String username);

    void createOrder(String username);
}
