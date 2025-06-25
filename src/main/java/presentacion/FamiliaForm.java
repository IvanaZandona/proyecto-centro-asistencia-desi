package presentacion;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import entidades.Familia;

public class FamiliaForm {

	private Integer nroFamilia;

	@NotNull
	@Size(min = 2, max = 50)
	private String nombre;

	@NotNull
	private LocalDate fechaRegistro;

	public FamiliaForm() {
		super();
	}

	public FamiliaForm(Familia f) {
		this.nroFamilia = f.getNroFamilia();
		this.nombre = f.getNombre();
		this.fechaRegistro = f.getFechaRegistro();
	}

	public Familia toPojo() {
		return new Familia(nroFamilia, nombre, fechaRegistro);
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
	
}
