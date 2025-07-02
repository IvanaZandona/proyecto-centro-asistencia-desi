package com.example.demo.entidades;
import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
@Table(name = "persona")
@Inheritance(strategy = InheritanceType.JOINED)
public class Persona {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idpersona")
	private Long id;
	
	@Column(name = "nombre")
	private String nombre;
	
	@Column(name = "apellido")
	private String apellido;
	
	@Column(name = "domicilio")
	private String domicilio;
	
    @Column(name = "ocupacion")
	private String ocupacion;
	
    @Column(name = "dni", unique = true)
	private Integer dni;
	
    @Column(name = "fecha_nacimiento")
	private LocalDate fechaNacimiento;
	
	public Persona() {
	    // Constructor vacío requerido por JPA
	}

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
