package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.entity.Category;
import com.example.entity.Product;
import com.example.exception.ResourceNotFoundException;
import com.example.repository.CategoryRepository;
import com.example.repository.ProductRepository;
import com.example.utils.Views;
import com.fasterxml.jackson.annotation.JsonView;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@JsonView(Views.ProductSummary.class)
	public Page<Product> getAllProducts(Pageable pageable) {
		return productRepository.findAll(pageable);
	}

	public Product createProduct(Product product, int categoryId) {
		Category category = categoryRepository.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "id", String.valueOf(categoryId)));

		product.setCategory(category);
		return productRepository.save(product);
	}

	@JsonView(Views.ProductDetail.class)
	public Product getProductById(int id) {

		return productRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Product", "id", String.valueOf(id)));
	}

	public Product updateProduct(int id, Product updatedProduct) {
		Product product = getProductById(id);
		product.setName(updatedProduct.getName());
		product.setPrice(updatedProduct.getPrice());

		return productRepository.save(product);
	}

	public void deleteProduct(int id) {
		Product product = getProductById(id);
		productRepository.delete(product);
	}
}
