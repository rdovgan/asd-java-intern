package team.asd.service;

import org.apache.commons.lang3.StringUtils;
import team.asd.dao.PartyDao;
import team.asd.entity.Party;
import team.asd.exceptions.ValidationException;

public class PartyService {
	private final PartyDao partyDao;

	public PartyService(PartyDao partyDao) {
		this.partyDao = partyDao;
	}

	public Party readById(Integer id) throws ValidationException {
		if (isWrongId(id)) {
			throw new ValidationException("Wrong id was provided");
		}
		return partyDao.readById(id);
	}

	public void create(Party party) throws ValidationException {
		if (party == null || StringUtils.isBlank(party.getName())) {
			throw new ValidationException("Wrong Party object was provided");
		}
		partyDao.create(party);
	}

	public void update(Party party) throws ValidationException {
		if (party == null || isWrongId(party.getId())) {
			throw new ValidationException("Wrong Party object was provided");
		}
		partyDao.update(party);
	}

	public void delete(Integer id) throws ValidationException {
		if (isWrongId(id)) {
			throw new ValidationException("Wrong id was provided");
		}
		partyDao.deleteById(id);
	}

	private boolean isWrongId(Integer id){
		return id == null || id <= 0;
	}
}
