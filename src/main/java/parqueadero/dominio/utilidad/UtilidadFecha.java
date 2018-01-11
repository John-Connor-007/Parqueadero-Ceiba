package parqueadero.dominio.utilidad;

import java.util.Calendar;
import java.util.Date;

public class UtilidadFecha {

	public static boolean placaDeExceptionDelVehiculo(Date fecha) {
		Date fecha1 = fecha;
		Calendar calendar2 = dateToCalendar(fecha1);
		return (calendar2.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY
				|| calendar2.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY) ? true : false;
	}

	public static Calendar dateToCalendar(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar;
	}

	public static double cantidadTotalDias(Calendar fechaInicial, Calendar fechaFinal) {
		long totalDias = 0;
		totalDias = ((fechaFinal.getTimeInMillis() - fechaInicial.getTimeInMillis()) / 1000 / 60 / 60/ 24);
		return (int) totalDias;
	}

	public static long cantidadTotalHoras(Calendar fechaInicial, Calendar fechaFinal) {
		long totalMinutos = 0;
		totalMinutos = ((fechaFinal.getTimeInMillis() - fechaInicial.getTimeInMillis()) / 1000 / 60 / 60);
		return (int) totalMinutos;
	}

}
