package com.ezen.bookstore.category;

import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/maincategory")
public class MainCategoryController {
    private final MainCategoryService mainCategoryService;

    public MainCategoryController(MainCategoryService mainCategoryService) {
        this.mainCategoryService = mainCategoryService;
    }

    // 전체 목록 조회
    @GetMapping
    public ResponseEntity<List<MainCategoryResponse>> getAll() {
        List<MainCategoryResponse> mainCategories = mainCategoryService.findAll();
        return new ResponseEntity<>(mainCategories, HttpStatus.OK);
    }

    // id로 조회
    @GetMapping("/{id}")
    public ResponseEntity<MainCategoryResponse> getById(@PathVariable Long id) {
        MainCategoryResponse maincategory = mainCategoryService.findById(id);
        return new ResponseEntity<>(maincategory, HttpStatus.OK);
    }
}
