package team.asd.tutorials.data;

import team.asd.tutorials.constants.Currency;
import team.asd.tutorials.entities.IsPerDayPrice;
import team.asd.tutorials.entities.IsPrice;
import team.asd.tutorials.entities.TestPerDayPrice;
import team.asd.tutorials.entities.TestPrice;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PriceData {

	private static final LocalDate FIRST_DAY_OF_NEXT_YEAR = LocalDate.now().with(TemporalAdjusters.firstDayOfNextYear());

	private static final Random random = new Random();

	public static IsPrice defineDefaultPrice() {
		int randomFromDate = random.nextInt(350);
		int randomDuration = random.nextInt(15) + 1;
		TestPrice price = new TestPrice();
		price.setFromDate(FIRST_DAY_OF_NEXT_YEAR.plusDays(randomFromDate));
		price.setToDate(FIRST_DAY_OF_NEXT_YEAR.plusDays(randomFromDate).plusDays(randomDuration));
		price.setPrice(new BigDecimal(random.nextInt(200) + 50));
		price.setCurrency(Currency.USD.name());
		return price;
	}

	public static IsPrice definePrice(LocalDate fromDate, LocalDate toDate) {
		TestPrice price = new TestPrice();
		price.setFromDate(fromDate);
		price.setToDate(toDate);
		price.setPrice(new BigDecimal(random.nextInt(200) + 50));
		price.setCurrency(Currency.USD.name());
		return price;
	}

	public static IsPrice definePrice(LocalDate fromDate, LocalDate toDate, BigDecimal amount) {
		TestPrice price = new TestPrice();
		price.setFromDate(fromDate);
		price.setToDate(toDate);
		price.setPrice(amount);
		price.setCurrency(Currency.USD.name());
		return price;
	}

	public static List<IsPrice> definePriceList(Integer limit) {
		if (limit == null || limit < 1) {
			limit = 1;
		}
		return Stream.generate(PriceData::defineDefaultPrice).limit(limit).collect(Collectors.toList());
	}

	public static IsPerDayPrice defineDefaultPerDayPrice() {
		int randomDate = random.nextInt(365);
		TestPerDayPrice perDayPrice = new TestPerDayPrice();
		perDayPrice.setDate(FIRST_DAY_OF_NEXT_YEAR.plusDays(randomDate));
		perDayPrice.setPrice(new BigDecimal(random.nextInt(200) + 50));
		perDayPrice.setCurrency(Currency.USD.name());
		return perDayPrice;
	}

	public static IsPerDayPrice definePerDayPrice(LocalDate date) {
		TestPerDayPrice perDayPrice = new TestPerDayPrice();
		perDayPrice.setDate(date);
		perDayPrice.setPrice(new BigDecimal(random.nextInt(200) + 50));
		perDayPrice.setCurrency(Currency.USD.name());
		return perDayPrice;
	}

	public static IsPerDayPrice definePerDayPrice(LocalDate date, BigDecimal value) {
		TestPerDayPrice perDayPrice = new TestPerDayPrice();
		perDayPrice.setDate(date);
		perDayPrice.setPrice(value);
		perDayPrice.setCurrency(Currency.USD.name());
		return perDayPrice;
	}

	public static List<IsPerDayPrice> definePerDayPriceList(Integer limit) {
		if (limit == null || limit < 1) {
			limit = 1;
		}
		return Stream.generate(PriceData::defineDefaultPerDayPrice).limit(limit).collect(Collectors.toList());
	}

}
