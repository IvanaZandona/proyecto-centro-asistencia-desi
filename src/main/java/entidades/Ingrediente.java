package entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Ingrediente {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
	private String nombre;
	private Integer calorias, cantidad;
	
	public Ingrediente(String nombre, Integer calorias) {
		this.nombre = nombre;
		this.calorias = calorias;
	}
	
	public String getNombre() {
        return nombre;
    }
	public Integer getCalorias() {
        return calorias;
    }
    public void setNombre(String nombre) {
	        this.nombre = nombre;
	}
    public void setCalorias(Integer calorias) {
        this.calorias = calorias;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
	
}
