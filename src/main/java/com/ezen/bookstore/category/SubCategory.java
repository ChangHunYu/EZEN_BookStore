package com.ezen.bookstore.category;

import jakarta.persistence.*;

@Entity
public class SubCategory {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToOne
    private MainCategory mainCategory;

    protected SubCategory() {}
}
