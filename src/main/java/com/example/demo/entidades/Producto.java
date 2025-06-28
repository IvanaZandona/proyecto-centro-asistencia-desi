package com.example.demo.entidades;

import jakarta.persistence.*;

@Entity
@Table(name = "producto")
@PrimaryKeyJoinColumn(name = "ingrediente_idingrediente")
public class Producto extends Ingrediente {

	@Column(name = "stock_disponible")
    private Float stockDisponible;

    @Column(name = "precio_actual")
    private Float precioActual;
	
	public Producto() {
	    // Constructor vacío requerido por JPA
	} 
	public Producto(String nombre, Integer calorias, float stockDisponible, float precioActual) {
		super(nombre, calorias);
		this.stockDisponible = stockDisponible;
		this.precioActual = precioActual;
	}

    public float getStockDisponible() {
        return stockDisponible;
    }

    public float getPrecioActual() {
        return precioActual;
    }

    public void setStockDisponible(float stockDisponible) {
        this.stockDisponible = stockDisponible;
    }

    public void setPrecioActual(float precioActual) {
        this.precioActual = precioActual;
    }
}
