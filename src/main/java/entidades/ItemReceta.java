package entidades;

public class ItemReceta {

	//private String id;
	private Integer cantidad, calorias;
	
	public ItemReceta(Integer cantidad, Integer calorias) {
		this.cantidad = cantidad;
		this.calorias = calorias;
	}
	
	public Integer getCantidad() {
        return cantidad;
    }
	
	public Integer getCalorias() {
        return calorias;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }
   
    public void setCalorias(Integer calorias) {
        this.calorias = calorias;
    }

	
}
