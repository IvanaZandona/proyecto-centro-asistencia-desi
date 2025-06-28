package com.example.demo.entidades;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "familia")
public class Familia {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "nro_familia")
	private Integer nroFamilia;
	
	private String nombre;
	
	@Column(name = "fecha_registro")
	private LocalDate fechaRegistro;
	
	/*@OneToMany(mappedBy = "familia", cascade = CascadeType.ALL)
    private List<Asistido> asistidos; // Relación con Asistido*/
	
	@OneToMany(mappedBy = "familia", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Asistido> asistidos = new ArrayList<>(); // Relación con Asistido
	
	public Familia() {
	    this.fechaRegistro = LocalDate.now(); // 
	}

	public Familia(Integer nroFamilia, String nombre, LocalDate fechaRegistro) {
		this.setNroFamilia(nroFamilia);
		this.setNombre(nombre);
		//this.setFechaRegistro(fechaRegistro);
		this.fechaRegistro = LocalDate.now(); // Valor por defecto
	}

	public Integer getNroFamilia() {
		return nroFamilia;
	}

	public void setNroFamilia(Integer nroFamilia) {
		this.nroFamilia = nroFamilia;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public LocalDate getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(LocalDate fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public List<Asistido> getAsistidos() {
		return asistidos ;
	}
	public void setAsistidos(List<Asistido> asistidos) {
	    this.asistidos = asistidos;
	}

}
