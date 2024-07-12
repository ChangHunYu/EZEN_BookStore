package com.ezen.bookstore.category;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/subcategory")
public class SubCategoryController {
    private final SubCategoryService subCategoryService;

    public SubCategoryController(SubCategoryService subCategoryService) {
        this.subCategoryService = subCategoryService;
    }

    @GetMapping("/{mainCategoryId}")
    public ResponseEntity<List<SubCategory>> getAllByMaincategoryId(@PathVariable Long mainCategoryId) {
        List<SubCategory> subCategories = subCategoryService.getAllSubCategories(mainCategoryId);
        return new ResponseEntity<>(subCategories, HttpStatus.OK);
    }
}
