package com.dicousdev.dsmeta.services;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dicousdev.dsmeta.dtos.SaleDTO;
import com.dicousdev.dsmeta.entities.Sale;
import com.dicousdev.dsmeta.repositories.SaleRepository;

@Service
public class SaleService {

	@Autowired
	private SaleRepository saleRepository;
	
	@Transactional(readOnly = true)
	public Optional<Sale> findSaleById(Long idVendedor) 
	{
		return saleRepository.findById(idVendedor);
	}
	
	@Transactional(readOnly = true)
	public Page<SaleDTO> findSalesAll(String minDate, String maxDate, Pageable pageable) {
		
		LocalDate hoje = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
		LocalDate min = minDate.equals("") ? hoje.minusDays(365) : LocalDate.parse(minDate);
		LocalDate max = maxDate.equals("") ? hoje : LocalDate.parse(maxDate);
		Page<Sale> sales = saleRepository.findSalesAll(min, max, pageable);
		return sales.map(sale -> new SaleDTO(sale));
	}
}
