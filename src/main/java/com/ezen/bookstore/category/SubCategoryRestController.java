package com.ezen.bookstore.category;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class SubCategoryRestController {

    private final SubCategoryService subCategoryService;


    public SubCategoryRestController(SubCategoryService subCategoryService) {
        this.subCategoryService = subCategoryService;
    }

    @GetMapping("/subcategory")
    void findAll() {
        subCategoryService.findAll();
    }
}
