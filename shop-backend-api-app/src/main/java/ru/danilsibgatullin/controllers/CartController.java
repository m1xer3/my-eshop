package ru.danilsibgatullin.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.danilsibgatullin.controllers.dto.AddLineItemDto;
import ru.danilsibgatullin.controllers.dto.AllCartDto;
import ru.danilsibgatullin.controllers.dto.LineItem;
import ru.danilsibgatullin.controllers.dto.ProductDto;
import ru.danilsibgatullin.services.CartInterface;
import ru.danilsibgatullin.services.ProductInterface;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    private static final Logger logger = LoggerFactory.getLogger(CartController.class);

    private final CartInterface cartService;

    private final ProductInterface productService;

    @Autowired
    public CartController(CartInterface cartService, ProductInterface productService) {
        this.cartService = cartService;
        this.productService = productService;
    }

    @PostMapping(produces = "application/json", consumes = "application/json")
    public List<LineItem> addToCart(@RequestBody AddLineItemDto addLineItemDto) {
        logger.info("New LineItem. ProductId = {}, qty = {}", addLineItemDto.getProductId(), addLineItemDto.getQty());

        ProductDto productDto = productService.findById(addLineItemDto.getProductId())
                .orElseThrow(RuntimeException::new);
        cartService.addProductQty(productDto, addLineItemDto.getColor(), addLineItemDto.getMaterial(), addLineItemDto.getQty());
        return cartService.getLineItems();
    }

    @GetMapping("/all")
    public AllCartDto findAll() {
        return new AllCartDto(cartService.getLineItems(), cartService.getSubTotal());
    }

    @PostMapping("/delete")
    public void removeFromCart(@RequestBody LineItem lineItem){
        cartService.removeProduct(lineItem);
    }

    @PostMapping("/decries")
    public void decriesQty(@RequestBody LineItem lineItem){
        cartService.removeProductQty(lineItem, lineItem.getQty());
    }

    @PostMapping("/deleteAll")
    public void clearCart(){
        cartService.clearCart();
    };

}
