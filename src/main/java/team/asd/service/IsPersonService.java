package team.asd.service;

import lombok.NonNull;
import team.asd.entities.IsPerson;

import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;

public interface IsPersonService {

	/**
	 * Filters persons by its name. If persons list is empty or null, returns an empty list. Also, items without age should be ignored.
	 * If prefix wasn't provided, return personList without filtering.
	 *
	 * @param personList list of persons.
	 * @param prefix value to find at the start of person name.
	 * @return list of persons where it's name starts with provided value.
	 */
	@NonNull
	List<IsPerson> collectPersonsWithNameStartsWith(List<IsPerson> personList, String prefix);

	/**
	 * Groups persons by age. Returns an empty map if no person was provided. Filters out persons with wrong age (negative or null)
	 *
	 * @param personList with person objects.
	 * @return a map with persons as value and age value as a key.
	 */
	@NonNull
	Map<Integer, List<IsPerson>> collectPersonsByAge(List<IsPerson> personList);

	/**
	 * Calculates an average age for provided persons.
	 *
	 * @param personList with person objects.
	 * @return average age amount or zero if no correct person was provided.
	 */
	@NonNull
	Double calculateAverageAge(List<IsPerson> personList);

	/**
	 * Collect statistic for provided persons list.
	 *
	 * @param personList with person objects.
	 * @return sum and count statistic for persons list.
	 */
	@NonNull
	IntSummaryStatistics sumAndCountAge(List<IsPerson> personList);

}
