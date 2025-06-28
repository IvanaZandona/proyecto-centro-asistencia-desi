package com.example.demo.entidades;
import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
@Table(name = "voluntario")
@PrimaryKeyJoinColumn(name = "persona_idpersona")
public class Voluntario extends Persona {

    @Column(name = "nro_seguro", unique = true)
	private Integer nroSeguro;
		
	public Voluntario() {
		
	}
	public Voluntario(String nombre, String apellido, String domicilio, String ocupacion, 
						Integer dni, LocalDate fechaNacimiento, Integer nroSeguro) {
        super(nombre, apellido, domicilio, ocupacion, dni, fechaNacimiento);
        this.nroSeguro = nroSeguro;
    }

    public int getNroSeguro() {
        return nroSeguro;
    }

    public void setNroSeguro(Integer nroSeguro) {
        this.nroSeguro = nroSeguro;
    }
}
