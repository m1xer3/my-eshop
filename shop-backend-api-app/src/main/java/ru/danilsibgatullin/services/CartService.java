package ru.danilsibgatullin.services;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import ru.danilsibgatullin.controllers.dto.LineItem;
import ru.danilsibgatullin.controllers.dto.ProductDto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Scope(scopeName = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class CartService implements CartInterface{

    private final Map<LineItem,Integer> lineItems = new HashMap<>();

    @Override
    public void addProductQty(ProductDto productDto, String color, String material, int qty) {
        LineItem lineItem = new LineItem(productDto,color,material);
        lineItems.put(lineItem,lineItems.getOrDefault(lineItem.getQty(),0)+qty);

    }

    @Override
    public void removeProductQty(ProductDto productDto, String color, String material, int qty) {
        LineItem lineItem = new LineItem(productDto,color,material);
        if(lineItems.get(lineItem)-qty>0){
            lineItems.replace(lineItem,lineItems.get(lineItem)-qty);
        }else{
            removeProduct(productDto,color,material);
        }
    }

    @Override
    public void removeProduct(ProductDto productDto, String color, String material) {
        LineItem lineItem = new LineItem(productDto,color,material);
        lineItems.remove(lineItem);
    }

    @Override
    public List<LineItem> getLineItems() {
        lineItems.forEach(LineItem::setQty);
        return new ArrayList<>(lineItems.keySet());
    }

    @Override
    public BigDecimal getSubTotal() {
        lineItems.forEach(LineItem::setQty);
        return lineItems.keySet()
                .stream().map(LineItem::getItemTotal)
                .reduce(BigDecimal.ZERO,BigDecimal::add);
    }
}
