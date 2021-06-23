package home.mvc.MVC.controller;

import home.mvc.MVC.models.Product;
import home.mvc.MVC.models.SubProd;
import home.mvc.MVC.services.ManagerProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController {
    ManagerProductService managerProductService;

    public MainController(ManagerProductService managerProductService) {
        this.managerProductService = managerProductService;
    }

    @GetMapping("/index")
    public String hello(Model model) {
        model.addAttribute("productList", managerProductService.getAllProductList());
        return "index";
    }

    @GetMapping("/product/delete/{id}")
    public String delete(@PathVariable int id) {
        managerProductService.decrementingCountProduct(id, 1);
        return "redirect:/index";
    }

    @GetMapping("/product/add/{id}")
    public String add(@PathVariable int id) {
        managerProductService.incrementingCountProduct(id, 1);
        return "redirect:/index";
    }

    @PostMapping("/product/addNew")
    public String addNew(@org.jetbrains.annotations.NotNull @ModelAttribute SubProd product) {
        managerProductService.addProduct(product.getTitle(), product.getCost());
        return "redirect:/index";
    }
}
