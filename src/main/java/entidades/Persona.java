package entidades;
import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
@Table(name = "persona")
public class Persona {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nombre, apellido, domicilio, ocupacion;
	private Integer dni;
	private LocalDate fechaNacimiento;
	
	public Persona(String nombre, String apellido, String domicilio, 
					String ocupacion, Integer dni, LocalDate fechaNacimiento) {
	    this.nombre = nombre;
	    this.apellido = apellido;
	    this.domicilio = domicilio;
	    this.ocupacion = ocupacion;
	    this.dni = dni;
	    this.fechaNacimiento = fechaNacimiento;
	}

    public String getNombre() {
        return nombre;
    }
    
    public String getApellido() {
        return apellido;
    }
    
    public String getDomicilio() {
        return domicilio;
    }

    public String getOcupacion() {
        return ocupacion;
    }
    public Integer getDni() {
        return dni;
    }
    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }
    public void setOcupacion(String ocupacion) {
        this.ocupacion = ocupacion;
    }
    public void setDni(Integer dni) {
        this.dni = dni;
    }
    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
