package team.asd.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.reflections.Reflections;
import team.asd.engine.ServicesScannerUtils;
import team.asd.entities.IsPerson;
import team.asd.entities.TestPerson;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@DisplayName("Test methods for IsPersonService implementations")
public class PersonServiceTest {

	private static final Long TOTAL_AGE = 150L; //1 + 0 + 99 + 20 + 30
	private static final Double AVERAGE_AGE = 30D; // 150 / 5

	private static List<IsPerson> definePersonList() {
		return Arrays
				.asList(new TestPerson("No1 Test", 1), new TestPerson("NO age", null), null, new TestPerson(null, null), new TestPerson("Newbie", 0), new TestPerson("NOT Old Man", 99),
						new TestPerson("Marty McFly", -4), new TestPerson("Student Guy", 20), new TestPerson("Agent Smith", 30));
	}

	private static List<IsPerson> definePersonListWithNewbies() {
		return Stream.concat(definePersonList().stream(), Arrays.asList(new TestPerson("Newbie 2", 0), new TestPerson("Newbie 3", 0)).stream())
				.collect(Collectors.toList());
	}

	private static Stream<IsPersonService> defineServices() {
		Reflections reflections = new Reflections("team.asd.service");
		Set<Class<? extends IsPersonService>> classes = reflections.getSubTypesOf(IsPersonService.class);
		ServicesScannerUtils<IsPersonService> servicesScanner = new ServicesScannerUtils<>();
		return classes.stream().map(servicesScanner::defineServiceImplementations);
	}

	//List<IsPerson> collectPersonsWithNameStartsWith(List<IsPerson> personList, String prefix);

	@ParameterizedTest
	@MethodSource("defineServices")
	void testCollectPersonsWithNameStartsWith(IsPersonService personService) {
		List<IsPerson> personList = definePersonList();
		List<IsPerson> resultPersonsList = personService.collectPersonsWithNameStartsWith(personList, "NO");
		assertEquals(2, resultPersonsList.size(), "Incorrect person count");
		assertEquals(0, personService.collectPersonsWithNameStartsWith(null, null).size());
		assertEquals(personList.size(), personService.collectPersonsWithNameStartsWith(personList, "").size());
	}

	//Map<Integer, List<IsPerson>> collectPersonsByAge(List<IsPerson> personList);

	@ParameterizedTest
	@MethodSource("defineServices")
	void testCollectPersonsByAge(IsPersonService personService) {
		List<IsPerson> personList = definePersonListWithNewbies();
		Map<Integer, List<IsPerson>> resultPersonsMap = personService.collectPersonsByAge(personList);
		assertEquals(0, personService.collectPersonsByAge(null).size());
		assertEquals(5, resultPersonsMap.size(), "Incorrect grouping by age");
		assertEquals(3, resultPersonsMap.get(0).size(), "Incorrect grouping method by age");
		assertNull(resultPersonsMap.get(-4), "Incorrect grouping method by age. Negative age should be ignored");
	}

	//Double calculateAverageAge(List<IsPerson> personList);

	@ParameterizedTest
	@MethodSource("defineServices")
	void testCalculateAverageAge(IsPersonService personService) {
		List<IsPerson> personList = definePersonList();
		Double averageAge = personService.calculateAverageAge(personList);
		assertEquals(0D, personService.calculateAverageAge(null), "Zero value should be return in case when no person was provided");
		assertEquals(AVERAGE_AGE, averageAge, "Incorrect person count");
	}

	//IntSummaryStatistics sumAndCountAge(List<IsPerson> personList);

	@ParameterizedTest
	@MethodSource("defineServices")
	void testSumAndCountAge(IsPersonService personService) {
		List<IsPerson> personList = definePersonList();
		IntSummaryStatistics summaryStatistics = personService.sumAndCountAge(personList);
		assertEquals(AVERAGE_AGE, summaryStatistics.getAverage(), "Incorrect average statistic");
		assertEquals(TOTAL_AGE, summaryStatistics.getSum(), "Incorrect total age");
		assertEquals(5, summaryStatistics.getCount());
		assertEquals(0L, personService.sumAndCountAge(null).getCount());
	}
}
