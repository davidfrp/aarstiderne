package io.davidfrp.aarstiderne.repository;

import io.davidfrp.aarstiderne.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAll();

    Product findById(long id);

    Product findByName(String name);

    Product removeById(long id);

    Product removeByName(String name);
}
