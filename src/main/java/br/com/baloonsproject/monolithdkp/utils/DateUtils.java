package br.com.baloonsproject.monolithdkp.utils;

import java.nio.file.Path;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

	private DateUtils() {

	}

	public static Date parseDateStringToDate(String dateStr) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH, Integer.valueOf(dateStr.substring(3, 5)));
		cal.set(Calendar.MONTH, Integer.valueOf(dateStr.substring(0, 2)) - 1);
		cal.set(Calendar.YEAR, Integer.valueOf(dateStr.substring(6, 8)) + 2000);
		cal.set(Calendar.HOUR_OF_DAY, Integer.valueOf(dateStr.substring(9, 11)));
		cal.set(Calendar.MINUTE, Integer.valueOf(dateStr.substring(12, 14)));
		cal.set(Calendar.SECOND, Integer.valueOf(dateStr.substring(15, 17)));
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	public static Date getDateFromFilePath(Path f) {
		String dateString = f.getFileName().toString().replaceAll("\\D", "");
		Calendar cal = Calendar.getInstance();

		if (!dateString.isEmpty()) {
			cal.set(Calendar.DAY_OF_MONTH, Integer.valueOf(dateString.substring(0, 2)));
			cal.set(Calendar.MONTH, Integer.valueOf(dateString.substring(2, 4)) - 1);
			cal.set(Calendar.YEAR, Integer.valueOf(dateString.substring(4, 8)));
		}

		return cal.getTime();
	}
	
	public static Date getDateFromFileName(String fileName) {
		String dateString = fileName.replaceAll("\\D", "");
		Calendar cal = Calendar.getInstance();

		if (!dateString.isEmpty()) {
			cal.set(Calendar.DAY_OF_MONTH, Integer.valueOf(dateString.substring(0, 2)));
			cal.set(Calendar.MONTH, Integer.valueOf(dateString.substring(2, 4)) - 1);
			cal.set(Calendar.YEAR, Integer.valueOf(dateString.substring(4, 8)));
			
			cal.set(Calendar.HOUR, 0);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
			
			cal.set(Calendar.MILLISECOND, 0);
		}

		return cal.getTime();
	}
}
