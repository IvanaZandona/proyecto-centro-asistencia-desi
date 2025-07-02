package com.example.demo.presentacion;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class AsistidoForm {

	@NotNull
    @Positive
    private Integer dni;

    @NotNull
    @Size(min = 2, max = 45)
    private String nombre;

    @NotNull
    @Size(min = 2, max = 45)
    private String apellido;

    @NotNull
    private LocalDate fechaNacimiento;

    @NotNull
    private String ocupacion;

    @NotNull
    @Size(min = 2, max = 45)
    private String domicilio;

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
    
}
