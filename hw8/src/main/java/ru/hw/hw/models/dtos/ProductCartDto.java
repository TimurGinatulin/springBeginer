package ru.hw.hw.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductCartDto {
    private Integer id;
    private String title;
    private Integer price;
    private Integer count;

    public ProductCartDto(Integer id, String title, Integer price) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.count = 1;
    }
}
