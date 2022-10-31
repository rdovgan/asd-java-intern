package team.asd.service;

import lombok.NonNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public interface IsStreamApiService {

	/**
	 * Defines stream from collection.
	 *
	 * @param collection with items
	 * @return stream with non-null items
	 */
	@NonNull
	Stream<?> getNonNullStreamItems(Collection<?> collection);

	/**
	 * Defines a list with integer values for provided range.
	 * If start value is greater than end value, params should be swapped.
	 *
	 * @param start first range number (included)
	 * @param end   last range number (included)
	 * @return a list with integers range
	 */
	@NonNull
	List<Integer> defineListFromRange(Integer start, Integer end) throws NumberFormatException;

	/**
	 * Converts list of string values into list of integers using Java Stream API. If provided list is empty of null, empty list should be returned.
	 *
	 * @param stringList list of Strings
	 * @return list of converted non-null values
	 */
	@NonNull
	List<Integer> convertStringListToIntegerList(List<String> stringList);

	/**
	 * Converts String value into char list, ignored all non-alphabets and non-numbers.
	 *
	 * @param value string
	 * @return IntStream of chars with alphabets and numbers
	 */
	@NonNull
	IntStream convertStringToLegalChars(String value);

	/**
	 * Sum all numbers that provided in the list.
	 *
	 * @param values array of
	 * @return a sum of all numbers
	 * @throws NullPointerException in the case when null argument was provided
	 */
	@NonNull
	BigDecimal sumAllValues(List<BigDecimal> values);

	/**
	 * Sort a provided list of dates with ascending order and returns a stream.
	 *
	 * @param listOfDates need to sort
	 * @return stream of dates, sorted in ascending order without null values
	 */
	@NonNull
	Stream<LocalDate> sortLocalDateList(List<LocalDate> listOfDates);

	/**
	 * Method will sort a provided list of dates. Then will try to find a parameter date in this list.
	 * If date is present, method will filter out next N elements. N is provided Integer parameter.
	 * If date is not present, method will use closest date after provided.
	 *
	 * @param listOfDates a provided list of dates
	 * @param date        starting from this parameter we will skip dates
	 * @param daysToSkip  number of dates that we will filter out if provided date will be found
	 * @return stream of dates
	 */
	@NonNull
	Stream<LocalDate> skipDaysFromSpecifiedDate(List<LocalDate> listOfDates, LocalDate date, Integer daysToSkip);

	/**
	 * Collect all provided lists into one that contains all the objects in the same iteration order
	 *
	 * @param lists of the same objects
	 * @return list with all items
	 */
	@NonNull
	List<? extends Object> collectLists(List<? extends Object> ... lists);

	/**
	 * Return elements without duplicates. Not modifies the provided list
	 *
	 * @param listWithDuplicates list with elements
	 * @return list without duplicates
	 */
	@NonNull
	List<? extends Object> removeDuplicates(List<? extends Object> listWithDuplicates);
}
