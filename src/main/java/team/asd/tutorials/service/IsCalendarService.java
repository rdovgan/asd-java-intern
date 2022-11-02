package team.asd.tutorials.service;

import team.asd.tutorials.constants.DateElement;
import team.asd.tutorials.exceptions.WrongArgumentException;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public interface IsCalendarService {

	/**
	 * Converts {@link LocalDate} parameter to {@link String} using format `yyyy-MM-dd`
	 * If provided parameter is null, null value should be returned
	 *
	 * @param date object to convert
	 * @return string value of date
	 */
	String toString(LocalDate date);

	/**
	 * Converts {@link String} parameter to {@link LocalDate} using format `yyyy-MM-dd`
	 * If provided parameter is null, null value should be returned
	 * If wrong string value or wrong pattern was used, should return null
	 *
	 * @param stringDate parameter to convert
	 * @return date object converted from String
	 */
	LocalDate toLocalDate(String stringDate);

	/**
	 * Defines count of {@link ChronoUnit} in provided date range
	 *
	 * @param fromDate start of range
	 * @param toDate end of range
	 * @param unit to calculate count
	 * @return count of calculated {@link ChronoUnit} in provided range
	 * @throws WrongArgumentException in case when {@link ChronoUnit} parameter is wrong
	 */
	long defineCountInRange(LocalDate fromDate, LocalDate toDate, ChronoUnit unit);

	/**
	 * Defines date info based on {@param dateElement} parameter.
	 * - If {@param dateElement} is DAY_OF_WEEK, returns name of day
	 * - If {@param dateElement} is WEEK_NUMBER, returns weeks number from start of the year
	 * - If {@param dateElement} is MONTH, returns month name
	 * - If {@param dateElement} is IS_LEAP_YEAR, returns 'Yes' or 'No'
	 *
	 * @param date to get info from
	 * @param dateElement [DAY_OF_WEEK, WEEK_NUMBER, MONTH, IS_LEAP_YEAR]
	 * @return date info based on {@param dateElement} parameter
	 * @throws WrongArgumentException is case when any parameter is wrong or null
	 */
	String getInfo(LocalDate date, DateElement dateElement);

	/**
	 * Convert the date string to the {@link LocalDate}.
	 * Date string is provided in human-readable format: "`Day` `Month` `Year`", e.g. "20th Oct 2052"
	 * - `Day` is in set "1st", "2nd", "3rd",  ...,  "31st"}
	 * - `Month` is in set {"Jan", "Feb", ..., "Nov", "Dec"}
	 * - `Year` is in range [1000, 3000]
	 *
	 * @param dateString a date string in the form (`Day` `Month` `Year`), e.g. "20th Oct 2052"
	 * @return {@link LocalDate} object
	 * @throws DateTimeException is case of invalid values or ranges
	 */
	LocalDate reformatToLocalDate(String dateString) throws DateTimeException;
}
