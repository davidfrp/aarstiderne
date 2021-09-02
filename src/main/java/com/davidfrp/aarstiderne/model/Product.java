package com.davidfrp.aarstiderne.model;

import com.davidfrp.aarstiderne.util.SnowflakeGenerator;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @Column(name = "id", updatable = false, nullable = false)
    @GeneratedValue(generator = SnowflakeGenerator.GENERATOR_NAME)
    @GenericGenerator(name = SnowflakeGenerator.GENERATOR_NAME, strategy = "com.davidfrp.aarstiderne.util.SnowflakeGenerator")
    private long id;

    @Size(min = 2, message = "Navnet på produktet skal være på mindst {min} tegn.")
    @Size(max = 50, message = "Navnet på produktet må ikke være længere end {max} tegn.")
    @Column(name = "name", length = 50, nullable = false)
    private String name;

    @Size(min = 2, message = "Beskrivelsen skal være på mindst {min} tegn.")
    @Size(max = 300, message = "Beskrivelsen må ikke være længere end {max} tegn.")
    @Column(name = "description", length = 300, nullable = false)
    private String description;

    @Min(value = 0, message = "Produktet skal mindst koste {value} kroner.")
    @Column(name = "price", nullable = false)
    private float price;

    protected Product() { }

    public Product(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
