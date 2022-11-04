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

	public void create(Party party) {
		partyDao.create(party);
	}

	public void update(Party party) {
		partyDao.update(party);
	}

	public void delete(Integer id) {
		partyDao.deleteById(id);
	}
}
