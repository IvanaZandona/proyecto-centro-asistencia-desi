package entidades;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

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
	
	public Familia(Integer nroFamilia, String nombre, LocalDate fechaRegistro) {
		this.setNroFamilia(nroFamilia);
		this.setNombre(nombre);
		this.setFechaRegistro(fechaRegistro);
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
