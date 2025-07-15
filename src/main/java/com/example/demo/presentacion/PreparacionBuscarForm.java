package com.example.demo.presentacion;
import java.util.List;
import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;
import com.example.demo.entidades.Receta;
public class PreparacionBuscarForm {

	private LocalDate fechaCoccion;
	private Long recetaSeleccionada;
	//private boolean soloActivo = true;
	private List<Receta> recetas;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public LocalDate getFechaCoccion() {
		return this.fechaCoccion;
	}
	
	public void setFechaCoccion(LocalDate fechaCoccion) {
		this.fechaCoccion = fechaCoccion;
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
	
	/*public void setSoloActivo(boolean activo) {
		this.soloActivo = activo;
	}
	
	public boolean getSoloActivo() {
		return this.soloActivo;
	}*/
	
}
