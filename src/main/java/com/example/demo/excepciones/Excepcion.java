package com.example.demo.excepciones;

public class Excepcion extends RuntimeException  {

	private String atributo;
	private String codigo;  
    private String mensaje;

    public Excepcion() {
        super();
    }

    public Excepcion(String mensaje) {
        super(mensaje);
        this.mensaje = mensaje;
    }

    public Excepcion(String mensaje, String atributo) {
        super(mensaje);
        this.atributo = atributo;
        this.mensaje = mensaje;
    }

    public Excepcion(String codigo, String mensaje, String atributo) {
        super(mensaje);
        this.codigo = codigo;
        this.mensaje = mensaje;
        this.atributo = atributo;
    }

    public String getAtributo() {
        return atributo;
    }

    public void setAtributo(String atributo) {
        this.atributo = atributo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    @Override
    public String getMessage() {
        return mensaje != null ? mensaje : super.getMessage();
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    
}
