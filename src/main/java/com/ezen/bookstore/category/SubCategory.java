package com.ezen.bookstore.category;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class SubCategory {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    protected SubCategory() {}

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
