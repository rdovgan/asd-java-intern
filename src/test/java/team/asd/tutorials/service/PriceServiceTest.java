package team.asd.tutorials.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.reflections.Reflections;
import team.asd.tutorials.data.PriceData;
import team.asd.tutorials.engine.ServicesScannerUtils;
import team.asd.tutorials.entities.IsPerDayPrice;
import team.asd.tutorials.entities.IsPrice;
import team.asd.tutorials.exceptions.WrongPriceException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Test methods for IsPriceService implementations")
public class PriceServiceTest {

	private static Stream<IsPriceService> definePriceServices() {
		Reflections reflections = new Reflections("team.asd.tutorials.service");
		Set<Class<? extends IsPriceService>> classes = reflections.getSubTypesOf(IsPriceService.class);
		ServicesScannerUtils<IsPriceService> servicesScanner = new ServicesScannerUtils<>();
		return classes.stream()
				.map(servicesScanner::defineServiceImplementations);
	}

	//Double defineAverageValueFromPerDayPrice(List<IsPerDayPrice> prices) throws WrongPriceException;


	@ParameterizedTest
	@MethodSource("definePriceServices")
	public void testDefineAverageValueFromPerDayPriceWithNullParameter(IsPriceService priceService) throws WrongPriceException {
		assertEquals(BigDecimal.ZERO, priceService.defineAverageValueFromPerDayPrice(null), "Zero value should be return if null list was provided");
	}

	@ParameterizedTest
	@MethodSource("definePriceServices")
	public void testDefineAverageValueFromPerDayPriceWithEmptyList(IsPriceService priceService) throws WrongPriceException {
		assertEquals(BigDecimal.ZERO, priceService.defineAverageValueFromPerDayPrice(new ArrayList<>()),
				"Zero value should be return if empty list was provided");
	}

	@ParameterizedTest
	@MethodSource("definePriceServices")
	public void testDefineAverageValueFromPerDayPrice(IsPriceService priceService) throws WrongPriceException {
		BigDecimal firstPriceAmount = new BigDecimal(199.95);
		IsPerDayPrice firstPerDayPrice = PriceData.definePerDayPrice(LocalDate.now(), firstPriceAmount);

		BigDecimal secondPriceAmount = new BigDecimal(800.02);
		IsPerDayPrice secondPerDayPrice = PriceData.definePerDayPrice(LocalDate.now()
				.plusYears(1), secondPriceAmount);

		BigDecimal thirdPriceAmount = new BigDecimal(500.03);
		IsPerDayPrice thirdPerDayPrice = PriceData.definePerDayPrice(LocalDate.now()
				.plusDays(2), thirdPriceAmount);

		BigDecimal averagePriceAmountPerDays = firstPriceAmount.add(secondPriceAmount)
				.add(thirdPriceAmount)
				.divide(new BigDecimal(3), RoundingMode.HALF_DOWN);

		List<IsPerDayPrice> perDayPriceList = Arrays.asList(firstPerDayPrice, secondPerDayPrice, thirdPerDayPrice);
		assertEquals(averagePriceAmountPerDays.setScale(2, BigDecimal.ROUND_HALF_UP), priceService.defineAverageValueFromPerDayPrice(perDayPriceList)
				.setScale(2, RoundingMode.HALF_UP), "Average per day price amount calculated incorrectly");
	}

	@ParameterizedTest
	@MethodSource("definePriceServices")
	public void testDefineAverageValueFromPerDayPriceWithEqualsDates(IsPriceService priceService) throws WrongPriceException {
		BigDecimal firstPriceAmount = new BigDecimal(45.94);
		IsPerDayPrice firstPerDayPrice = PriceData.definePerDayPrice(LocalDate.now(), firstPriceAmount);

		BigDecimal secondPriceAmount = new BigDecimal(98.02);
		IsPerDayPrice secondPerDayPrice = PriceData.definePerDayPrice(LocalDate.now()
				.plusYears(1), secondPriceAmount);

		BigDecimal thirdPriceAmount = new BigDecimal(131.43);
		IsPerDayPrice thirdPerDayPrice = PriceData.definePerDayPrice(LocalDate.now(), thirdPriceAmount);

		BigDecimal averagePriceAmountPerDays = firstPriceAmount.add(secondPriceAmount)
				.add(thirdPriceAmount)
				.divide(new BigDecimal(3), RoundingMode.HALF_DOWN);

		List<IsPerDayPrice> perDayPriceList = Arrays.asList(firstPerDayPrice, secondPerDayPrice, thirdPerDayPrice);
		assertThrows(WrongPriceException.class, () -> priceService.defineAverageValueFromPerDayPrice(perDayPriceList),
				"Exception should be thrown where equals per day price's dates were provided");
	}

	@ParameterizedTest
	@MethodSource("definePriceServices")
	public void testDefineAverageValueFromPerDayPriceWithNullDate(IsPriceService priceService) throws WrongPriceException {
		BigDecimal firstPriceAmount = new BigDecimal(1100);
		IsPerDayPrice firstPerDayPrice = PriceData.definePerDayPrice(LocalDate.now()
				.plusMonths(4), firstPriceAmount);

		BigDecimal secondPriceAmount = new BigDecimal(1500);
		IsPerDayPrice secondPerDayPrice = PriceData.definePerDayPrice(null, secondPriceAmount);

		List<IsPerDayPrice> perDayPriceList = Arrays.asList(firstPerDayPrice, secondPerDayPrice);
		assertThrows(WrongPriceException.class, () -> priceService.defineAverageValueFromPerDayPrice(perDayPriceList),
				"Exception should be thrown if null date was provided in the per day price item");
	}

