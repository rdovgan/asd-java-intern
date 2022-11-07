package team.asd.service;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import team.asd.constants.UserType;
import team.asd.dao.PartyDao;
import team.asd.dao.TestPartyDao;
import team.asd.entity.Party;
import team.asd.exceptions.ValidationException;

import static org.junit.jupiter.api.Assertions.*;

class PartyServiceTest {
	private static final PartyDao partyDao = new TestPartyDao();
	private static PartyService partyService;
	private Party party;

	@BeforeAll
	public static void setUp() {
		partyService = new PartyService(partyDao);
	}

	@BeforeEach
	public void initParty() {
		party = Party.builder()
				.name("test name")
				.postalAddress("test address")
				.emailAddress("test@email.com")
				.password("12345")
				.currency("USD")
				.userType(UserType.Customer)
				.build();
	}

	@Test
	void testReadByIdWithNull() {
		assertThrows(ValidationException.class, () -> partyService.readById(null), "Validation exception should be thrown");
	}

	@Test
	void testReadByIdWithZeroAndNegativeId() {
		assertThrows(ValidationException.class, () -> partyService.readById(0), "Validation exception should be thrown");
		assertThrows(ValidationException.class, () -> partyService.readById(-190), "Validation exception should be thrown");
	}

	@Test
	void testReadById() throws ValidationException {
		assertNull(partyService.readById(5), "Null value should be returned");
	}

	@Test
	void testCreateWithNull() {
		assertThrows(ValidationException.class, () -> partyService.create(null), "Validation exception should be thrown");
	}

	@Test
	void testCreateWithNullAndBlankName() {
		party.setName(null);
		assertThrows(ValidationException.class, () -> partyService.create(party), "Validation exception should be thrown");
		party.setName("  ");
		assertThrows(ValidationException.class, () -> partyService.create(party), "Validation exception should be thrown");
	}

	@Test
	void testCreate() throws ValidationException {
		assertDoesNotThrow(() -> partyService.create(party), "Validation should be passed");
	}

	@Test
	void testUpdateWithNull() {
		assertThrows(ValidationException.class, () -> partyService.update(null), "Validation exception should be thrown");
		assertThrows(ValidationException.class, () -> partyService.update(party), "Validation exception should be thrown");
	}

	@Test
	void testUpdateWithZeroAndNegativeId() {
		party.setId(0);
		assertThrows(ValidationException.class, () -> partyService.update(party), "Validation exception should be thrown");
		party.setId(-100);
		assertThrows(ValidationException.class, () -> partyService.update(party), "Validation exception should be thrown");
	}

	@Test
	void testUpdate() throws ValidationException {
		party.setId(12);
		assertDoesNotThrow(() -> partyService.update(party), "Validation should be passed");
	}

	@Test
	void testDeleteWithNull() {
		assertThrows(ValidationException.class, () -> partyService.delete(null), "Validation exception should be thrown");
	}

	@Test
	void testDeleteWithZeroAndNegativeId() {
		assertThrows(ValidationException.class, () -> partyService.delete(0), "Validation exception should be thrown");
		assertThrows(ValidationException.class, () -> partyService.delete(-10), "Validation exception should be thrown");
	}

	@Test
	void testDelete() throws ValidationException {
		assertDoesNotThrow(() -> partyService.delete(15), "Validation should be passed");
	}
}
