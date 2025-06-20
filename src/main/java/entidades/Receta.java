package entidades;

public class Receta {

	private Long id;
	private String nombre, descripcion;
	
	public Receta(String nombre, String descripcion) {
		this.nombre = nombre;
		this.descripcion = descripcion;
	}
	
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
}

