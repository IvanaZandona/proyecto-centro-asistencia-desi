package com.example.demo.presentacion;
import java.util.List;
import com.example.demo.entidades.Receta;
public class PreparacionBuscarForm {

	private Long Id;
	private Long recetaSeleccionada;
	
	private List<Receta> recetas;

	public Long getId() {
		if (Id != null )
			return Id;
		else
			return null;
	}

	public void setId(Long id) {
		this.Id = id;
	}
	
	public List<Receta> getRecetas(){
		return recetas;
	}
	
	public void setRecetas(List<Receta> recetas) {
		this.recetas = recetas;
	}
	
	public Long getRecetaSeleccionada() {
		return this.recetaSeleccionada;
	}
	
	public void setRecetaSeleccionada(Long idreceta) {
		this.recetaSeleccionada = idreceta;
	}
	
