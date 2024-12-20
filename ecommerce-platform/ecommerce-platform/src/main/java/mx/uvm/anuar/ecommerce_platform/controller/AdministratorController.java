package mx.uvm.anuar.ecommerce_platform.controller;

import mx.uvm.anuar.ecommerce_platform.model.Product;
import mx.uvm.anuar.ecommerce_platform.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/administrator")
public class AdministratorController {

    @Autowired
    private ProductService productService;

    @GetMapping("")
    public String home(Model model) {

        List<Product> products = productService.findAll();
        model.addAttribute("products", products);

        return "administrator/home";
    }

}
