package team.asd.service;

import team.asd.dao.PartyDao;
import team.asd.entity.Party;

public class PartyService {
	private final PartyDao partyDao;

	public PartyService(PartyDao partyDao) {
		this.partyDao = partyDao;
	}

	public Party readById(Integer id) {
		return partyDao.readById(id);
	}

	public Party create(Party party) {
		return partyDao.create(party);
	}

	public Party update(Party party) {
		return partyDao.update(party);
	}

	public void delete(Integer id) {
		partyDao.deleteById(id);
	}
}
