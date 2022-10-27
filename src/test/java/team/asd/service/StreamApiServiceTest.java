package team.asd.service;

import org.apache.commons.collections4.CollectionUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.reflections.Reflections;
import team.asd.data.ProductData;
import team.asd.engine.ServicesScannerUtils;
import team.asd.entities.IsProduct;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Test methods for IsStreamApiService implementations")
public class StreamApiServiceTest {

	private static final Random random = new Random();

	private static Stream<IsStreamApiService> defineStreamApiServices() {
		Reflections reflections = new Reflections("team.asd.service");
		Set<Class<? extends IsStreamApiService>> classes = reflections.getSubTypesOf(IsStreamApiService.class);
		ServicesScannerUtils<IsStreamApiService> servicesScanner = new ServicesScannerUtils<>();
		return classes.stream().map(servicesScanner::defineServiceImplementations);
	}

	//Stream<?> getNonNullStreamItems(Collection<?> collection);

	@ParameterizedTest
	@MethodSource("defineStreamApiServices")
	public void testGetNonNullStreamItemsWhenNullParameterWasProvided(IsStreamApiService streamApiService) {
		Stream<?> resultStream = streamApiService.getNonNullStreamItems(null);
		assertNotNull(resultStream, "Result should not be null");
		assertEquals(0, resultStream.count(), "Result should be an empty list when null parameter was provided");
	}

	@ParameterizedTest
	@MethodSource("defineStreamApiServices")
	public void testGetNonNullStreamItemsWhenEmptyListWasProvided(IsStreamApiService streamApiService) {
		Stream<?> resultStream = streamApiService.getNonNullStreamItems(new ArrayList<>());
		assertNotNull(resultStream, "Result should not be null");
		assertEquals(0, resultStream.count(), "Result should be an empty list when empty list was provided");
	}

	@ParameterizedTest
	@MethodSource("defineStreamApiServices")
	public void testGetNonNullStreamItemsWithOneElement(IsStreamApiService streamApiService) {
		final int productSize = 1;
		Stream<?> resultStream = streamApiService.getNonNullStreamItems(ProductData.defineProductList(productSize));
		Object product = resultStream.findFirst().orElse(null);
		assertNotNull(product, "No objects in result stream");
		assertTrue(product instanceof IsProduct, "Object is not equals to the provided type");
	}

	@ParameterizedTest
	@MethodSource("defineStreamApiServices")
	public void testGetNonNullStreamItems(IsStreamApiService streamApiService) {
		final int productSize = 10;
		Stream<?> resultStream = streamApiService.getNonNullStreamItems(ProductData.defineProductList(productSize));
		assertEquals(productSize, resultStream.count(), "Result should be an empty list when empty list was provided");
	}

	//List<Integer> defineListFromRange(Integer start, Integer end) throws NumberFormatException;

	@ParameterizedTest
	@MethodSource("defineStreamApiServices")
	public void testDefineListFromRangeWithNullValues(IsStreamApiService streamApiService) {
		List<Integer> resultList = streamApiService.defineListFromRange(null, null);
		assertNotNull(resultList, "Result should be an empty list when null value was provided");
		assertEquals(0, resultList.size(), "Result should be an empty list when null value was provided");
	}

	@ParameterizedTest
	@MethodSource("defineStreamApiServices")
	public void testDefineListFromRange(IsStreamApiService streamApiService) {
		final Integer firstValue = random.nextInt(100);
		final Integer secondValue = random.nextInt(20) + firstValue;
		List<Integer> resultList = streamApiService.defineListFromRange(firstValue, secondValue);
		assertEquals(Math.abs(secondValue - firstValue) + 1, resultList.size(), "Wrong size of result list");
		assertEquals((firstValue + secondValue) * (secondValue - firstValue + 1) / 2, resultList.stream().reduce(0, Integer::sum),
				"Range of numbers is incorrect");
	}

	@ParameterizedTest
	@MethodSource("defineStreamApiServices")
	public void testDefineListFromRangeWithNegativeAndRevertedValues(IsStreamApiService streamApiService) {
		Integer firstValue = random.nextInt(10) + 30;
		Integer secondValue = random.nextInt(20) - firstValue;
		List<Integer> resultList = streamApiService.defineListFromRange(firstValue, secondValue);
		assertEquals(Math.abs(secondValue - firstValue) + 1, resultList.size(), "Wrong size of result list");
	}

	@ParameterizedTest
	@MethodSource("defineStreamApiServices")
	public void testDefineListFromRangeWithEqualsValues(IsStreamApiService streamApiService) {
		Integer firstValue = random.nextInt(100);
		List<Integer> resultList = streamApiService.defineListFromRange(firstValue, firstValue);
		assertEquals(1, resultList.size(), "Wrong size of result list");
		assertEquals(firstValue, resultList.stream().findFirst().orElse(null), "Incorrect value");
	}

