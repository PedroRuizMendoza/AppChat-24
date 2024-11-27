package persistencia;


public class TDSFactoriaDAO extends FactoriaDAO {
	public TDSFactoriaDAO () {
	}
	
	
	
	@Override
	public GrupoDao getGrupoDAO() {
		return AdaptadorGrupo.getInstancia();
	}

	@Override
	public ContactoIndividualDAO getContactoIndividualDAO() {
		return AdaptadorContactoIndividual.getInstancia();
	}

	@Override
	public MensajeDAO getMensajeDAO() {
		return AdaptadorMensaje.getInstancia();
	}


	@Override
	public UsuarioDAO getUserDAO() {
		return AdaptadorUsuario.getInstancia();
	}








}
