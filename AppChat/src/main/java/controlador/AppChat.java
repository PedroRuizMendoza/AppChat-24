package controlador;



public class AppChat {
	// Instancia del controlador.
		private static AppChat unicaInstancia = null;
	
		
		private AppChat() {
			//inicializarAdaptadores(); // debe ser la primera linea para evitar error de sincronización
		}

		
		// Aplicamos el patrón Singleton.
		// Consiguiendo de esta forma que exista una única instancia de la clase
		// controlador que es accesible globalmente.
		public static AppChat getInstancia() {
			if (unicaInstancia == null)
				unicaInstancia = new AppChat();
			return unicaInstancia;
		}
		
		/* Inicializamos los adaptadores
		private void inicializarAdaptadores() {
			FactoriaDAO factoria = null;
			try {
				factoria = FactoriaDAO.getInstancia(FactoriaDAO.DAO_TDS);
			} catch (DAOException e) {
				e.printStackTrace();
			}
*/
	
	public static boolean login(String telefono, String contraseña) {
		
		return true;
		
	}
	


	
	
	
}
