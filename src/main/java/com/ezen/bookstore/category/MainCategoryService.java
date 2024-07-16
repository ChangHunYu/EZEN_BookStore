package com.ezen.bookstore.category;

import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class MainCategoryService {
	
	private final MainCategoryRepository repository;
	
	public MainCategoryService(MainCategoryRepository repository) {
		this.repository = repository;
	}
	
	public List<FindMainListResponse> findAll() {
		
		return repository.findAll()
		.stream()
		.map(m -> new FindMainListResponse(
		m.getId(),
		m.getName()
		))
		.toList();
	}
}
