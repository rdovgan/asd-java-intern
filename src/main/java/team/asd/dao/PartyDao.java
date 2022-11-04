package team.asd.dao;

import team.asd.entity.Party;

public interface PartyDao {
	Party readById(Integer id);

	Party create(Party party);

	Party update(Party party);

	void deleteById(Integer id);
}
