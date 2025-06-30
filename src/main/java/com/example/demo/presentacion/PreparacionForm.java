package com.example.demo.presentacion;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.example.demo.entidades.Receta;
import com.example.demo.entidades.Preparacion;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class PreparacionForm {

	private Long id;

	private Integer total_raciones_preparadas;
	
	private Integer stock_raciones_restantes;
	@NotNull
	private LocalDate fechaCoccion;
	
    private Long  idreceta; 
    
    private String nombreReceta;

	public PreparacionForm() {
		super();
	}

	public PreparacionForm(Preparacion p) {
		super();
		this.id = p.getId();
		this.total_raciones_preparadas = p.getTotalRacionesPreparadas();
		this.stock_raciones_restantes = p.getStockRacionesRestantes();
		this.fechaCoccion = p.getFechaCoccion();
		this.idreceta = p.getReceta().getId();
		this.nombreReceta = p.getReceta().getNombre();
	}

	public Preparacion toPojo() {
		Preparacion preparacion = new Preparacion();
		preparacion.setId(this.id);
		preparacion.setTotalRacionesPreparadas(total_raciones_preparadas);
		preparacion.setStockRacionesRestantes(stock_raciones_restantes);
		preparacion.setFechaCoccion(fechaCoccion);
        return preparacion;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
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
	
	public void setIdreceta(Long idreceta) {
		this.idreceta = idreceta;
	}
	
	public Long getIdreceta() {
		return this.idreceta;
	}
	
	public String getnombreReceta() {
		return this.nombreReceta;
	}
	
	public void setnombreReceta(String nombreReceta) {
		this.nombreReceta = nombreReceta;
	}
}
