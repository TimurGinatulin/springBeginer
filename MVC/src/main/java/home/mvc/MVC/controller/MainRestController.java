package home.mvc.MVC.controller;

import home.mvc.MVC.models.Product;
import home.mvc.MVC.services.ManagerProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/odata")
public class MainRestController {
    @Autowired
    ManagerProductService managerProductService;

    @GetMapping("/product")
    public Product getProduct(@RequestParam int id) {
        return managerProductService.getProductById(id);
    }
    @GetMapping("/all")
    public List<Product> getProduct() {
        return managerProductService.getAllProductList();
    }
    @PostMapping("/product")
    public String addProduct(@RequestBody Product product){
        managerProductService.addProduct(product);
        return "Success.";
    }
    @DeleteMapping("/product")
    public String delProduct(@RequestParam String title){
        managerProductService.deleteProductByTitle(title);
        return "Success.";
    }
}