	//List<Integer> convertStringListToIntegerList(List<String> stringList);

	@ParameterizedTest
	@MethodSource("defineStreamApiServices")
	public void testConvertStringListToIntegerListWithEmptyList(IsStreamApiService streamApiService) {
		assertTrue(CollectionUtils.isEmpty(streamApiService.convertStringListToIntegerList(new ArrayList<>())),
				"Result should be an empty list when empty list was provided");
	}

	@ParameterizedTest
	@MethodSource("defineStreamApiServices")
	public void testConvertStringListToIntegerListWithProvidedNullParameter(IsStreamApiService streamApiService) {
		assertTrue(CollectionUtils.isEmpty(streamApiService.convertStringListToIntegerList(null)),
				"Result should be an empty list when null value was provided");
	}

	@ParameterizedTest
	@MethodSource("defineStreamApiServices")
	public void testConvertStringListToIntegerList(IsStreamApiService streamApiService) {
		int correctValuesCount = 7;
		List<String> stringList = Arrays.asList("2", "0", "76498", "0x9a2f", null, "", "4", "-78", "09", "8.9", "4_000", "22");
		List<Integer> convertedIntegerList = streamApiService.convertStringListToIntegerList(stringList);
		assertEquals(correctValuesCount, convertedIntegerList.size(), "Wrong size for converted list");
		assertTrue(convertedIntegerList.stream().allMatch(Objects::nonNull), "There should not be a null values in result list");
	}

	@ParameterizedTest
	@MethodSource("defineStreamApiServices")
	public void testConvertLongStringListToIntegerList(IsStreamApiService streamApiService) {
		int correctValuesCount = 0;
		List<String> stringList = List.of("329124942389234892348934289342892348923489234892348923489");
		List<Integer> convertedIntegerList = streamApiService.convertStringListToIntegerList(stringList);
		assertEquals(correctValuesCount, convertedIntegerList.size(), "Wrong size for converted list");
		assertTrue(convertedIntegerList.stream().allMatch(Objects::nonNull), "There should not be a null values in result list");
	}

	@ParameterizedTest
	@MethodSource("defineStreamApiServices")
	public void testConvertStringListToIntegerWithZeros(IsStreamApiService streamApiService) {
		int correctValuesCount = 1;
		List<String> stringList = List.of("-00001");
		List<Integer> convertedIntegerList = streamApiService.convertStringListToIntegerList(stringList);
		assertEquals(correctValuesCount, convertedIntegerList.size(), "Wrong size for converted list");
		assertTrue(convertedIntegerList.stream().allMatch(Objects::nonNull), "There should not be a null values in result list");
	}


	//IntStream convertStringToLegalChars(String value);

	@ParameterizedTest
	@MethodSource("defineStreamApiServices")
	public void testConvertStringToLegalCharsWithNullParameter(IsStreamApiService streamApiService) {
		assertNotNull(streamApiService.convertStringToLegalChars(null));
	}

	@ParameterizedTest
	@MethodSource("defineStreamApiServices")
	public void testConvertStringToLegalCharsWithEmptyStringParameter(IsStreamApiService streamApiService) {
		assertNotNull(streamApiService.convertStringToLegalChars(""));
	}

	@ParameterizedTest
	@MethodSource("defineStreamApiServices")
	public void testConvertStringToLegalCharsWithAllWrongCharacters(IsStreamApiService streamApiService) {
		assertEquals(0, streamApiService.convertStringToLegalChars("日本語のキーボード").count(), "Wrong character wasn't skipped");
	}

	@ParameterizedTest
	@MethodSource("defineStreamApiServices")
	public void testConvertStringToLegalCharsWithSomeWrongCharacters(IsStreamApiService streamApiService) {
		final String correctLetters = "TheseLetter5AreC0rRect";
		final String wrongLetters = "βüτ τηéšė äρę ńòτ =(";
		assertEquals(correctLetters.length(), streamApiService.convertStringToLegalChars(correctLetters + wrongLetters).count(), "Wrong character size");
	}

	//BigDecimal sumAllValues(List<BigDecimal> values);

