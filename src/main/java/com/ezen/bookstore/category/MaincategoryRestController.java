package com.ezen.bookstore.category;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class MaincategoryRestController {

    MainCategoryService mainCategoryService;

    public MaincategoryRestController(MainCategoryService mainCategoryService) {
        this.mainCategoryService = mainCategoryService;
    }

    @GetMapping("/maincategory")
    void findAll() {
        mainCategoryService.findAll();
    }

}
