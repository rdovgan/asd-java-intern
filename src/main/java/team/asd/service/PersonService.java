package team.asd.service;

import lombok.NonNull;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import team.asd.entities.IsPerson;

import java.util.Collections;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PersonService implements IsPersonService {
	@Override
	public @NonNull List<IsPerson> collectPersonsWithNameStartsWith(List<IsPerson> personList, String prefix) {
		if (CollectionUtils.isEmpty(personList))
			return Collections.emptyList();
		if (StringUtils.isEmpty(prefix))
			return personList;
		return personList.stream()
				.filter(p -> isHasAge(p) && StringUtils.startsWithIgnoreCase(p.getName(), prefix))
				.collect(Collectors.toList());
	}

	@Override
	public @NonNull Map<Integer, List<IsPerson>> collectPersonsByAge(List<IsPerson> personList) {
		if (CollectionUtils.isEmpty(personList))
			return Collections.emptyMap();
		return personList.stream()
				.filter(this::isCorrectAge)
				.collect(Collectors.groupingBy(IsPerson::getAge));
	}

	@Override
	public @NonNull Double calculateAverageAge(List<IsPerson> personList) {
		if (CollectionUtils.isEmpty(personList))
			return 0d;
		return personList.stream()
				.filter(this::isCorrectAge)
				.collect(Collectors.averagingInt(IsPerson::getAge));
	}

	@Override
	public @NonNull IntSummaryStatistics sumAndCountAge(List<IsPerson> personList) {
		if (CollectionUtils.isEmpty(personList))
			return new IntSummaryStatistics();
		return personList.stream()
				.filter(this::isCorrectAge)
				.collect(Collectors.summarizingInt(IsPerson::getAge));
	}

	private boolean isHasAge(IsPerson person) {
		return person != null && person.getAge() != null;
	}

	private boolean isCorrectAge(IsPerson person) {
		return isHasAge(person) && person.getAge() >= 0;
	}
}
