package team.asd.tutorials.service;

import lombok.NonNull;
import org.apache.commons.collections4.CollectionUtils;
import team.asd.tutorials.entities.IsPerDayPrice;
import team.asd.tutorials.entities.IsPrice;
import team.asd.tutorials.exceptions.WrongPriceException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.function.Predicate;

public class PriceService implements IsPriceService {
	@Override
	public @NonNull BigDecimal defineAverageValueFromPerDayPrice(List<IsPerDayPrice> prices) throws WrongPriceException {
		if (CollectionUtils.isEmpty(prices)) {
			return BigDecimal.ZERO;
		}
		if (prices.stream()
				.anyMatch(isPerDayPriceWrong())) {
			throw new WrongPriceException("One or more of provided per day prices were wrong");
		}
		if (prices.stream()
				.anyMatch(isPerDayDateSame(prices))) {
			throw new WrongPriceException("There were the same dates");
		}
		return prices.stream()
				.map(IsPerDayPrice::getPrice)
				.reduce(BigDecimal.ZERO, BigDecimal::add)
				.divide(BigDecimal.valueOf(prices.size()), RoundingMode.HALF_DOWN);
	}

	private static Predicate<IsPerDayPrice> isPerDayPriceWrong() {
		return p -> p == null || p.getDate() == null || p.getPrice() == null;
	}

	private static Predicate<IsPerDayPrice> isPerDayDateSame(List<IsPerDayPrice> prices) {
		return p -> prices.stream()
				.anyMatch(another -> p != another && p.getDate()
						.isEqual(another.getDate()));
	}

	@Override
	public @NonNull BigDecimal defineAverageValueFromPrices(List<IsPrice> prices) throws WrongPriceException {
		if (CollectionUtils.isEmpty(prices)) {
			return BigDecimal.ZERO;
		}
		if (prices.stream()
				.anyMatch(isPriceWrong())) {
			throw new WrongPriceException("One or more of provided prices were wrong");
		}
		if (prices.stream()
				.anyMatch(isPricePeriodOverlap(prices))) {
			throw new WrongPriceException("There were overlapped dates");
		}

		BigDecimal totalDuration = prices.stream()
				.map(p -> getDuration(p.getFromDate(), p.getToDate()))
				.reduce(BigDecimal.ZERO, BigDecimal::add);
		return prices.stream()
				.map(p -> p.getPrice()
						.multiply(getDuration(p.getFromDate(), p.getToDate())))
				.reduce(BigDecimal.ZERO, BigDecimal::add)
				.divide(totalDuration, RoundingMode.HALF_DOWN);
	}

	private static Predicate<IsPrice> isPriceWrong() {
		return p -> p == null || p.getPrice() == null || p.getFromDate() == null || p.getToDate() == null || p.getFromDate()
				.isAfter(p.getToDate());
	}

	private static Predicate<IsPrice> isPricePeriodOverlap(List<IsPrice> prices) {
		return p -> prices.stream()
				.anyMatch(another -> p != another && p.getFromDate()
						.isBefore(another.getToDate()) && another.getFromDate()
						.isBefore(p.getToDate()));
	}

	private static BigDecimal getDuration(LocalDate from, LocalDate to) {
		return new BigDecimal(Period.between(from, to)
				.getDays() + 1);
	}

}
