package com.example.controller;

import com.example.entity.Product;
import com.example.service.ProductService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
public class ProductController {

	@Autowired
	private ProductService productService;

	@GetMapping
	public ResponseEntity<Page<Product>> getAllProducts(@PageableDefault(size = 2) Pageable pageable) {
		return ResponseEntity.ok(productService.getAllProducts(pageable));
	}

	@PostMapping
	public ResponseEntity<Product> createProducts(@RequestBody Product products, @RequestParam int categoryId) {
		return ResponseEntity.ok(productService.createProduct(products, categoryId));
	}

	@GetMapping("/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable int id) {
		return ResponseEntity.ok(productService.getProductById(id));
	}

	@PutMapping("/{id}")
	public ResponseEntity<Product> updateProduct(@PathVariable int id, @RequestBody Product updatedProduct) {
		return ResponseEntity.ok(productService.updateProduct(id, updatedProduct));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteProduct(@PathVariable int id) {
		productService.deleteProduct(id);
		return ResponseEntity.ok("Product deleted successfully");
	}
}
