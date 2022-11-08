package team.asd.dao;

import team.asd.entity.Party;

public interface PartyDao {
	Party readById(Integer id);

	void create(Party party);

	void update(Party party);

	void deleteById(Integer id);
}
