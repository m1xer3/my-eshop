package ru.danilsibgatullin.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.danilsibgatullin.models.Order;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Long> {

    @Query("select o "+
            "from Order o " +
            "inner join o.user u " +
            "where u.username = :username "+
            "group by o.id")
    List<Order> findAllByUsername(String username);
}
