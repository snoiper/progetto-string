package com.example.shoprating.repository;

import java.awt.print.Pageable;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.shoprating.domain.Valutazione;

public interface RateRepo extends CrudRepository<Valutazione, String>{
	public List<Valutazione> findByProdcutId(String prodcutId);
	public List<Valutazione> findByUserId(String userId);
}
