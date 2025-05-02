package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.entity.Category;
import com.example.exception.ResourceNotFoundException;
import com.example.repository.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	public Page<Category> getAllCategories(Pageable pageable) {
		return categoryRepository.findAll(pageable);
	}

	public List<Category> createCategory(List<Category> category) {
		return categoryRepository.saveAll(category);
	}

	public Category getCategoryById(int id) {
		return categoryRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "id", String.valueOf(id)));
	}

	public Category updateCategory(int id, Category categoryDetails) {
		Category category = getCategoryById(id);
		category.setName(categoryDetails.getName());
		return categoryRepository.save(category);
	}

	public void deleteCategory(int id) {
		Category category = getCategoryById(id);
		categoryRepository.delete(category);
	}
}
