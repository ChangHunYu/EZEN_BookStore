package com.ezen.bookstore.category;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class MainCategoryService {
    private final MainCategoryRepository mainCategoryRepository;

    public MainCategoryService(MainCategoryRepository mainCategoryRepository) {
        this.mainCategoryRepository = mainCategoryRepository;
    }


    public List<MainCategoryResponse> findAll() {
        List<MainCategory> mainCategories = mainCategoryRepository.findAll();

//        List<MainCategoryResponse> list = mainCategories.stream().map(mainCategory
//                -> new MainCategoryResponse(mainCategory.getId(), mainCategory.getName())).toList();

//        List<MainCategoryResponse> list2 = mainCategories.stream().map(mainCategory
//                -> MainCategoryResponse.of(mainCategory)).toList();

        return mainCategories.stream().map(MainCategoryResponse::of).toList();
    }

    public MainCategoryResponse findById(Long id) {
        MainCategory mainCategory = mainCategoryRepository.findById(id).orElse(null);
        if (mainCategory == null) {
            throw new NoSuchElementException("id에 해당하는 mainCategory가 없습니다.");
        }
        return MainCategoryResponse.of(mainCategory);
    }
}
