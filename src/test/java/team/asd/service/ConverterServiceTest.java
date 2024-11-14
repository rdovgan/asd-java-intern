package team.asd.service;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.reflections.Reflections;
import team.asd.engine.ServicesScannerUtils;

import java.time.format.DateTimeParseException;
import java.util.Set;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ConverterServiceTest {

	private static final Class<IsConverterService> serviceClass = IsConverterService.class;


	private static final Integer INT_VALUE = 19748;
	private static final String STRING_VALUE = "19748";

	private static Stream<IsConverterService> defineServices() {
		Reflections reflections = new Reflections("team.asd.service");
		Set<Class<? extends IsConverterService>> classes = reflections.getSubTypesOf(serviceClass);
		ServicesScannerUtils<IsConverterService> servicesScanner = new ServicesScannerUtils<>();
		return classes.stream().map(servicesScanner::defineServiceImplementations);
	}

	//String convertIntegerIntoString(Integer value);

	@ParameterizedTest
	@MethodSource("defineServices")
	void testConvertIntegerToStringNotNull(IsConverterService converterService) {
		assertNotNull(converterService.convertIntegerIntoString(INT_VALUE), "Result should be not null with provided Integer value");
	}

	@ParameterizedTest
	@MethodSource("defineServices")
	void testConvertIntegerToString(IsConverterService converterService) {
		assertEquals(STRING_VALUE, converterService.convertIntegerIntoString(INT_VALUE), "Wrong converting from Integer to String");
	}

	@ParameterizedTest
	@MethodSource("defineServices")
	void testConvertIntegerToStringWithNullParameter(IsConverterService converterService) {
		assertNull(converterService.convertIntegerIntoString(null), "For null value converter should return null");
	}

	//Integer convertStringIntoInteger(String value);

	@ParameterizedTest
	@MethodSource("defineServices")
	void testConvertStringIntoInteger(IsConverterService converterService) {
		assertNull(converterService.convertStringIntoInteger(null), "For null value converter should return null");
		assertNotNull(converterService.convertStringIntoInteger("123"), "Value should be converted");
		assertThrows(NumberFormatException.class, () -> converterService.convertStringIntoInteger("A"), "For wrong numeric values converter should throw a NumberFormatException exception");
	}

	//Double convertStringIntoDouble(String value);

	@ParameterizedTest
	@MethodSource("defineServices")
	void testConvertStringIntoDouble(IsConverterService converterService) {
		assertNull(converterService.convertStringIntoDouble(null), "For null value converter should return null");
		assertNotNull(converterService.convertStringIntoDouble("123.12"), "Value should be converted");
		assertThrows(NumberFormatException.class, () -> converterService.convertStringIntoDouble("A"), "For null value converter should return null");
	}

	//LocalDate convertStringIntoLocalDate(String dateString) throws DateTimeParseException;

	@ParameterizedTest
	@MethodSource("defineServices")
	void testConvertStringIntoLocalDate(IsConverterService converterService) {
		assertNull(converterService.convertStringIntoLocalDate(null), "For null value converter should return null");
		assertNotNull(converterService.convertStringIntoLocalDate("2021-02-13"), "Value should be converted");
		assertThrows(DateTimeParseException.class, () -> converterService.convertStringIntoLocalDate("01-02-2021"), "yyy-MM-dd parser should be used");
		assertThrows(DateTimeParseException.class, () -> converterService.convertStringIntoLocalDate("01/02/2021"), "yyy-MM-dd parser should be used");
		assertThrows(DateTimeParseException.class, () -> converterService.convertStringIntoLocalDate("2021-13-12"), "yyy-MM-dd parser should be used");
		assertThrows(DateTimeParseException.class, () -> converterService.convertStringIntoLocalDate("A"), "For null value converter should return null");
	}

}
