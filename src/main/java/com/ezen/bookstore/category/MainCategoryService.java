package com.ezen.bookstore.category;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MainCategoryService {
    private final MainCategoryRepository mainCategoryRepository;

    public MainCategoryService(MainCategoryRepository mainCategoryRepository) {
        this.mainCategoryRepository = mainCategoryRepository;
    }


    public List<MainCategory> findAll() {
        return mainCategoryRepository.findAll();
    }
}
