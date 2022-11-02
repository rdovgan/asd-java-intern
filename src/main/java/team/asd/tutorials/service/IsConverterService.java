package team.asd.tutorials.service;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public interface IsConverterService {

	/**
	 * Converts Integer value into String. If provided value is null, the same value should return.
	 *
	 * @param value of Integer
	 * @return string value or null
	 */
	String convertIntegerIntoString(Integer value);

	/**
	 * Converts String value into Integer. If provided value is null, the same value should return.
	 *
	 * @param value as string
	 * @return integer value
	 * @throws NumberFormatException in case when provided value is not numeric
	 */
	Integer convertStringIntoInteger(String value);

	/**
	 * Converts String value into Double. If provided value is null, the same value should return.
	 *
	 * @param value as string
	 * @return double value
	 * @throws NumberFormatException in case when provided value is not numeric
	 */
	Double convertStringIntoDouble(String value);

	/**
	 * Converts string value into {@link LocalDate}. Should return null if null parameter was provided.
	 * Provided value should be in the next format: "1970-01-21"
	 *
	 * @param dateString string value of date
	 * @return date value in {@link LocalDate} format
	 * @throws DateTimeParseException in case when provided string date is incorrect
	 */
	LocalDate convertStringIntoLocalDate(String dateString) throws DateTimeParseException;

}
