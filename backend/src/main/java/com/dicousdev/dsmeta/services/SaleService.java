package com.dicousdev.dsmeta.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dicousdev.dsmeta.entities.Sale;
import com.dicousdev.dsmeta.repositories.SaleRepository;

@Service
public class SaleService {

	@Autowired
	private SaleRepository saleRepository;
	
	public List<Sale> findSalesAll() {
		return saleRepository.findAll();
	}
}
