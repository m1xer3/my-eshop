package ru.danilsibgatullin.services;

import ru.danilsibgatullin.controllers.dto.LineItem;
import ru.danilsibgatullin.controllers.dto.ProductDto;

import java.math.BigDecimal;
import java.util.List;

public interface CartInterface {

    void addProductQty(ProductDto productDto, String color, String material, int qty);

    void removeProductQty(ProductDto productDto, String color, String material, int qty);

    void removeProduct(ProductDto productDto, String color, String material);

    List<LineItem> getLineItems();

    BigDecimal getSubTotal();

}
