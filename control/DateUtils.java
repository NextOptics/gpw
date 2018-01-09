package gpw.control;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Calendar;
import java.time.format.DateTimeFormatter;

import java.util.ArrayList;
import gpw.model.Inflacao;
import gpw.dao.DaoInflacao;


/*
		********** IMPORTANTE metodos existentes em LocalDate
		se tempData é uma LocalDate,  pode-se fazer
		tempData.plusDays(2)   - Acrescenta 2 dias à data
		tempData.plusMonths(3) - Acrescenta 3 meses à data
*/

/*
		public static LocalDate dateToLocalDate(Date data)
		public static Date asDate(LocalDate localDate)
		public static Date asDate(LocalDateTime localDateTime)
		public static LocalDate asLocalDate(Date date)
		public static String asString(LocalDate localDate)
		public static LocalDate firstDateOfPreviousMonth(LocalDate localDate)
		public static LocalDate lastDateOfPreviousMonth(LocalDate localDate)
		public static LocalDate lastDateOfNextMonth(LocalDate localDate)
		public static LocalDate mudaDia(LocalDate localDate, int xDia)
		public static LocalDate mesSeguinte(LocalDate localDate){
		
		public static Double buscarMoc(LocalDate localDate)
 
*/

public class DateUtils {
	

	
	public static LocalDate dateToLocalDate(Date data) {
		Instant instante = Instant.ofEpochMilli(data.getTime());
		return LocalDateTime.ofInstant(instante, ZoneId.systemDefault()).toLocalDate();
	}
	
	public static Date asDate(LocalDate localDate) {
		return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
	}
	
	public static Date asDate(LocalDateTime localDateTime) {
		return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant()); 
	}

	public static LocalDate asLocalDate(Date date) {
		return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
	}

	public static LocalDateTime asLocalDateTime(Date date) {
		return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDateTime();
	}
	
	public static String asString(LocalDate localDate) {
		DateTimeFormatter  f = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		return localDate.format(f);	
	}

	public static LocalDate firstDateOfPreviousMonth(LocalDate localDate) {
		int ano, mes, dia;
		ano = localDate.getYear();
		mes = localDate.getMonthValue();
		dia = localDate.getDayOfMonth();
		
		Calendar calendario = Calendar.getInstance();
		calendario.set(ano, mes, dia);
		calendario.add(Calendar.MONTH, -2);
		calendario.set(Calendar.DATE, 1);
		return asLocalDate(calendario.getTime());
	}
	
	public static LocalDate lastDateOfPreviousMonth(LocalDate localDate) {
		int ano, mes, dia;
		ano = localDate.getYear();
		mes = localDate.getMonthValue();
//		dia = localDate.getDayOfMonth();
		dia = 27;
		
		Calendar calendario = Calendar.getInstance();
		calendario.set(ano, mes, dia);
		calendario.add(Calendar.MONTH, -2);
		calendario.set(Calendar.DATE, calendario.getActualMaximum(Calendar.DAY_OF_MONTH));
		return asLocalDate(calendario.getTime());
		
	}
	
	public static LocalDate lastDateOfNextMonth(LocalDate localDate) {
		int ano, mes, dia;
		ano = localDate.getYear();
		mes = localDate.getMonthValue();
//		dia = localDate.getDayOfMonth();
		dia = 27;
		
		
		Calendar calendario = Calendar.getInstance();
		calendario.set(ano, mes, dia);
		calendario.add(Calendar.MONTH, 0);
		calendario.set(Calendar.DATE, calendario.getActualMaximum(Calendar.DAY_OF_MONTH));
		return asLocalDate(calendario.getTime());
		
	}
	
	public static LocalDate mudaDia(LocalDate localDate, int xDia) {
		int ano, mes, dia;
		ano = localDate.getYear();
		mes = localDate.getMonthValue();
    	dia = xDia;
		if(dia >= 27) {dia =27;}
		Calendar calendario = Calendar.getInstance();
		calendario.set(ano, mes -1, dia);
		return asLocalDate(calendario.getTime());	
	}
	
	public static LocalDate mesSeguinte(LocalDate localDate){
		int ano, mes, dia;
		ano = localDate.getYear();
		mes = localDate.getMonthValue();
    	dia = localDate.getDayOfMonth();
		if(dia >= 27) {dia =27;}
		Calendar calendario = Calendar.getInstance();
		calendario.set(ano, mes, dia);
		return asLocalDate(calendario.getTime());	
	}
	
	public static double buscarMoc(LocalDate xData) {
		double valorMoc = 0.;			// Retorna zero se nao encontrar o moc correto
		ArrayList<Inflacao> indices = new ArrayList<Inflacao>();
		DaoInflacao inflacao = new DaoInflacao();
		indices = inflacao.buscarInfla();
		xData = lastDateOfPreviousMonth(xData);
		int i = 0;
		while(i < indices.size()){
			if(indices.get(i).getDataMoc().equals(xData))
				valorMoc = (Double)indices.get(i).getMoc();
			i++;
		}
		return valorMoc;
	}
}