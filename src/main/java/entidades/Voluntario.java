package entidades;
import java.time.LocalDate;

public class Voluntario extends Persona {

	private Integer nroSeguro;
		
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
