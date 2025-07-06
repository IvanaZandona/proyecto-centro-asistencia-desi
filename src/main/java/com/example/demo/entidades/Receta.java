package com.example.demo.entidades;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.*;
import java.util.List;
import java.util.ArrayList;


@Entity
@Table(name = "receta")
public class Receta {
	
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "idreceta")
	private Long id;
    
    @Column(name = "nombre")
    private String nombre;

    @Column(name = "descripcion")
    private String descripcion;
    
    @Column (name = "activa")
    private Boolean activa =  true;
    	
	// Relación con ItemReceta (uno a muchos)
    @OneToMany(mappedBy = "receta", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemReceta> items = new ArrayList<>();

    // Relación con Preparacion (uno a muchos)
    @OneToMany(mappedBy = "receta", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Preparacion> preparaciones = new ArrayList<>();

	public Receta() {
		
	}
	
	public Receta(String nombre, String descripcion, Boolean activa) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.activa = activa;
	}
	

	public String getNombre() {
        return nombre;
    }
	
	public String getDescripcion() {
        return descripcion;
    }
	
	public void setNombre(String nombre) {
	        this.nombre = nombre;
	}
	public void setDescripcion(String descripcion) {
	        this.descripcion = descripcion;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public List<ItemReceta> getItems() {
        return items;
    }

    public void setItems(List<ItemReceta> items) {
        this.items = items;
    }

    public List<Preparacion> getPreparaciones() {
        return preparaciones;
    }

    public void setPreparaciones(List<Preparacion> preparaciones) {
        this.preparaciones = preparaciones;
    }
    
}

