package modelo;

import java.util.List;
import java.util.Optional;



public abstract class Contacto {
    private String nombre;
    private String telefono;
	private List<Mensaje> mensajes;

    public Contacto(String nombre, String telefono, List<Mensaje> mensajes) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.mensajes = mensajes;
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
    
	public abstract List<Mensaje> getMensajesRecibidos(Optional<Usuario> usuario);
     
	public List<Mensaje> getMensajesEnviados() {
		return mensajes;
	}

    @Override
    public String toString() {
        return nombre != null ? nombre : telefono;
    }
    
}
