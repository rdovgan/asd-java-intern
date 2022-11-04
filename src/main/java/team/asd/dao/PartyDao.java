package team.asd.dao;

import team.asd.entity.Party;

public interface PartyDao {
	Party readById(Integer id);

	Party create(Party party);

	Party update(Party party, Integer id);

	void deleteById(Integer id);
}
