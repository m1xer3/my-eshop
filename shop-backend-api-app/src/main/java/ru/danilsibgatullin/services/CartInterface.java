package ru.danilsibgatullin.services;

import ru.danilsibgatullin.controllers.dto.LineItem;
import ru.danilsibgatullin.controllers.dto.ProductDto;

import javax.sound.sampled.Line;
import java.math.BigDecimal;
import java.util.List;

public interface CartInterface {

    void addProductQty(ProductDto productDto, String color, String material, int qty);

    void removeProductQty(LineItem lineItem,Integer qty);

    void removeProduct(LineItem lineItem);

    List<LineItem> getLineItems();

    BigDecimal getSubTotal();

    void clearCart();

}
