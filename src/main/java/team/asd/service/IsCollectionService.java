package team.asd.service;

import java.util.List;
import java.util.Set;

public interface IsCollectionService {

	/**
	 * Wrap provided objects into unmodifiable list. If you try to modify these collection, you will get {@link UnsupportedOperationException}
	 *
	 * @param objects to wrap into list
	 * @return unmodifiable list of objects
	 */
	List<Object> unmodifiableList(Object ... objects);

	/**
	 * Create an immutable list with provided objects. If you try to modify these collection, you will get {@link UnsupportedOperationException}
	 *
	 * @param objects to create an immutable list
	 * @return immutable list of objects
	 */
	List<Object> immutableList(Object ... objects);

	/**
	 * Checks provided 2 sets and form a new set of objects that are present in both ones. If any of provided collections is empty, returns empty set.
	 *
	 * @return set of objects that are present in both lists
	 */
	Set<Object> retrieveObjectsThatPresentInBothLists(Set<Object> firstSet, Set<Object> secondSet);



}
