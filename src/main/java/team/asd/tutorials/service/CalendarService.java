package team.asd.tutorials.service;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import team.asd.tutorials.constants.DateElement;
import team.asd.tutorials.exceptions.WrongArgumentException;

import java.text.DateFormatSymbols;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.time.temporal.WeekFields;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class CalendarService implements IsCalendarService {
	private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	@Override
	public String toString(LocalDate date) {
		return date == null ? null : date.format(DATE_FORMATTER);
	}

	@Override
	public LocalDate toLocalDate(String stringDate) {
		try {
			return stringDate == null ? null : LocalDate.parse(stringDate, DATE_FORMATTER);
		} catch (DateTimeParseException exception) {
			return null;
		}
	}

	@Override
	public long defineCountInRange(LocalDate fromDate, LocalDate toDate, ChronoUnit unit) {
		if (ObjectUtils.anyNull(unit, fromDate, toDate)) {
			throw new WrongArgumentException("Wrong parameters were provided");
		}
		return fromDate.atStartOfDay()
				.until(toDate.atStartOfDay(), unit);
	}

	@Override
	public String getInfo(LocalDate date, DateElement dateElement) {
		if (ObjectUtils.anyNull(date, dateElement)) {
			throw new WrongArgumentException("Wrong parameters were provided");
		}
		switch (dateElement) {
		case DAY_OF_WEEK:
			return date.getDayOfWeek().toString();
		case WEEK_NUMBER:
			return String.valueOf(date.get(WeekFields.of(Locale.getDefault())
					.weekOfWeekBasedYear()));
		case MONTH:
			return date.getMonth().toString();
		case IS_LEAP_YEAR:
			return date.isLeapYear() ? "Yes" : "No";
		default:
			return null;
		}
	}

	@Override
	public LocalDate reformatToLocalDate(String dateString) throws DateTimeException {
		if (StringUtils.isEmpty(dateString)) {
			throw new DateTimeException("Wrong date was provided");
		}
		List<String> shortMonths = Arrays.asList(new DateFormatSymbols().getShortMonths());
		String[] parsedDate = dateString.split(" ");

		int day = NumberUtils.toInt(parsedDate[0].substring(0, parsedDate[0].length() - 2));
		int month = shortMonths.indexOf(parsedDate[1]) + 1;
		int year = NumberUtils.toInt(parsedDate[2]);

		if (year < 1000 || year > 3000) {
			throw new DateTimeException("Wrong year was provided");
		}

		return LocalDate.of(year, month, day);
	}
}
