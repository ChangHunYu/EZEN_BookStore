package com.ezen.bookstore.category;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class SubCategoryService {
    private final SubCategoryRepository subCategoryRepository;

    public SubCategoryService(SubCategoryRepository subCategoryRepository) {
        this.subCategoryRepository = subCategoryRepository;
    }

    public List<SubCategoryResponse> getAllSubCategories(Long mainCategoryId) {
        List<SubCategory> allByMainCategoryId = subCategoryRepository.findAllByMainCategoryId(mainCategoryId);
        if (allByMainCategoryId.isEmpty()) {
            throw new NoSuchElementException("서브 카테고리가 존재하지 않습니다.");
        }
        return allByMainCategoryId.stream().map(SubCategoryResponse::of).toList();
    }
}
