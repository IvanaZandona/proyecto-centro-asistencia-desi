package com.example.demo.presentacion;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class AsistidoForm {

	private Long id;  // para mapear el id de persona/asistido
	 
	@NotNull(message = "Debe ingresar un DNI")
    @Positive(message = "El DNI debe ser un número positivo")
    private Integer dni;

	@NotBlank(message = "El nombre no puede estar vacío")
    @Size(min = 2, max = 45, message = "El nombre debe tener entre 2 y 45 caracteres")
    private String nombre;

	@NotBlank(message = "El apellido no puede estar vacío")
    @Size(min = 2, max = 45, message = "El apellido debe tener entre 2 y 45 caracteres")
    private String apellido;

	@NotNull(message = "Debe ingresar la fecha de nacimiento")
    @Past(message = "La fecha debe ser en el pasado")
    private LocalDate fechaNacimiento;

	@NotBlank(message = "Debe ingresar la ocupación")
    private String ocupacion;

	@NotBlank(message = "Debe ingresar el domicilio")
    @Size(min = 2, max = 45, message = "El domicilio debe tener entre 2 y 45 caracteres")
    private String domicilio;

	private boolean eliminado;

	 public Long getId() {
	        return id;
	    }
	 public void setId(Long id) {
	        this.id = id;
	    }    
	    
    public Integer getDni() { return dni; }
    public void setDni(Integer dni) { this.dni = dni; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }

    public LocalDate getFechaNacimiento() { return fechaNacimiento; }
    public void setFechaNacimiento(LocalDate fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; }

    public String getOcupacion() { return ocupacion; }
    public void setOcupacion(String ocupacion) { this.ocupacion = ocupacion; }

    public String getDomicilio() { return domicilio; }
    public void setDomicilio(String domicilio) { this.domicilio = domicilio; }
    
    public boolean isEliminado() {
        return eliminado;
    }
    public void setEliminado(boolean eliminado) {
        this.eliminado = eliminado;
    }
}
