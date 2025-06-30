package com.example.demo.entidades;
import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
@Table(name = "preparacion")
public class Preparacion {

	@Id
    @GeneratedValue(strategy = GenerationType.TABLE)
	@Column(name = "idpreparacion")
	private Long id;
	
	@ManyToOne()
    @JoinColumn(name = "receta_idreceta")
    private Receta receta;

    @Column(name = "total_raciones_preparadas")
    private Integer totalRacionesPreparadas;

    @Column(name = "stock_raciones_restantes")
    private Integer stockRacionesRestantes;

    @Column(name = "fecha_coccion")
    private LocalDate fechaCoccion;
	
	public Preparacion() {
		
	}
	public Preparacion(Integer totalRacionesPreparadas, Integer stockRacionesRestantes, LocalDate fechaCoccion) {
        this.totalRacionesPreparadas = totalRacionesPreparadas;
        this.stockRacionesRestantes = stockRacionesRestantes;
        this.fechaCoccion = fechaCoccion;
    }

    public Integer getTotalRacionesPreparadas() {
        return totalRacionesPreparadas;
    }

    public Integer getStockRacionesRestantes() {
        return stockRacionesRestantes;
    }

    public LocalDate getFechaCoccion() {
        return fechaCoccion;
    }

    public void setTotalRacionesPreparadas(Integer totalRacionesPreparadas) {
        this.totalRacionesPreparadas = totalRacionesPreparadas;
    }

    public void setStockRacionesRestantes(Integer stockRacionesRestantes) {
        this.stockRacionesRestantes = stockRacionesRestantes;
    }

    public void setFechaCoccion(LocalDate fechaCoccion) {
        this.fechaCoccion = fechaCoccion;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	public Receta getReceta() {
        return receta;
    }

    public void setReceta(Receta receta) {
        this.receta = receta;
    }
}
