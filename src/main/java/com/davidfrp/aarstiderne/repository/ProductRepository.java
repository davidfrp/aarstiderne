package com.davidfrp.aarstiderne.repository;

import com.davidfrp.aarstiderne.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Product findById(long id);

    List<Product> findAll();
}
