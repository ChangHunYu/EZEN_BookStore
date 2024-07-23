package com.ezen.bookstore.category;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subcategory")
public class SubCategoryController {
    private final SubCategoryService subCategoryService;

    public SubCategoryController(SubCategoryService subCategoryService) {
        this.subCategoryService = subCategoryService;
    }

    // 서브카테고리 목록 조회 by 메인카테고리 ID
    @GetMapping
    public ResponseEntity<List<SubCategoryResponse>> getAllByMaincategoryId(@RequestParam Long mainCategoryId) {
        List<SubCategoryResponse> subCategories = subCategoryService.getAllSubCategories(mainCategoryId);
        return new ResponseEntity<>(subCategories, HttpStatus.OK);
    }
}
