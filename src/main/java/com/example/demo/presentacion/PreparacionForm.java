package com.example.demo.presentacion;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.example.demo.entidades.Receta;
import com.example.demo.entidades.Preparacion;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class PreparacionForm {

	private Long Id;

	private Integer total_raciones_preparadas;
	
	private Integer stock_raciones_restantes;
	@NotNull
	private LocalDate fechaCoccion;
	
    private Receta receta = new Receta(); // Lista de integrantes

	public PreparacionForm() {
		super();
	}

	public PreparacionForm(Preparacion p) {
		this.Id = p.getId();
		this.total_raciones_preparadas = p.getTotalRacionesPreparadas();
		this.stock_raciones_restantes = p.getStockRacionesRestantes();
		this.fechaCoccion = p.getFechaCoccion();
		this.receta = p.getReceta();
		
	}

	public Preparacion toPojo() {
		Preparacion preparacion = new Preparacion();
		preparacion.setId(Id);
		preparacion.setTotalRacionesPreparadas(total_raciones_preparadas);
		preparacion.setStockRacionesRestantes(stock_raciones_restantes);
		preparacion.setFechaCoccion(fechaCoccion);
		preparacion.setReceta(receta); 
        return preparacion;
	}

	public Long getId() {
		return Id;
	}

	public void setId(Long Id) {
		this.Id = Id;
	}

	public Integer getTotalRacionesPreparadas() {
		return total_raciones_preparadas;
	}

	public void setTotalRacionesPreparadas(Integer totalRaciones) {
		this.total_raciones_preparadas = totalRaciones;
	}

	public Integer getStockRacionesRestantes() {
		return stock_raciones_restantes;
	}

	public void setStockRacionesRestantes(Integer stockRaciones) {
		this.stock_raciones_restantes = stockRaciones;
	}

	public LocalDate getFechaCoccion() {
		return fechaCoccion;
	}

	public void setFechaCoccion(LocalDate fechaCoccion) {
		this.fechaCoccion = fechaCoccion;
	}
	
}
