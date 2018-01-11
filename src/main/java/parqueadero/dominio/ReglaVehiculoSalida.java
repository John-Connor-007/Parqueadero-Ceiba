package parqueadero.dominio;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import parqueadero.dominio.exception.VehiculoException;
import parqueadero.dominio.reposistorio.RepositorioVehiculo;
import parqueadero.entidad.VehiculoEntity;



@Repository("webService.dominio.ReglaVehiculoSalida")
public class ReglaVehiculoSalida {
	public static final String NO_HAY_VEHICULOS = "No hay ningún vehiculo en el parqueadero";
	
	@Autowired
	@Qualifier("webService.dominio.reposistorio.RepositorioVehiculo")
	private RepositorioVehiculo repositorioVehiculo;
	
	@Autowired
	@Qualifier("webService.dominio.ReciboDelParqueadero")
	private ReciboDelParqueadero reciboDelParqueadero; 
	
	private ModelMapper modelMapper = new ModelMapper();
		
	public void sacarVehiculoDelParqueadero (int id) throws Exception{
		VehiculoEntity vehiculoEntity =  repositorioVehiculo.buscarVehiculoParaSalir(id);
		Vehiculo vehiculo = modelMapper.map(vehiculoEntity, Vehiculo.class);
		if(vehiculo != null){
			int tipoVehiculo = vehiculo.getTipoVehiculo();
			vehiculo.setEstadoVehiculo(false);
			vehiculoEntity = modelMapper.map(vehiculo, VehiculoEntity.class);
			repositorioVehiculo.save(vehiculoEntity);
			reciboDelParqueadero.calcularFacturaDeSalida(id, tipoVehiculo);
		}
		else
			throw new VehiculoException(NO_HAY_VEHICULOS);
	}
	

		
}
