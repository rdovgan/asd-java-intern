package team.asd.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.reflections.Reflections;
import team.asd.constants.DateElement;
import team.asd.engine.ServicesScannerUtils;
import team.asd.exceptions.WrongArgumentException;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Set;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Test methods for IsCalendarService implementations")
class CalendarServiceTest {

	private static final Class<IsCalendarService> serviceClass = IsCalendarService.class;

	private static Stream<IsCalendarService> defineServices() {
		Reflections reflections = new Reflections("team.asd.service");
		Set<Class<? extends IsCalendarService>> classes = reflections.getSubTypesOf(serviceClass);
		ServicesScannerUtils<IsCalendarService> servicesScanner = new ServicesScannerUtils<>();
		return classes.stream().map(servicesScanner::defineServiceImplementations);
	}

	@ParameterizedTest
	@MethodSource("defineServices")
	void testNullValues(IsCalendarService calendarService) {
		assertNull(calendarService.toString(null));
		assertNull(calendarService.toLocalDate(null));
	}

	@ParameterizedTest
	@MethodSource("defineServices")
	void testDatesConvert(IsCalendarService calendarService) {
		String futureDateString = "2090-01-01";
		String pastDateString = "2000-01-01";
		String wrongDateFormatString = "2010-21-01";
		String wrongDatePatternString = "01/01/2010";
		LocalDate futureDate = LocalDate.of(2090, 1, 1);
		LocalDate pastDate = LocalDate.of(2000, 1, 1);

		assertNotNull(calendarService.toString(LocalDate.now()));
		assertEquals(futureDate, calendarService.toLocalDate(futureDateString));
		assertEquals(pastDate, calendarService.toLocalDate(pastDateString));
		assertNull(calendarService.toLocalDate(wrongDateFormatString));
		assertNull(calendarService.toLocalDate(wrongDatePatternString));

		assertEquals(futureDateString, calendarService.toString(futureDate));
		assertEquals(pastDateString, calendarService.toString(pastDate));
	}

	@ParameterizedTest
	@MethodSource("defineServices")
	void testDefineCountInRange(IsCalendarService calendarService) {
		assertThrows(WrongArgumentException.class, () -> calendarService.defineCountInRange(LocalDate.now(), LocalDate.MAX, null));
		assertThrows(WrongArgumentException.class, () -> calendarService.defineCountInRange(LocalDate.now(), null, ChronoUnit.WEEKS));
		assertThrows(WrongArgumentException.class, () -> calendarService.defineCountInRange(null, LocalDate.now(), ChronoUnit.WEEKS));

		LocalDate pastSaturdayDate = LocalDate.of(2000, 1, 1);
		LocalDate pastTuesdayDate = LocalDate.of(2000, 1, 4);
		assertEquals(0L, calendarService.defineCountInRange(pastSaturdayDate, pastTuesdayDate, ChronoUnit.WEEKS));

		long secondsInDay = 60 * 60 * 24;
		assertEquals(3 * secondsInDay, calendarService.defineCountInRange(LocalDate.now(), LocalDate.now().plusDays(3), ChronoUnit.SECONDS));
		assertEquals(2 * secondsInDay / 60, calendarService.defineCountInRange(LocalDate.now(), LocalDate.now().plusDays(2), ChronoUnit.MINUTES));
		assertEquals(1L, calendarService.defineCountInRange(LocalDate.now(), LocalDate.now().plusDays(7), ChronoUnit.WEEKS));
		assertEquals(7L, calendarService.defineCountInRange(LocalDate.now(), LocalDate.now().plusDays(7), ChronoUnit.DAYS));
	}


	@ParameterizedTest
	@MethodSource("defineServices")
	void testGetInfo(IsCalendarService calendarService) {
		assertThrows(WrongArgumentException.class, () -> calendarService.getInfo(null, DateElement.DAY_OF_WEEK));
		assertThrows(WrongArgumentException.class, () -> calendarService.getInfo(LocalDate.now(), null));

		assertEquals("Yes", calendarService.getInfo(LocalDate.of(2000, 1, 1), DateElement.IS_LEAP_YEAR));
		assertEquals("No", calendarService.getInfo(LocalDate.of(2100, 1, 1), DateElement.IS_LEAP_YEAR));
		assertEquals("Yes", calendarService.getInfo(LocalDate.of(2020, 1, 1), DateElement.IS_LEAP_YEAR));
		assertEquals("No", calendarService.getInfo(LocalDate.of(2022, 1, 1), DateElement.IS_LEAP_YEAR));

		assertTrue(calendarService.getInfo(LocalDate.of(2022, 1, 12), DateElement.MONTH).toLowerCase().startsWith("jan"));
		assertTrue(calendarService.getInfo(LocalDate.of(2002, 12, 12), DateElement.MONTH).toLowerCase().startsWith("dec"));

		assertEquals("1", calendarService.getInfo(LocalDate.of(1999, 1, 1), DateElement.WEEK_NUMBER));
		assertEquals("6", calendarService.getInfo(LocalDate.of(2000, 1, 31), DateElement.WEEK_NUMBER));
		assertEquals("5", calendarService.getInfo(LocalDate.of(2001, 1, 31), DateElement.WEEK_NUMBER));

		assertTrue(calendarService.getInfo(LocalDate.of(2000, 1, 3), DateElement.DAY_OF_WEEK).toLowerCase().startsWith("mon"));
		assertTrue(calendarService.getInfo(LocalDate.of(2004, 1, 31), DateElement.DAY_OF_WEEK).toLowerCase().startsWith("sat"));
	}


	@ParameterizedTest
	@MethodSource("defineServices")
	void testReformatToLocalDate(IsCalendarService calendarService) {
		assertThrows(DateTimeException.class, () -> calendarService.reformatToLocalDate(""));
		assertEquals(LocalDate.of(1000, 1, 1), calendarService.reformatToLocalDate("1st Jan 1000"));
		assertThrows(DateTimeException.class, () -> calendarService.reformatToLocalDate("1st Jan 999"));
		assertEquals(LocalDate.of(1999, 1, 2), calendarService.reformatToLocalDate("2ft Jan 1999"));
		assertThrows(DateTimeException.class, () -> calendarService.reformatToLocalDate("38th Jan 2000"));
		assertThrows(DateTimeException.class, () -> calendarService.reformatToLocalDate("1st Red 2000"));
		assertEquals(LocalDate.of(3000, 12, 8), calendarService.reformatToLocalDate("8th Dec 3000"));
		assertThrows(DateTimeException.class, () -> calendarService.reformatToLocalDate("8th Dec 3001"));
		assertThrows(DateTimeException.class, () -> calendarService.reformatToLocalDate("Apr 5th 2020"));
		assertThrows(DateTimeException.class, () -> calendarService.reformatToLocalDate("10th May 2020T"));
		assertEquals(LocalDate.of(2020, 5, 10), calendarService.reformatToLocalDate("10th May 2020"));
	}
}