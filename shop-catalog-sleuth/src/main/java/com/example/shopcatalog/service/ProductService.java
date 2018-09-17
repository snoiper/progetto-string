package com.example.shopcatalog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.shopcatalog.data.ProductRepository;
import com.example.shopcatalog.domain.Product;

@Service
public class ProductService {

	@Autowired
	private ProductRepository repo;
	

	
	public Page<Product> getProducts(Pageable pageable) {
		return (Page<Product>) repo.findAll(pageable);
	}
	
	public Product getProduct(String productId) {
		return repo.findById(productId).orElse(null);
	}
	
	public Page<Product> getProductsByCategory(String category, Pageable pageRequest) {
		return repo.findByCategory(category, pageRequest);
	}
	
	public Product create(Product product) {
		return repo.save(product);
	}
	
	public Product changeAvailability(String productId, int change) {
		Product p = repo.findById(productId).orElse(null);
		
		if(p != null) {
			p.setAvailability(p.getAvailability() + change);
			
			return repo.save(p);
		}
		else
		{
			return null;
		}
		//return getProduct(productId);
	}
}
