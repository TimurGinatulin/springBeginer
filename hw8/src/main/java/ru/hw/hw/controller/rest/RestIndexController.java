package ru.hw.hw.controller.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import ru.hw.hw.exception.NotFoundException;
import ru.hw.hw.models.dtos.ProductDto;
import ru.hw.hw.service.ProductService;


@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class RestIndexController {
    @Autowired
    private ProductService productService;

    @GetMapping
    public Page<ProductDto> findAllProducts(
            @RequestParam(name = "min_price", defaultValue = "0") Integer minPrice,
            @RequestParam(name = "max_price", required = false) Integer maxPrice,
            @RequestParam(name = "title", required = false) String name,
            @RequestParam MultiValueMap<String, String> params,
            @RequestParam(name = "p", defaultValue = "1") Integer page
    ) {
        if (name != null)
            return productService
                    .findByNameLike(page, 2, name);
        if (maxPrice == null)
            maxPrice = Integer.MAX_VALUE;
        return productService
                .findByCostBetween(page, 2, minPrice, maxPrice);
    }

    @GetMapping("/{id}")
    public ProductDto getProductById(@PathVariable Integer id) {
        return productService.findById(id).orElseThrow(() -> new NotFoundException("NotFound"));
    }

    @PostMapping
    public ProductDto addProduct(@RequestBody ProductDto product) {
        return productService.save(product);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable int id) {
        productService.delete(id);
    }

    @PutMapping("/{id}")
    public ProductDto updateProduct(
            @PathVariable Integer id,
            @RequestParam(name = "new_title", required = false) String name,
            @RequestParam(name = "new_cost", required = false) Integer cost) {
        return productService.update(id, name, cost);
    }
}