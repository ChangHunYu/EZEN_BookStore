package com.ezen.bookstore.category;

import jakarta.persistence.*;

@Entity
public class SubCategory {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToOne(fetch = FetchType.LAZY) // FetchType.LAZY: 지연 로딩 설정
    @JoinColumn(name = "main_category_id") // 외래키 이름 설정 (DB 상의 컬럼 이름)
    private MainCategory mainCategory;

    protected SubCategory() {}

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public MainCategory getMainCategory() {
        return mainCategory;
    }

    public SubCategory(String name, MainCategory mainCategory) {
        this.name = name;
        this.mainCategory = mainCategory;
    }
}
