package com.dicousdev.dsmeta.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dicousdev.dsmeta.dtos.SaleDTO;
import com.dicousdev.dsmeta.services.SaleService;
import com.dicousdev.dsmeta.services.SmsService;

@RestController
@RequestMapping(value = "/sales")
public class SaleController {

	@Autowired
	private SaleService saleService;
	
	@Autowired
	private SmsService smsService;
	
	@GetMapping
	public Page<SaleDTO> findSalesAll(
			@RequestParam(value="minDate", defaultValue="") String minDate, 
			@RequestParam(value="maxDate", defaultValue="") String maxDate, 
			Pageable pageable) {
		return saleService.findSalesAll(minDate, maxDate, pageable);
	}
	
	@GetMapping("/{idVendedor}/notification")
	public ResponseEntity<Object> notifySms(@PathVariable Long idVendedor) {
		
		try {
			smsService.sendSms(idVendedor);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		catch(RuntimeException exception) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception);
		}
	}
}
