package com.example.demo.entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.*;

@Entity
@Table(name = "ingrediente")
@Inheritance(strategy = InheritanceType.JOINED)
public class Ingrediente {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "idingrediente")
    private Long id;
	
	@Column(name = "nombre")
    private String nombre;

    @Column(name = "calorias")
    private Integer calorias;
	
    @Column(name = "cantidad")
	private Integer cantidad;
    
    @ManyToOne
    private Condimento condimento;

    public Condimento getCondimento() {
		return condimento;
	}

	public void setCondimento(Condimento condimento) {
		this.condimento = condimento;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	@ManyToOne
    private Producto producto;

    
	public Ingrediente() {
	    // Constructor vacío requerido por JPA
	}
	
	public Ingrediente(String nombre, Integer calorias) {
		this.nombre = nombre;
		this.calorias = calorias;
	}
	
	public String getNombre() {
        return nombre;
    }
	public Integer getCalorias() {
        return calorias;
    }
    public void setNombre(String nombre) {
	        this.nombre = nombre;
	}
    public void setCalorias(Integer calorias) {
        this.calorias = calorias;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
	
}
