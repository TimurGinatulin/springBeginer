package ru.hw.hw.controller.rest;

import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.hw.hw.models.Product;
import ru.hw.hw.repository.ProductRepository;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class MainRestController {
    @Autowired
    private ProductRepository repository;

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Integer id) {
        return repository.findById(id).get();
    }

    @GetMapping
    public List<Product> getAllProduct() {
        return repository.findAll();
    }

    @PostMapping
    public Product addProduct(@ModelAttribute Product product) {
        return repository.save(product);
    }

    @GetMapping("/delete/{id}")
    public void deleteProduct(@PathVariable int id) {
        repository.delete(getProductById(id));
    }

    @GetMapping("/lower/{max}")
    public List<Product> getLowerThenPrice(@PathVariable Integer max) {
        List<Product> product = null;
        try {
            product = repository
                    .findByCostLessThan(max)
                    .orElseThrow(() -> new NotFoundException("Product not found"));
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
        return product;
    }

    @GetMapping("/greater/{max}")
    public List<Product> getGreaterThenPrice(@PathVariable Integer min) {
        List<Product> product = null;
        try {
            product = repository
                    .findByCostGreaterThan(min)
                    .orElseThrow(() -> new NotFoundException("Product not found"));
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
        return product;
    }

    @GetMapping("/between")
    public List<Product> getGreaterThenPrice(@RequestParam Integer min, @RequestParam Integer max) {
        List<Product> product = null;
        try {
            product = repository
                    .findByCostBetween(min, max)
                    .orElseThrow(() -> new NotFoundException("Product not found"));
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
        return product;
    }

    @GetMapping("/find/{name}")
    public List<Product> findByNameLike(@PathVariable String name) {
        List<Product> product = null;
        String query = "%" + name + "%";
        try {
            product = repository
                    .findByNameLike(query)
                    .orElseThrow(() -> new NotFoundException("Product not found"));
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
        return product;
    }
}