package com.example.controller;

import com.example.entity.Category;
import com.example.service.CategoryService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@GetMapping
	public ResponseEntity<Page<Category>> getAllCategories(@PageableDefault(size = 3) Pageable pageable) {
		return ResponseEntity.ok(categoryService.getAllCategories(pageable));
	}

	@PostMapping
	public ResponseEntity<List<Category>> createCategory(@RequestBody List<Category> category) {
		return ResponseEntity.ok(categoryService.createCategory(category));
	}

	@GetMapping("/{id}")
	public ResponseEntity<Category> getCategoryById(@PathVariable int id) {
		return ResponseEntity.ok(categoryService.getCategoryById(id));
	}

	@PutMapping("/{id}")
	public ResponseEntity<Category> updateCategory(@PathVariable int id, @RequestBody Category updatedCategory) {
		return ResponseEntity.ok(categoryService.updateCategory(id, updatedCategory));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteCategory(@PathVariable int id) {
		categoryService.deleteCategory(id);
		return ResponseEntity.ok("Category deleted successfully");
	}
}
