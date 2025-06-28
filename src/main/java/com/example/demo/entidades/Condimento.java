package com.example.demo.entidades;

import jakarta.persistence.*;

@Entity
@Table(name = "condimento")
@PrimaryKeyJoinColumn(name = "ingrediente_idingrediente")
public class Condimento extends Ingrediente {

	public Condimento() {
	    // Constructor vacío requerido por JPA
	}
	public Condimento(String nombre, Integer calorias) {
	    super(nombre, calorias);
	}
}
