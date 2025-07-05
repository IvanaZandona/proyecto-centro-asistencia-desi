package com.example.demo.presentacion;

public class FamiliaBuscarForm {

	private String nombre;
	private Integer nroFamilia;

	public String getNombre() {
		if (nombre != null && !nombre.trim().isEmpty())
			return nombre;
		else
			return null;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getNroFamilia() {
		return nroFamilia;
	}

	public void setNroFamilia(Integer nroFamilia) {
		this.nroFamilia = nroFamilia;
	}
	
}
