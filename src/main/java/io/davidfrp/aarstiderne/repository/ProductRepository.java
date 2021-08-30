package io.davidfrp.aarstiderne.repository;

import io.davidfrp.aarstiderne.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Product findById(long id);

    Product findByName(String name);
}
