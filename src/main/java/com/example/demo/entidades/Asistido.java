package com.example.demo.entidades;
import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
@Table(name = "asistido")
@PrimaryKeyJoinColumn(name = "persona_idpersona")
public class Asistido extends Persona {
	
    @Column(name = "fecha_registro")
	private LocalDate fechaRegistro;

	@ManyToOne
    @JoinColumn(name = "familia_nro_familia", nullable = false)
    private Familia familia; // relación con familia
	
	public Asistido() {
	    // Constructor vacío requerido por JPA
	}

	public Asistido(String nombre, String apellido, String domicilio, String ocupacion, 
			Integer dni, LocalDate fechaNacimiento, LocalDate fechaRegistro) {
			super(nombre, apellido, domicilio, ocupacion, dni, fechaNacimiento);
			this.fechaRegistro = fechaRegistro;
	}
	
	public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }
	public void setFechaRegistro(LocalDate fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
	public Familia getFamilia() {
        return familia;
    }
    public void setFamilia(Familia familia) {
        this.familia = familia;
    }
}
