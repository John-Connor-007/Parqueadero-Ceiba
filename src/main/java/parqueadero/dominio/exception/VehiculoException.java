package parqueadero.dominio.exception;

public class VehiculoException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public VehiculoException(String message) {
		
		super(message);
	}
}
