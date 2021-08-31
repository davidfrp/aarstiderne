package io.davidfrp.aarstiderne.controller;

import io.davidfrp.aarstiderne.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/")
    public String getIndexPage() {
        return "redirect:/products";
    }

    @GetMapping("/products/{id}")
    public String getProductsPage(@PathVariable String id, Model model) {
        var products = productService.getProducts();
        model.addAttribute("products", products);
        return "productsPage";
    }
}
