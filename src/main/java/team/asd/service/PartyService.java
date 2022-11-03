package team.asd.service;

import team.asd.dao.PartyDao;
import team.asd.entity.Party;

public class PartyService {
	private static PartyDao partyDao;

	public Party readById(Integer id) {
		return partyDao.readById(id);
	}

	public Party create(Party party) {
		return partyDao.create(party);
	}

	public Party update(Party party, Integer id) {
		return partyDao.update(party, id);
	}

	public Party delete(Integer id) {
		return partyDao.delete(id);
	}
}
