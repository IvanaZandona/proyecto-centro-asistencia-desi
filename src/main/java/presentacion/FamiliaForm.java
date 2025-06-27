package presentacion;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import entidades.Familia;
import entidades.Asistido;

public class FamiliaForm {

	private Integer nroFamilia;

	@NotNull
	@Size(min = 2, max = 50)
	private String nombre;

	@NotNull
	private LocalDate fechaRegistro;
	
    private List<Asistido> asistidos = new ArrayList<>(); // Lista de integrantes

	public FamiliaForm() {
		super();
	}

	public FamiliaForm(Familia f) {
		this.nroFamilia = f.getNroFamilia();
		this.nombre = f.getNombre();
		this.fechaRegistro = f.getFechaRegistro();
		this.asistidos = f.getAsistidos();
	}

	public Familia toPojo() {
		Familia familia = new Familia();
        familia.setNroFamilia(nroFamilia);
        familia.setNombre(nombre);
        familia.setFechaRegistro(fechaRegistro);
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
	
}