	@ParameterizedTest
	@MethodSource("definePriceServices")
	public void testDefineAverageValueFromPerDayPriceWithNullAmount(IsPriceService priceService) throws WrongPriceException {
		BigDecimal firstPriceAmount = new BigDecimal(1100);
		IsPerDayPrice firstPerDayPrice = PriceData.definePerDayPrice(LocalDate.now()
				.plusMonths(4), firstPriceAmount);

		IsPerDayPrice secondPerDayPrice = PriceData.definePerDayPrice(LocalDate.now(), null);

		List<IsPerDayPrice> perDayPriceList = Arrays.asList(firstPerDayPrice, secondPerDayPrice);
		assertThrows(WrongPriceException.class, () -> priceService.defineAverageValueFromPerDayPrice(perDayPriceList),
				"Exception should be thrown if null price amount was provided in the per day item");
	}

	//Double defineAverageValueFromPrices(List<IsPrice> prices) throws WrongPriceException;

	@ParameterizedTest
	@MethodSource("definePriceServices")
	public void defineAverageValueFromPricesWithNullParameter(IsPriceService priceService) throws WrongPriceException {
		assertEquals(BigDecimal.ZERO, priceService.defineAverageValueFromPrices(null), "Zero value should be return if null was provided");
	}

	@ParameterizedTest
	@MethodSource("definePriceServices")
	public void defineAverageValueFromPricesWithEmptyListParameter(IsPriceService priceService) throws WrongPriceException {
		assertEquals(BigDecimal.ZERO, priceService.defineAverageValueFromPrices(new ArrayList<>()), "Zero value should be return if empty list was provided");
	}

	@ParameterizedTest
	@MethodSource("definePriceServices")
	public void defineAverageValueFromPricesWithWrongPrice(IsPriceService priceService) {
		LocalDate collidesDate = LocalDate.now()
				.plusYears(2)
				.plusMonths(2);
		List<IsPrice> priceList = PriceData.definePriceList(12);
		priceList.add(PriceData.definePrice(collidesDate.plusDays(-2), collidesDate.plusDays(1)));
		priceList.add(PriceData.definePrice(collidesDate, collidesDate.plusDays(5)));
		assertThrows(WrongPriceException.class, () -> priceService.defineAverageValueFromPrices(priceList), "Exception should be thrown when dates collides");
	}

	@ParameterizedTest
	@MethodSource("definePriceServices")
	public void defineAverageValueFromPricesWithNullDateInPrice(IsPriceService priceService) {
		assertThrows(WrongPriceException.class, () -> priceService.defineAverageValueFromPrices(Arrays.asList(null, null)),
				"Exception should be thrown when prices are null");
		LocalDate collidesDate = LocalDate.now()
				.plusYears(2)
				.plusMonths(2);
		List<IsPrice> priceList = PriceData.definePriceList(12);
		priceList.add(PriceData.definePrice(collidesDate.plusDays(-2), collidesDate.plusDays(1)));
		priceList.add(PriceData.definePrice(collidesDate, null));
		assertThrows(WrongPriceException.class, () -> priceService.defineAverageValueFromPrices(priceList), "Exception should be thrown when date is null");
	}

	@ParameterizedTest
	@MethodSource("definePriceServices")
	public void defineAverageValueFromPricesWithWrongDates(IsPriceService priceService) {
		LocalDate wrongDate = LocalDate.now()
				.plusYears(2)
				.plusMonths(2);
		List<IsPrice> priceList = PriceData.definePriceList(12);
		priceList.add(PriceData.definePrice(wrongDate.plusDays(3), wrongDate.plusDays(-2)));
		assertThrows(WrongPriceException.class, () -> priceService.defineAverageValueFromPrices(priceList), "Exception should be thrown when dates are wrong");
	}

	@ParameterizedTest
	@MethodSource("definePriceServices")
	public void defineAverageValueFromPricesWithCorrectPrices(IsPriceService priceService) throws WrongPriceException {
		LocalDate notCollidesDate = LocalDate.now()
				.plusYears(2)
				.plusMonths(2);
		BigDecimal firstPriceAmount = new BigDecimal(100);
		BigDecimal secondPriceAmount = new BigDecimal(1000);
		BigDecimal thirdPriceAmount = new BigDecimal(200);

		IsPrice firstPrice = PriceData.definePrice(notCollidesDate.plusDays(-3), notCollidesDate.plusDays(-1), firstPriceAmount);
		BigDecimal firstPriceDuration = new BigDecimal(Period.between(firstPrice.getFromDate(), firstPrice.getToDate())
				.getDays() + 1);

		IsPrice secondPrice = PriceData.definePrice(notCollidesDate, notCollidesDate.plusDays(5), secondPriceAmount);
		BigDecimal secondPriceDuration = new BigDecimal(Period.between(secondPrice.getFromDate(), secondPrice.getToDate())
				.getDays() + 1);

		IsPrice thirdPrice = PriceData.definePrice(notCollidesDate.plusDays(6), notCollidesDate.plusDays(6), thirdPriceAmount);
		BigDecimal thirdPriceDuration = new BigDecimal(Period.between(thirdPrice.getFromDate(), thirdPrice.getToDate())
				.getDays() + 1);

		BigDecimal averagePrice = firstPriceAmount.multiply(firstPriceDuration)
				.add(secondPriceAmount.multiply(secondPriceDuration))
				.add(thirdPriceAmount.multiply(thirdPriceDuration))
				.divide(firstPriceDuration.add(secondPriceDuration)
						.add(thirdPriceDuration), RoundingMode.HALF_DOWN);
		List<IsPrice> priceList = Arrays.asList(firstPrice, secondPrice, thirdPrice);
		assertEquals(averagePrice, priceService.defineAverageValueFromPrices(priceList), "Wrong calculated average price amount");
	}

}
