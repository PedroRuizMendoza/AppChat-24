package modelo;

import java.time.LocalDateTime;


public class Mensaje {
	
	
	private Usuario emisor;
	private Contacto receptor;
	private String texto;
	private LocalDateTime hora;
	private int codigo;
	

	public Mensaje(String texto, LocalDateTime hora, Usuario emisor, Contacto receptor) {
		this.texto = texto;
		this.hora = hora;
		this.setEmisor(emisor);
		this.setReceptor(receptor);
	}

	public Mensaje(String texto, LocalDateTime hora) {
		this.texto = texto;
		this.hora = hora;
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
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public void setEmisor(Usuario emisor) {
		this.emisor = emisor;
	}

	public void setReceptor(Contacto receptor) {
		this.receptor = receptor;
	}
	
	
}