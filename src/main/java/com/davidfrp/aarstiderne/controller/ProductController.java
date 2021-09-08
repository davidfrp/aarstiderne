package com.davidfrp.aarstiderne.controller;

import com.davidfrp.aarstiderne.model.Product;
import com.davidfrp.aarstiderne.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping()
    public String getIndex() {
        return "redirect:/products";
    }

    @GetMapping("/products")
    public String getProductsPage(Model model) {
        List<Product> products = productService.getProducts();
        model.addAttribute("products", products);
        return "products";
    }

    @GetMapping("/products/{id}/remove")
    public String removeProduct(@PathVariable("id") long id, Model model) {

        Product product = productService.getProductById(id);

        if (product == null)
            return "error/404";

        productService.removeProductById(id);

        return "redirect:/products";
    }

    @GetMapping("/products/{id}/edit")
    public String editProduct(@PathVariable("id") long id, Model model) {

        Product product = productService.getProductById(id);

        if (product == null)
            return "error/404";

        model.addAttribute("product", product);
        return "editProduct";
    }

    @PostMapping("/products/{id}/edit")
    public String editProduct(@PathVariable("id") long id,
                              @Valid @ModelAttribute("product") Product product,
                              BindingResult result, Model model) {

        Product productFromDatabase = productService.getProductById(id);

        if (productFromDatabase == null)
            return "error/404";

        productFromDatabase.setName(product.getName());
        productFromDatabase.setDescription(product.getDescription());
        productFromDatabase.setPrice(product.getPrice());

        if (result.hasErrors()) {
            model.addAttribute("product", product);
            return "editProduct";
        }

        Product newlyEditedProduct = productService.saveProduct(productFromDatabase);

        if (newlyEditedProduct == null) {
            ObjectError error = new ObjectError("globalError", "Something internally prevented your product from being changed. Try again later.");
            result.addError(error);
            return "editProduct";
        }

        return "redirect:/products";
    }

    @GetMapping("/products/add")
    public String addProduct(Model model) {
        model.addAttribute("product", new Product("", "", 0));
        return "addProduct";
    }

    @PostMapping("/products/add")
    public String addProduct(@Valid @ModelAttribute("product") Product product,
                             BindingResult result, Model model) {

        if (result.hasErrors()) {
            model.addAttribute("product", product);
            return "addProduct";
        }

        Product newlyAddedProduct = productService.saveProduct(product);

        if (newlyAddedProduct == null) {
            ObjectError error = new ObjectError("globalError", "Something internally prevented your product from being added. Try again later.");
            result.addError(error);
            return "addProduct";
        }

        return "redirect:/products";
    }
}
