package com.ezen.bookstore.category;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MainCategoryService {
    private final MainCategoryRepository mainCategoryRepository;

    public MainCategoryService(MainCategoryRepository mainCategoryRepository) {
        this.mainCategoryRepository = mainCategoryRepository;
    }


    public List<MainCategoryResponse> findAll() {
        List<MainCategory> mainCategories = mainCategoryRepository.findAll();
        return mainCategories.stream().map(MainCategoryResponse::of).toList();
    }
}
