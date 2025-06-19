package entidades;
import java.time.LocalDate;

public class Asistido extends Persona {

	private LocalDate fechaRegistro;
	
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
}
