package com.example.demo.presentacion;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.format.annotation.DateTimeFormat;
import com.example.demo.entidades.Receta;
import com.example.demo.entidades.Preparacion;

import jakarta.validation.constraints.*;

public class PreparacionForm {

	private Long id;

	private Integer totalRacionesPreparadas;
	
	private Integer stockRacionesRestantes;
	
	@NotNull
	@PastOrPresent(message = "La fecha de cocción no puede ser en el futuro")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate fechaCoccion;
	
    private Long  idreceta; 
    
    private String nombreReceta;
    
    private Receta receta;
    
    private boolean activo;

	public PreparacionForm() {
		super();
	}

	public PreparacionForm(Preparacion p) {
		super();
		this.id = p.getId();
		this.totalRacionesPreparadas = p.getTotalRacionesPreparadas();
		this.stockRacionesRestantes = p.getStockRacionesRestantes();
		this.fechaCoccion = p.getFechaCoccion();
		this.idreceta = p.getReceta().getId();
		this.nombreReceta = p.getReceta().getNombre();
		this.receta = p.getReceta();
		this.activo = p.getActivo();
	}

	public Preparacion toPojo() {
		Preparacion preparacion = new Preparacion();
		preparacion.setId(this.id);
		preparacion.setTotalRacionesPreparadas(this.totalRacionesPreparadas);
		preparacion.setStockRacionesRestantes(this.stockRacionesRestantes);
		preparacion.setFechaCoccion(fechaCoccion);
		preparacion.setActivo(true);
        return preparacion;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getTotalRacionesPreparadas() {
		return totalRacionesPreparadas;
	}

	public void setTotalRacionesPreparadas(Integer totalRaciones) {
		this.totalRacionesPreparadas = totalRaciones;
	}

	public Integer getStockRacionesRestantes() {
		return stockRacionesRestantes;
	}

	public void setStockRacionesRestantes(Integer stockRaciones) {
		this.stockRacionesRestantes = stockRaciones;
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
	
	public String getNombreReceta() {
		return this.nombreReceta;
	}
	
	public void setNombreReceta(String nombreReceta) {
		this.nombreReceta = nombreReceta;
	}
	
	public boolean getActivo() {
		return this.activo;
	}
	
	public void setActivo(boolean activo) {
		this.activo = activo;
	}
}
