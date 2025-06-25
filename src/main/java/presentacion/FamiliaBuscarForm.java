package presentacion;

public class FamiliaBuscarForm {

	private String nombre;

	public String getNombre() {
		if (nombre != null && !nombre.trim().isEmpty())
			return nombre;
		else
			return null;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
}