	@ParameterizedTest
	@MethodSource("defineStreamApiServices")
	public void testSumAllValues(IsStreamApiService streamApiService) {
		List<BigDecimal> nullObjects = Arrays.asList(null, null);
		assertThrows(NullPointerException.class, () -> streamApiService.sumAllValues(null));
		assertEquals(BigDecimal.ZERO, streamApiService.sumAllValues(nullObjects));
		assertEquals(BigDecimal.ZERO, streamApiService.sumAllValues(new ArrayList<>()));
		List<BigDecimal> bigDecimals = Arrays.asList(null, BigDecimal.ONE, new BigDecimal("-9"), new BigDecimal("10"));
		assertEquals(new BigDecimal("2").setScale(2, RoundingMode.HALF_UP), streamApiService.sumAllValues(bigDecimals).setScale(2, RoundingMode.HALF_UP));
	}

	//Stream<LocalDate> sortLocalDateList(List<LocalDate> listOfDates);

	@ParameterizedTest
	@MethodSource("defineStreamApiServices")
	public void testSortLocalDateList(IsStreamApiService streamApiService) {
		assertEquals(0, streamApiService.sortLocalDateList(null).count());
		List<LocalDate> dates = Arrays.asList(LocalDate.now(), LocalDate.now().plusDays(-10), LocalDate.now().plusDays(-1), null, LocalDate.MAX);
		assertEquals(4, streamApiService.sortLocalDateList(dates).count(), "Null values should be skipped");
		assertTrue(isSorted(streamApiService.sortLocalDateList(dates).collect(Collectors.toList())));
	}

	//Stream<LocalDate> skipDaysFromSpecifiedDate(List<LocalDate> listOfDates, LocalDate date, Integer daysToSkip)

	@ParameterizedTest
	@MethodSource("defineStreamApiServices")
	public void testSkipDaysFromSpecifiedDate(IsStreamApiService streamApiService) {
		List<LocalDate> dates = Arrays
				.asList(LocalDate.now(), LocalDate.now().plusDays(-10), LocalDate.now().plusDays(-1), null, LocalDate.MAX, LocalDate.now().plusDays(4));
		assertEquals(0, streamApiService.skipDaysFromSpecifiedDate(null, LocalDate.now(), 1).count());
		assertEquals(0, streamApiService.skipDaysFromSpecifiedDate(dates, null, 2).count());
		assertEquals(0, streamApiService.skipDaysFromSpecifiedDate(dates, LocalDate.now(), null).count());
		assertEquals(0, streamApiService.skipDaysFromSpecifiedDate(dates, LocalDate.now(), -2).count());
		assertEquals(1, streamApiService.skipDaysFromSpecifiedDate(dates, LocalDate.now(), 2).count());
		assertEquals(3, streamApiService.skipDaysFromSpecifiedDate(dates, LocalDate.now(), 0).count());
	}

	@ParameterizedTest
	@MethodSource("defineStreamApiServices")
	public void testCollectLists(IsStreamApiService streamApiService) {
		assertEquals(0, streamApiService.collectLists().size());
		List<Integer> firstList = Arrays.asList(1, 2, 3);
		assertEquals(firstList.size(), streamApiService.collectLists(firstList).size());
		List<Integer> secondList = Arrays.asList(2, 4, 5);
		List<Integer> thirdList = Arrays.asList(5, 6, 7);
		List<Integer> combinedList = new ArrayList<>(firstList);
		combinedList.addAll(secondList);
		assertEquals(3, streamApiService.collectLists(firstList, secondList).lastIndexOf(2));
		combinedList.addAll(thirdList);
		assertEquals(combinedList.size(), streamApiService.collectLists(firstList, secondList, thirdList).size());
	}

	@ParameterizedTest
	@MethodSource("defineStreamApiServices")
	public void testRemoveDuplicates(IsStreamApiService streamApiService) {
		String letterA = "A", letterB = "B", group = "ABBA";
		List<String> listWithDuplicates = Arrays.asList(letterA, letterB, letterA, letterA+letterB+letterB+letterA, group);
		List<String> listWithoutDuplicates = Arrays.asList(letterA, letterB, group);
		List<?> resultList = streamApiService.removeDuplicates(listWithDuplicates);
		assertEquals(listWithoutDuplicates.size(), resultList.size());
		assertTrue(resultList.contains(letterA));
		assertTrue(resultList.contains(letterB));
		assertTrue(resultList.contains(group));
		assertTrue(CollectionUtils.isEmpty(streamApiService.removeDuplicates(null)));
		assertNotNull(streamApiService.removeDuplicates(null));
	}

	private static boolean isSorted(List<LocalDate> listOfStrings) {
		return isSorted(listOfStrings, listOfStrings.size());
	}

	private static boolean isSorted(List<LocalDate> listOfStrings, int index) {
		if (index < 2) {
			return true;
		} else if (listOfStrings.get(index - 2).compareTo(listOfStrings.get(index - 1)) > 0) {
			return false;
		} else {
			return isSorted(listOfStrings, index - 1);
		}
	}
}
