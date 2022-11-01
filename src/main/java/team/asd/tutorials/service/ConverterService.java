package team.asd.tutorials.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class ConverterService implements IsConverterService {
	private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	@Override
	public String convertIntegerIntoString(Integer value) {
		return value == null ? null : Integer.toString(value);
	}

	@Override
	public Integer convertStringIntoInteger(String value) {
		return value == null ? null : Integer.parseInt(value);
	}

	@Override
	public Double convertStringIntoDouble(String value) {
		return value == null ? null : Double.parseDouble(value);
	}

	@Override
	public LocalDate convertStringIntoLocalDate(String dateString) throws DateTimeParseException {
		return dateString == null ? null : LocalDate.parse(dateString, DATE_FORMAT);
	}
}
