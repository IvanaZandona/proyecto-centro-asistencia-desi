package com.example.demo.presentacion;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.example.demo.entidades.Asistido;
import com.example.demo.entidades.Familia;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class FamiliaForm {

	private Integer nroFamilia;

	@NotNull
	@Size(min = 2, max = 50)
	private String nombre;

	@NotNull
	private LocalDate fechaRegistro;
	    
    @NotNull
    @Size(min = 1)
    private List<AsistidoForm> integrantes = new ArrayList<>();
    
    private AsistidoForm nuevoIntegrante = new AsistidoForm(); // este se usa para el form individual

	public FamiliaForm() {
		super();
	}

	public FamiliaForm(Familia f) {
		this.nroFamilia = f.getNroFamilia();
		this.nombre = f.getNombre();
		this.fechaRegistro = f.getFechaRegistro();
		this.integrantes = new ArrayList<>();
		for (Asistido a : f.getAsistidos()) {
		    AsistidoForm af = new AsistidoForm();
		    af.setDni(a.getDni());
		    af.setNombre(a.getNombre());
		    af.setApellido(a.getApellido());
		    af.setDomicilio(a.getDomicilio());
		    af.setFechaNacimiento(a.getFechaNacimiento());
		    af.setOcupacion(a.getOcupacion());
		    this.integrantes.add(af);
		    }
	}

	public Familia toPojo() {
		Familia familia = new Familia();
	    familia.setNroFamilia(nroFamilia);
	    familia.setNombre(nombre);
	    familia.setFechaRegistro(LocalDate.now());

	    List<Asistido> asistidos = new ArrayList<>();
	    for (AsistidoForm af : this.integrantes) {
	        Asistido asistido = new Asistido(
	            af.getNombre(),
	            af.getApellido(),
	            af.getDomicilio(),
	            af.getOcupacion(),
	            af.getDni(),
	            af.getFechaNacimiento(),
	            LocalDate.now()
	        );
	        asistido.setFamilia(familia);
	        asistidos.add(asistido);
	    }
	    familia.setAsistidos(asistidos);
	    return familia;
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
	
	public List<AsistidoForm> getIntegrantes() {
	    return integrantes;
	}

	public void setIntegrantes(List<AsistidoForm> integrantes) {
	    this.integrantes = integrantes;
	}

	public AsistidoForm getNuevoIntegrante() {
		return nuevoIntegrante;
	}

	public void setNuevoIntegrante(AsistidoForm nuevoIntegrante) {
		this.nuevoIntegrante = nuevoIntegrante;
	}
	
}
