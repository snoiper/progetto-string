package com.example.shopcatalog.data;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.example.shopcatalog.domain.Product;

public interface ProductRepository extends CrudRepository<Product, String> {

	
	//Iterable<Product> findAll(Sort sort);
	
	Page<Product> findByCategory(String category, Pageable pageRequest);
	Page<Product> findAll(Pageable pageRequest);
}
