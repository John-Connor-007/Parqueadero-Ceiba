package unitarias;

import java.util.Calendar;
import java.util.Date;
import org.junit.Assert;
import org.junit.Test;
import parqueadero.dominio.Facturacion;
import parqueadero.dominio.ReciboDelParqueadero;
import parqueadero.dominio.ReglaVehiculoEntrada;
import parqueadero.dominio.ValidadorDelPaqueadero;

public class VigilanteTest {
	
	private ReglaVehiculoEntrada reglaVehiculoEntrada;
	private ValidadorDelPaqueadero validadorDelParqueadero;
	private ReciboDelParqueadero reciboDelParqueadero;
	
	@Test
	public void placaComienzaPorABuenaTest(){
		// Arrange
		validadorDelParqueadero = new ValidadorDelPaqueadero();
		String placa = "AFG696";
		// Act 
		boolean respuesta = validadorDelParqueadero.placaQueComienzanPorA(placa);
		// Assert
		Assert.assertFalse(respuesta);
	}
	
	@Test 
	public void placaComienzaPorAFallandoTest(){
		//Arrange
		validadorDelParqueadero = new ValidadorDelPaqueadero();
		String placa = "FGT696";
		// Act 
		boolean respuesta = validadorDelParqueadero.placaQueComienzanPorA(placa);
		// Assert
		Assert.assertTrue(respuesta);
	}
	
	@Test
	public void fechaEntradaTest(){
		//Arrage
		reglaVehiculoEntrada = new ReglaVehiculoEntrada();
		//Act
		boolean respuesta = reglaVehiculoEntrada.fechaDeEntradaDelVehiculo();
		//Assert
		Assert.assertTrue(respuesta);
	}
	
	
	@Test
	public void cuposdeCarrosTest(){
		//Arrage
		validadorDelParqueadero = new ValidadorDelPaqueadero();
		int cantidadCarro = 19;
		// Act
		boolean respuesta = validadorDelParqueadero.disponibilidadDelParqueaderoDeCarro(cantidadCarro);
		// Assert
		Assert.assertTrue(respuesta);
	}
	
	@Test
	public void cuposdeMotosTest(){
		//Arrage
		validadorDelParqueadero = new ValidadorDelPaqueadero();
		int cantidadMoto = 9;
		// Act
		boolean respuesta = validadorDelParqueadero.disponibilidadDelParqueaderoDeMoto(cantidadMoto);
		// Assert
		Assert.assertTrue(respuesta);
	}
	
	@Test
	public void calcularValorAPagarDelParqueaderoCarroTest() {
		reciboDelParqueadero = new ReciboDelParqueadero();
		Calendar calendario = Calendar.getInstance();
		calendario.add(Calendar.HOUR, -48);
		Date fechaEntrada = calendario.getTime();
		Facturacion facturacion = new Facturacion(); 
		facturacion.setFechaEntrada(fechaEntrada);
		double respuesta = reciboDelParqueadero.calcularValorAPagarDelParqueaderoCarro(facturacion);
		System.out.println(respuesta);
		Assert.assertNotNull(respuesta);
	}
	
	
}
