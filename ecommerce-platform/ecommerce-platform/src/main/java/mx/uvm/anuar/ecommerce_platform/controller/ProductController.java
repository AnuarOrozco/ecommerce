package mx.uvm.anuar.ecommerce_platform.controller;

import mx.uvm.anuar.ecommerce_platform.model.Product;
import mx.uvm.anuar.ecommerce_platform.model.User;
import mx.uvm.anuar.ecommerce_platform.service.ProductService;
import mx.uvm.anuar.ecommerce_platform.service.UploadFileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.text.html.Option;
import java.io.IOException;
import java.util.Optional;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductService productService;

    @Autowired
    private UploadFileService uploadFileService;

    @GetMapping("")
    public String show(Model model) {
        model.addAttribute("products", productService.findAll());
        return "products/show";
    }

    @GetMapping("/create")
    public String create() {
        return "products/create";
    }

    @PostMapping("/save")
    public String save(Product product, @RequestParam("img") MultipartFile file) throws IOException {
        LOGGER.info("Este es el objeto producto {}", product);
        User user = new User(1, "", "", "", "", "", "", "");
        product.setUser(user);

        // image
        if (product.getId() == null) { // When a product is created
            String imageName = uploadFileService.saveImage(file);
            product.setImage(imageName);
        } else {
            if (file.isEmpty()) { // When we edit the product but we dont change the img
                Product p = new Product();
                p = productService.get(product.getId()).get();
                product.setImage(p.getImage());
            } else {
                String imageName = uploadFileService.saveImage(file);
                product.setImage(imageName);
            }
        }

        productService.save(product);
        return "redirect:/products";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        Product product = new Product();
        Optional<Product> optionalProduct = productService.get(id);
        product = optionalProduct.get();

        LOGGER.info("Producto buscado: {}", product);
        model.addAttribute("product", product);

        return "products/edit";
    }

    @PostMapping("/update")
    public String update(Product product, @RequestParam("img") MultipartFile file) throws IOException {
        Product p = new Product();
        p=productService.get(product.getId()).get();

        if (file.isEmpty()) {
            product.setImage(p.getImage());
        } else {
            if (!p.getImage().equals("default.jpg")) {
                uploadFileService.deleteImage(p.getImage());
            }
            String imageName = uploadFileService.saveImage(file);
            product.setImage(imageName);
        }
        product.setUser(p.getUser());
        productService.update(product);
        return "redirect:/products";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        productService.delete(id);
        return "redirect:/products";
    }

}
