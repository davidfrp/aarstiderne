package com.davidfrp.aarstiderne.service;

import com.davidfrp.aarstiderne.model.Product;
import com.davidfrp.aarstiderne.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product createProduct(String name, String description, int price) {
        return productRepository.save(new Product(name, description, price));
    }

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    public Product getProductById(long id) {
        return productRepository.findById(id);
    }

    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    public void removeProductById(long id) {
        productRepository.deleteById(id);
    }
}
