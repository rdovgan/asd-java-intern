package team.asd.dao;

import team.asd.entity.Party;

public interface PartyDao {
	Party readById(int id);

	Party create(Party party);

	Party update(Party party, int id);

	Party delete(int id);
}
