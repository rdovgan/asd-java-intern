package team.asd.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class ConverterService implements IsConverterService {

	@Override
	public String convertIntegerIntoString(Integer value) {
		return value != null ? String.valueOf(value) : null;
	}

	@Override
	public Integer convertStringIntoInteger(String value) {
		if (value == null)
			return null;

		int number;
		number = Integer.parseInt(value);
		return number;
	}

	@Override
	public Double convertStringIntoDouble(String value) {
		if (value == null)
			return null;

		double number;
		number = Double.parseDouble(value);
		return number;
	}

	@Override
	public LocalDate convertStringIntoLocalDate(String dateString) throws DateTimeParseException {
		if (dateString == null)
			return null;

		LocalDate date;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		date = LocalDate.parse(dateString, formatter);
		return date;
	}
}
