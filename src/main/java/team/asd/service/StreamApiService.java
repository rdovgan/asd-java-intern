package team.asd.service;

import lombok.NonNull;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.CharUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.IntPredicate;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamApiService implements IsStreamApiService {
	@Override
	public @NonNull Stream<?> getNonNullStreamItems(Collection<?> collection) {
		return CollectionUtils.emptyIfNull(collection)
				.stream()
				.filter(Objects::nonNull);
	}

	@Override
	public @NonNull List<Integer> defineListFromRange(Integer start, Integer end) throws NumberFormatException {
		if (ObjectUtils.anyNull(start, end)) {
			return Collections.emptyList();
		}
		return IntStream.rangeClosed(Integer.min(start, end), Integer.max(start, end))
				.boxed()
				.collect(Collectors.toList());
	}

	@Override
	public @NonNull List<Integer> convertStringListToIntegerList(List<String> stringList) {
		if (CollectionUtils.isEmpty(stringList)) {
			return Collections.emptyList();
		}
		return stringList.stream()
				.map(StreamApiService::parseInteger)
				.filter(Objects::nonNull)
				.collect(Collectors.toList());
	}

	private static Integer parseInteger(String stringInteger) {
		try {
			return Integer.parseInt(stringInteger);
		} catch (NumberFormatException ex) {
			return null;
		}
	}

	@Override
	public @NonNull IntStream convertStringToLegalChars(String value) {
		if (StringUtils.isEmpty(value)) {
			return IntStream.empty();
		}
		return value.chars()
				.filter(isAlphabetOrNumber());
	}

	private static IntPredicate isAlphabetOrNumber() {
		return ch -> CharUtils.isAsciiAlpha((char) ch) || Character.isDigit(ch);
	}

	@Override
	public @NonNull BigDecimal sumAllValues(List<BigDecimal> values) {
		return values.stream()
				.filter(Objects::nonNull)
				.reduce(BigDecimal.ZERO, BigDecimal::add);
	}

	@Override
	public @NonNull Stream<LocalDate> sortLocalDateList(List<LocalDate> listOfDates) {
		return CollectionUtils.emptyIfNull(listOfDates)
				.stream()
				.filter(Objects::nonNull)
				.sorted();
	}

	@Override
	public @NonNull Stream<LocalDate> skipDaysFromSpecifiedDate(List<LocalDate> listOfDates, LocalDate date, Integer daysToSkip) {
		if (ObjectUtils.anyNull(listOfDates, date, daysToSkip) || daysToSkip < 0) {
			return Stream.empty();
		}
		return listOfDates.stream()
				.filter(isToSkipDate(date))
				.sorted()
				.skip(daysToSkip);
	}

	private static Predicate<LocalDate> isToSkipDate(LocalDate date) {
		return currDate -> currDate != null && (currDate.equals(date) || currDate.isAfter(date));
	}

	@Override
	public @NonNull List<? extends Object> collectLists(List<?>... lists) {
		return Arrays.stream(lists)
				.flatMap(Collection::stream)
				.collect(Collectors.toList());
	}

	@Override
	public @NonNull List<? extends Object> removeDuplicates(List<?> listWithDuplicates) {
		if (CollectionUtils.isEmpty(listWithDuplicates)) {
			return Collections.emptyList();
		}
		return listWithDuplicates.stream()
				.distinct()
				.collect(Collectors.toList());
	}
}
