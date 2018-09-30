package com.example.shoprating.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.shoprating.domain.ProdottoRecensito;
import com.example.shoprating.domain.RateRequest;
import com.example.shoprating.domain.Valutazione;
import com.example.shoprating.service.CatalogService;
import com.example.shoprating.service.RatingService;

@RestController
public class RatingController {
//controller è una lcasse che richiama tutto dalle altr, è un passa carte
	@Autowired
	private RatingService service;
	
	@PostMapping("/api/ratings/{productId}/{userId}")
	public @ResponseBody Valutazione setRating(@PathVariable String productId, 
			@PathVariable String userId,
			@RequestBody RateRequest rateReq) {	
		
		return service.setrating(productId, userId, rateReq);
	}
	
	@GetMapping("/api/ratings/{productId}")
	public @ResponseBody List<Valutazione> getProductRating(@PathVariable String productId) {
		return service.getProductRating(productId);
	}
	
	@GetMapping("/api/ratings/popular")
	public @ResponseBody List<ProdottoRecensito> getPopular() {
		return service.getPopular();
	}
}
