package entidades;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.List;
import java.util.ArrayList;


@Entity
public class Receta {
	
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String nombre, descripcion;
	private List<Ingrediente> ingredientes = new ArrayList<>();

	
	public Receta(String nombre, String descripcion) {
		this.nombre = nombre;
		this.descripcion = descripcion;
	}
	
	public Receta() {}
	
	public String getNombre() {
        return nombre;
    }
	
	public String getDescripcion() {
        return descripcion;
    }
	
	public void setNombre(String nombre) {
	        this.nombre = nombre;
	}
	public void setDescripcion(String descripcion) {
	        this.descripcion = descripcion;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	@Override
	public String toString() {
		return String.format(descripcion, null);
	}

	public List<Ingrediente> getIngredientes() {
		return ingredientes;
	}

	public void setIngredientes(List<Ingrediente> ingredientes) {
		this.ingredientes = ingredientes;
	}
	
}

