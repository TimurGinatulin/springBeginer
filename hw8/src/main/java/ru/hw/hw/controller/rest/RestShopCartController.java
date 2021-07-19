package ru.hw.hw.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.hw.hw.models.dtos.ProductDto;
import ru.hw.hw.models.dtos.ResponseDto;
import ru.hw.hw.service.CartService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/shop_cart")
public class RestShopCartController {
    @Autowired
    private CartService service;

    @GetMapping
    public Map<ProductDto, Integer> getProductList(
            @RequestParam(name = "id", required = false) Integer id,
            @RequestParam(name = "title", required = false) String title
    ) {
        Map<ProductDto, Integer> response = new HashMap<>();
        if (id != null)
            response = service.getProductById(id);
        if (title != null)
            response.putAll(service.getProductByTitle(title));
        else
            response.putAll(service.getShopCart());
        return response;
    }

    @PostMapping
    public ProductDto addProduct(@RequestBody ProductDto productDto) {
        return service.addProductToShopCart(productDto);
    }

    @PutMapping("/{id}")
    public Map<ProductDto, Integer> updateProductCount(
            @PathVariable Integer id, @RequestParam(name = "new_count") Integer count) {
        return service.updateCount(id, count);
    }

    @DeleteMapping("/{id}")
    public ResponseDto deleteProduct(@PathVariable Integer id) {
        service.deleteProduct(id);
        return new ResponseDto(HttpStatus.OK.value(), "Deleted");
    }
}
