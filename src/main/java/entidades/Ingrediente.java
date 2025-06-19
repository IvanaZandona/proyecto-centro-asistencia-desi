package entidades;

public class Ingrediente {

	//private String id;
	private String nombre;
	private Integer calorias;
	
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
	
}
