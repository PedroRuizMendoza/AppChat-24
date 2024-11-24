package modelo;

public class DescuentoMensajes implements Descuento {

	@Override
	public double getDescuento(double precioInicial) {
		
		return 0.9 * precioInicial;
	}

}
