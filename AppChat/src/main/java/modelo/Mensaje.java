package modelo;


public class Mensaje {

	private Usuario emisor;
	private Contacto receptor;
	private String texto;
	
	

	public Mensaje(Usuario emisor, Contacto receptor, String texto) {
		this.emisor = emisor;
		this.receptor = receptor;
		this.texto = texto;
	}

	public Usuario getEmisor() {
		return emisor;
	}

	public Contacto getReceptor() {
		return receptor;
	}
	public String getTexto() {
		return texto;
	}
}