package ru.danilsibgatullin.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.danilsibgatullin.models.Order;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Long> {

    List<Order> getOrderByUser(String username);

}
