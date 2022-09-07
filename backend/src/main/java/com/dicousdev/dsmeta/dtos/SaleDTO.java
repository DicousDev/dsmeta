package com.dicousdev.dsmeta.dtos;

import java.time.LocalDate;


import com.dicousdev.dsmeta.entities.Sale;
import com.fasterxml.jackson.annotation.JsonFormat;

public class SaleDTO {

	private Long id;
	private String nomeVendedor;
	private Integer visitados;
	private Integer vendas;
	private Double total;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate date;
	
	public SaleDTO() {
		
	}
	
	public SaleDTO(Sale sale) {
		setId(sale.getId());
		setNomeVendedor(sale.getNomeVendedor());
		setVisitados(sale.getVisitados());
		setVendas(sale.getVendas());
		setTotal(sale.getTotal());
		setDate(sale.getDate());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeVendedor() {
		return nomeVendedor;
	}

	public void setNomeVendedor(String nomeVendedor) {
		this.nomeVendedor = nomeVendedor;
	}

	public Integer getVisitados() {
		return visitados;
	}

	public void setVisitados(Integer visitados) {
		this.visitados = visitados;
	}

	public Integer getVendas() {
		return vendas;
	}

	public void setVendas(Integer vendas) {
		this.vendas = vendas;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}
}
