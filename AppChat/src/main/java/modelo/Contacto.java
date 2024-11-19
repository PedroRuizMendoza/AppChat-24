package modelo;

import java.util.List;


public class Contacto {
    private String nombre;
    private String telefono;
	private List<Mensaje> mensajes;

    public Contacto(String nombre, String telefono) {
        this.nombre = nombre;
        this.telefono = telefono;
    }
    public Contacto(String nombre) {
        this.nombre = nombre;
      
    }

    public String getNombre() {
        return nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    @Override
    public String toString() {
        return nombre != null ? nombre : telefono;
    }
}
