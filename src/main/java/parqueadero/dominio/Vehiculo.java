package parqueadero.dominio;

public class Vehiculo {
	
	private int id;
	private String placa;
	private boolean estadoVehiculo;
	private int tipoVehiculo;
	private int cilindraje;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPlaca() {
		return placa;
	}
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	public boolean isEstadoVehiculo() {
		return estadoVehiculo;
	}
	public void setEstadoVehiculo(boolean estadoVehiculo) {
		this.estadoVehiculo = estadoVehiculo;
	}
	public int getTipoVehiculo() {
		return tipoVehiculo;
	}
	public void setTipoVehiculo(int tipoVehiculo) {
		this.tipoVehiculo = tipoVehiculo;
	}
	public int getCilindraje() {
		return cilindraje;
	}
	public void setCilindraje(int cilindraje) {
		this.cilindraje = cilindraje;
	}
}
