package modelo;

import java.util.List;
import java.util.Optional;



public abstract class Contacto {
	private int codigo;
    private String nombre;
	private List<Mensaje> mensajes;

    public Contacto(String nombre,List<Mensaje> mensajes) {
        this.nombre = nombre;
        this.mensajes = mensajes;
    }
    public Contacto(String nombre) {
        this.nombre = nombre;
      
    }

    public String getNombre() {
        return nombre;
    }

	public int getCodigo() {
		return codigo;
	}
    
	public abstract List<Mensaje> getMensajesRecibidos(Optional<Usuario> usuario);
     
	public List<Mensaje> getMensajesEnviados() {
		return mensajes;
	}

		
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	
    
}
