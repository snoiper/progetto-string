package com.example.shoprating.service;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.shoprating.domain.ProdottoRecensito;
import com.example.shoprating.domain.Product;
import com.example.shoprating.domain.RateRequest;
import com.example.shoprating.domain.Valutazione;
import com.example.shoprating.repository.RateRepo;

//le applicazioni vedono il controller, lui chiama il service , il controller ha il compito di smistare, le altre classi tipo questo svolgo le attività()

@Service
public class RatingService {
	@Autowired
	private RateRepo repo;
	
	@Autowired
	private CatalogService catalog;
//aggiunge una recensione
	public Valutazione setrating(String productId, String userId, RateRequest raterequest) {
		//andiamo a pescare tutte le recensioni che ha fatto l'utente
		List<Valutazione> tempVal = repo.findByUserId(userId);
		int presente = -1;
		
		for(int i = 0; i < tempVal.size(); i++) {
			if(tempVal.get(i).getProdcutId() == productId) {
				presente = i;
				break;
			}
		}
		
		if(presente < 0) {
			Valutazione nuovaVal = new Valutazione();
			nuovaVal.setProdcutId(productId);
			nuovaVal.setUserId(userId);
			nuovaVal.setRate(raterequest.getRate());
			nuovaVal.setComment(raterequest.getComment());
			
			return repo.save(nuovaVal);
		}
		else
		{
			//se è gia presente la recensione non puoi modificarla.
			
			
			return tempVal.get(presente);
		}
	}
//restituisce una lista contenente tutte le recensioni di un prodotto
	public List<Valutazione> getProductRating(String productId) {
		return repo.findByProdcutId(productId);
	}

	public List<ProdottoRecensito> getPopular() {
		//tutti i prodotti
		List<Valutazione> tempVal = (List<Valutazione>) repo.findAll();
		
		double somma = 0;
		
		for(int i = 0; i < tempVal.size(); i++) {
			somma = tempVal.get(i).getRate();
		}
		
		double media = somma / tempVal.size();
		//creo una variabile vuota nella quale andranno tutti i prodotti sopra la media
		List<ProdottoRecensito> belli = new ArrayList<ProdottoRecensito>() ;
		
		for(int i = 0; i < tempVal.size(); i++) {
			if(tempVal.get(i).getRate() > media) {
				//interroga il servizio catalog, restituisce il prodotto completo
				Product p = catalog.getProductById(tempVal.get(i).getProdcutId());
				
				//creo un progetto recensito
				ProdottoRecensito tempProd = new ProdottoRecensito();
				//questi sono i prodotti di prodottorecensito
				tempProd.setProductId(p.getId());
				tempProd.setPrice(p.getPrice());
				tempProd.setTitle(p.getTitle());
				tempProd.setAverageRate(tempVal.get(i).getRate());
				//belli è una lista di prodottorecensito, prodottorecensito non è un prodotto, ci assomiglia
				belli.add(tempProd);
			}
		}
		//srà la lista contenente i prodotti recensiti con i canmpi, id, price, title, averagerate.
		return belli;
	}
	
}
