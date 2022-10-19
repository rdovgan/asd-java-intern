package team.asd.service;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class ConverterService implements IsConverterService {

	@Override
	public String convertIntegerIntoString(Integer value) {
		if (value == null) {
			return null;
		}
		return value.toString();
	}

	@Override
	public Integer convertStringIntoInteger(String value) {
		if (value == null) {
			return null;
		}
		return Integer.valueOf(value);
	}

	@Override
	public Double convertStringIntoDouble(String value) {
		if (value == null) {
			return null;
		}
		return Double.valueOf(value);
	}

	@Override
	public LocalDate convertStringIntoLocalDate(String dateString) throws DateTimeParseException {
		if (dateString == null) {
			return null;
		}
		return LocalDate.parse(dateString);
	}
}
