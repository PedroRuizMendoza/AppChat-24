package persistencia;

import modelo.Mensaje;

public interface MensajeDAO {

	void registrarMensaje(Mensaje mensaje);

	Mensaje recuperarMensaje(int codigo);


	
	
}
