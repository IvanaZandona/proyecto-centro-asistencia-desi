package entidades;
import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
@Table(name = "asistido")
public class Asistido extends Persona {
	
	private LocalDate fechaRegistro;

	@ManyToOne
    @JoinColumn(name = "familia_nro_familia", nullable = false)
    private Familia familia; // relación con familia
	
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
