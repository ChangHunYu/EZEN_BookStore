package com.ezen.bookstore.category;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SubCategoryController {
	
	private final SubCategoryService service;
	
	public SubCategoryController(SubCategoryService service) {
		this.service = service;
	}
	
	@GetMapping("/subcategory")
	public List<FindSubListResponse> findAll() {
		return service.findAll();
	}
}
