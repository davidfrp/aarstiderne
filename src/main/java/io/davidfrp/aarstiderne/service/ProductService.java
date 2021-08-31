package io.davidfrp.aarstiderne.service;

import io.davidfrp.aarstiderne.model.Product;
import io.davidfrp.aarstiderne.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void removeProduct(Product product) {
        productRepository.delete(product);
    }
}
