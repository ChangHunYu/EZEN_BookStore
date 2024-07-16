package com.ezen.bookstore.category;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainCategoryController {
	
	private final MainCategoryService mainCategoryService;
	
	public MainCategoryController(MainCategoryService mainCategoryService) {
		this.mainCategoryService = mainCategoryService;
	}
	
	@GetMapping("/maincategory")
	public List<FindMainListResponse> findAll() {
		return mainCategoryService.findAll();
	}
}
