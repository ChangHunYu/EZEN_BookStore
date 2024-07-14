package com.ezen.bookstore.category;

import org.springframework.stereotype.Service;

@Service
public class MainCategoryService {

    private final MainCategoryRepository mainCategoryRepository;


    public MainCategoryService(MainCategoryRepository mainCategoryRepository) {
        this.mainCategoryRepository = mainCategoryRepository;
    }


    void findAll() {
        mainCategoryRepository.findAll()
                .stream()
                .map(m -> new MainCategoryResponse(
                        m.getId(),
                        m.getName()
                ))
                .toList();
    }
}
