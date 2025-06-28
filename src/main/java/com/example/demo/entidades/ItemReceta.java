package com.example.demo.entidades;

import jakarta.persistence.*;

@Entity
@Table(name = "item_receta")
public class ItemReceta {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "iditem_receta")
	private Long id;
	
	@ManyToOne
    @JoinColumn(name = "receta_idreceta", nullable = false)
    private Receta receta;

    @ManyToOne
    @JoinColumn(name = "ingrediente_idingrediente")
    private Ingrediente ingrediente;

    @Column(name = "cantidad")
    private Integer cantidad;

    @Column(name = "calorias")
    private Integer calorias;
	
	public ItemReceta() {
	    // Constructor vacío requerido por JPA
	}
	
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Receta getReceta() {
        return receta;
    }

    public void setReceta(Receta receta) {
        this.receta = receta;
    }

    public Ingrediente getIngrediente() {
        return ingrediente;
    }

    public void setIngrediente(Ingrediente ingrediente) {
        this.ingrediente = ingrediente;
    }
	
}
