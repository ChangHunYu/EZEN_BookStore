package com.ezen.bookstore.category;

import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class SubCategoryService {
	
	private final SubCategoryRepository repository;
	
	public SubCategoryService(SubCategoryRepository repository) {
		this.repository = repository;
	}
	
	public List<FindSubListResponse> findAll() {
		
		return repository.findAll()
		.stream()
		.map(m -> new FindSubListResponse(
		m.getId(),
		m.getName()
		))
		.toList();
	}
}
