package parqueadero.dominio;

import java.util.Calendar;
import java.util.Date;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import parqueadero.dominio.exception.VehiculoException;
import parqueadero.dominio.reposistorio.RepositorioVehiculo;
import parqueadero.dominio.utilidad.UtilidadFecha;
import parqueadero.entidad.VehiculoEntity;



@Repository("webService.dominio.ReglaVehiculoEntrada")
public class ReglaVehiculoEntrada {

	public static final String PLACAEXCEPTION = "no puede ingresar porque no está en un dia hábil";
	public static final String DEBE_LLENAR_LOS_CAMPOS = "Debe llenar los campos del vehiculo.";
	public static final String NO_HAY_CUPOS = "No hay cupos disponibles para el vehiculo.";

	private ModelMapper modelMapper = new ModelMapper();

	@Autowired
	@Qualifier("webService.dominio.reposistorio.RepositorioVehiculo")
	private RepositorioVehiculo repositorioVehiculo;

	@Autowired
	@Qualifier("webService.dominio.ReciboDelParqueadero")
	private ReciboDelParqueadero reciboDelParqueadero;

	private ValidadorDelPaqueadero validadorDelParqueadero;

	public void permitirEntradaDeVehiculo(Vehiculo vehiculo) throws Exception {
		validadorDelParqueadero = new ValidadorDelPaqueadero();
		try {
			if (vehiculo != null) {
				if (this.tipoVehiculo(vehiculo)) {
					if (validadorDelParqueadero.placaQueComienzanPorA(vehiculo.getPlaca())) {
						this.crearEntradaDelVehiculo(vehiculo);
					} else {
						if (this.fechaDeEntradaDelVehiculo()) {
							this.crearEntradaDelVehiculo(vehiculo);
						} else {
							throw new VehiculoException(PLACAEXCEPTION);
						}
					}
				}
			} else {
				throw new VehiculoException(NO_HAY_CUPOS);
			}
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

	public void crearEntradaDelVehiculo(Vehiculo vehiculo) {
		int id = 0;
		VehiculoEntity vehiculoEntity = modelMapper.map(vehiculo, VehiculoEntity.class);
		repositorioVehiculo.save(vehiculoEntity);
		id = repositorioVehiculo.obtenerElVehiculoQueApenasEntro(vehiculo.getPlaca());
		reciboDelParqueadero.crearFacturaDelVehiculo(id);
	}

	public boolean fechaDeEntradaDelVehiculo() {
		Calendar calendario = Calendar.getInstance();
		Date fechaEntrada = calendario.getTime();
		return UtilidadFecha.placaDeExceptionDelVehiculo(fechaEntrada) ? true : false;
	}

	public boolean tipoVehiculo(Vehiculo vehiculo) {
		int cantidadVehiculo = this.cantidadVehiculo();
		if (vehiculo.getTipoVehiculo() == 1) {
			return (validadorDelParqueadero.disponibilidadDelParqueaderoDeCarro(cantidadVehiculo));
		} else
			return (validadorDelParqueadero.disponibilidadDelParqueaderoDeMoto(cantidadVehiculo));
	}

	public int cantidadVehiculo() {
		int cantidadVehiculo = 0;
		cantidadVehiculo = repositorioVehiculo.obtenerCatidadDeVehiculoParqueados();
		return cantidadVehiculo > 0 ? cantidadVehiculo : 0;
	}
}
