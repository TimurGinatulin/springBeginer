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

}
