package parqueadero.dominio;

import java.util.Calendar;
import java.util.Date;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import parqueadero.dominio.reposistorio.RepositorioFacturacion;
import parqueadero.dominio.utilidad.UtilidadFecha;
import parqueadero.entidad.FacturacionEntity;

@Repository("webService.dominio.ReciboDelParqueadero")
public class ReciboDelParqueadero {
	public static final double VALOR_DIA_CARRO = 2000;
	public static final double VALOR_DIA_MOTO = 500;
	public static final double VALOR_HORA_CARRO = 1000;
	public static final double VALOR_HORA_MOTO = 100;

	private ModelMapper modelMapper = new ModelMapper();

	@Autowired
	@Qualifier("webService.dominio.ReglaMotoAltoCilindraje")
	private ReglaMotoAltoCilindraje reglaMotoAltoCilindraje;

	@Autowired
	@Qualifier("webService.dominio.reposistorio.RepositorioFacturacion")
	private RepositorioFacturacion repositorioFacturacion;

	public void crearFacturaDelVehiculo(int id) {
		Facturacion facturacion = new Facturacion();
		facturacion.setFechaEntrada(fechaActual());
		facturacion.setFechaSalida(fechaActual());
		facturacion.setIdVehiculo(id);
		facturacion.setPrecio(0);
		FacturacionEntity facturacionEntity = modelMapper.map(facturacion, FacturacionEntity.class);
		repositorioFacturacion.save(facturacionEntity);
	}

	public Date fechaActual() {
		Calendar calendario = Calendar.getInstance();
		Date date = calendario.getTime();
		return date;
	}

	public double calcularValorAPagarDelParqueaderoCarro(Facturacion facturacion) {
		double precio = facturacion.getPrecio();
		double numeroDias = UtilidadFecha.cantidadTotalDias(UtilidadFecha.dateToCalendar(facturacion.getFechaEntrada()),
				UtilidadFecha.dateToCalendar(fechaActual()));
		double numeroTotalHoras = UtilidadFecha.cantidadTotalHoras(
				UtilidadFecha.dateToCalendar(facturacion.getFechaEntrada()),
				UtilidadFecha.dateToCalendar(fechaActual()));
		precio += numeroDias * VALOR_DIA_CARRO;
		precio += numeroTotalHoras * VALOR_HORA_CARRO;
		return precio;
	}

	public double calcularValorAPagarDelParqueaderoMoto(Facturacion facturacion) {
		double precio = facturacion.getPrecio();
		double numeroDias = UtilidadFecha.cantidadTotalDias(UtilidadFecha.dateToCalendar(facturacion.getFechaEntrada()),
				UtilidadFecha.dateToCalendar(fechaActual()));
		double numeroTotalHoras = UtilidadFecha.cantidadTotalHoras(
				UtilidadFecha.dateToCalendar(facturacion.getFechaEntrada()),
				UtilidadFecha.dateToCalendar(fechaActual()));
		precio += numeroDias * VALOR_DIA_MOTO;
		precio += numeroTotalHoras * VALOR_HORA_MOTO;
		return precio;
	}

	public double calcularFacturaDeSalida(int idVehiculo, int tipoVehiculo) {
		double precio = 0;
		FacturacionEntity facturacionEntity = repositorioFacturacion.salidaDelVehiculo(idVehiculo);
		Facturacion facturacion = modelMapper.map(facturacionEntity, Facturacion.class);
		if (tipoVehiculo == 2) {
			precio = reglaMotoAltoCilindraje.calcularServicioDeMotodeAltoCilindraje(idVehiculo);
			facturacion.setPrecio(precio);
			precio += calcularValorAPagarDelParqueaderoMoto(facturacion);
			facturacion.setFechaSalida(fechaActual());
			facturacion.setPrecio(precio);
			facturacionEntity = modelMapper.map(facturacion, FacturacionEntity.class);
			repositorioFacturacion.save(facturacionEntity);
		} else {
			precio += calcularValorAPagarDelParqueaderoCarro(facturacion);
			facturacion.setFechaSalida(fechaActual());
			facturacion.setPrecio(precio);
			facturacionEntity = modelMapper.map(facturacion, FacturacionEntity.class);
			repositorioFacturacion.save(facturacionEntity);
		}
		return precio;
	}
}
