package ru.danilsibgatullin.controllers.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonTypeInfo;


@Data
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS)
@JsonIgnoreProperties(ignoreUnknown = true)
public class LineItem implements Serializable {

    private Long productId;

    private ProductDto productDto;

    private Integer qty;

    private String color;

    private String material;

    public LineItem(ProductDto productDto, String color, String material) {
        this.productId = productDto.getId();
        this.productDto = productDto;
        this.color = color;
        this.material = material;
    }

    public LineItem() {
    }

    public BigDecimal getItemTotal(){
        return productDto.getPrice().multiply(new BigDecimal(qty));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LineItem lineItem = (LineItem) o;
        return productId.equals(lineItem.productId) &&
                Objects.equals(color, lineItem.color) &&
                Objects.equals(material, lineItem.material);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, color, material);
    }

}
