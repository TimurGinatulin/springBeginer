package ru.hw.hw.models.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.hw.hw.models.entitys.ProductEntity;

@Data
@NoArgsConstructor
public class ProductDto {
    private Integer id;
    private String title;
    private int price;
    private String category;

    public ProductDto(ProductEntity p) {
        this.id = p.getId();
        this.title = p.getName();
        this.price = p.getCost();
        this.category = p.getCategory().getName();
    }
}
