package ru.hw.hw.controller.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import ru.hw.hw.exception.NotFoundException;
import ru.hw.hw.models.dtos.ProductDto;
import ru.hw.hw.repository.specifications.ProductSpecifications;
import ru.hw.hw.service.UserService;
import ru.hw.hw.service.ProductService;


@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class RestIndexController {
    @Autowired
    private ProductService productService;
    @Autowired
    private UserService userDetailsService;

    @GetMapping
    public Page<ProductDto> findAllProducts(
            @RequestParam MultiValueMap<String, String> params,
            @RequestParam(name = "p", defaultValue = "1") Integer page
    ) {
        return productService.findAll(ProductSpecifications.build(params), page, 2);
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