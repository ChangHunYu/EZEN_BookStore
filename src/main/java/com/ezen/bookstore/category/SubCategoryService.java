package com.ezen.bookstore.category;

import org.springframework.stereotype.Service;

@Service
public class SubCategoryService {

    private final SubCategoryRepository subCategoryRepository;

    public SubCategoryService(SubCategoryRepository subCategoryRepository) {
        this.subCategoryRepository = subCategoryRepository;
    }

    public void findAll() {
        subCategoryRepository.findAll()
                .stream()
                .map(subCategory -> new SubCategoryResponse(
                        subCategory.getId(),
                        subCategory.getName()
                )).toList();
    }
}
