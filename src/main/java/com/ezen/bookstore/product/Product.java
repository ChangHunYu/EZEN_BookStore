package com.ezen.bookstore.product;

import com.ezen.bookstore.category.SubCategory;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Product {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private Long price;
    private LocalDate publishDate;
    private String author;
    @Column(length = 4000) // 예시: 최대 길이를 4000으로 지정
    private String contentsInfo;
    private Long sales;
    private String publisher;
    private String imageUrl;
    @ManyToOne
    private SubCategory subCategory;

    protected Product() {}
}
