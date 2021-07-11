package ru.hw.hw.controller.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.*;
import ru.hw.hw.models.Product;
import ru.hw.hw.repository.ProductRepository;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class RestIndexController {
    @Autowired
    private ProductRepository repository;

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Integer id) {
        return repository.findById(id).get();
    }

    @PostMapping
    public Product addProduct(@RequestBody Product product) {
        return repository.save(product);
    }

    @CrossOrigin
    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable int id) {
        repository.delete(getProductById(id));
    }

    @GetMapping
    public List<Product> findAllProducts(
            @RequestParam(name = "min_price", defaultValue = "0") Integer minPrice,
            @RequestParam(name = "max_price", required = false) Integer maxPrice,
            @RequestParam(name = "title", required = false) String name
    ) {
        if (name != null)
            return repository
                    .findByNameLike("%" + name + "%")
                    .orElseThrow(() -> new ru.hw.hw.exception.NotFoundException("Not found"));
        if (maxPrice == null)
            maxPrice = Integer.MAX_VALUE;
        return repository
                .findByCostBetween(minPrice, maxPrice)
                .orElseThrow(() -> new ru.hw.hw.exception.NotFoundException(""));
    }

    @CrossOrigin
    @PostMapping("/{id}")
    public Product updateProduct(
            @PathVariable Integer id,
            @RequestParam(name = "new_title", required = false) String name,
            @RequestParam(name = "new_cost", required = false) Integer cost) {
        Product product = repository.getById(id);
        if (name != null)
            product.setName(name);
        if (cost != null)
            product.setCost(cost);
        return repository.save(product);
    }
}