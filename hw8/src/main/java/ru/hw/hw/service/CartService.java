package ru.hw.hw.service;

import org.springframework.stereotype.Service;
import ru.hw.hw.exception.NotFoundException;
import ru.hw.hw.models.dtos.ProductDto;

import java.util.HashMap;
import java.util.Map;

@Service
public class CartService {
    private final Map<ProductDto, Integer> shopCart = new HashMap<>();

    public Map<ProductDto, Integer> getShopCart() {
        return shopCart;
    }

    public Map<ProductDto, Integer> getProductByTitle(String title) {
        Map<ProductDto, Integer> find = new HashMap<>();
        for (Map.Entry<ProductDto, Integer> entry : shopCart.entrySet()) {
            if (entry.getKey().getTitle().toLowerCase().equals(title))
                find.put(entry.getKey(), entry.getValue());
        }
        return find;
    }

    public Map<ProductDto, Integer> getProductById(Integer id) {
        Map<ProductDto, Integer> find = new HashMap<>();
        for (Map.Entry<ProductDto, Integer> entry : shopCart.entrySet()) {
            if (entry.getKey().getId().equals(id))
                find.put(entry.getKey(), entry.getValue());
        }
        return find;
    }

    public ProductDto addProductToShopCart(ProductDto productDto) {
        if (shopCart.containsKey(productDto))
            shopCart.replace(productDto, shopCart.get(productDto) + 1);
        else
            shopCart.put(productDto, 1);
        return productDto;
    }

    public Map<ProductDto, Integer> updateCount(Integer id, Integer count) {
        Map<ProductDto, Integer> response = getProductById(id);
        for (Map.Entry<ProductDto, Integer> entry : response.entrySet())
            shopCart.replace(entry.getKey(), count);
        return response;
    }

    public void deleteProduct(Integer id) {
        boolean flag = true;
        for (Map.Entry<ProductDto, Integer> entry : shopCart.entrySet()) {
            if (entry.getKey().getId().equals(id)) {
                shopCart.remove(entry.getKey());
                flag = false;
            }
        }
        if (flag)
            throw new NotFoundException("Not Found");
    }
}
