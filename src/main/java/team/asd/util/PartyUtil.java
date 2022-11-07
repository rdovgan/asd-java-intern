package team.asd.util;

import team.asd.constants.PartyState;
import team.asd.constants.UserType;
import team.asd.dto.PartyDto;
import team.asd.entity.Party;

import java.util.stream.Stream;

public class PartyUtil {
	public static Party convertToEntity(PartyDto partyDto) {
		if (partyDto == null) {
			return null;
		}
		return Party.builder()
				.id(partyDto.getId())
				.name(partyDto.getName())
				.state(convertStringIntoPartyState(partyDto.getState()))
				.postalAddress(partyDto.getPostalAddress())
				.emailAddress(partyDto.getEmailAddress())
				.mobilePhone(partyDto.getMobilePhone())
				.password(partyDto.getPassword())
				.currency(partyDto.getCurrency())
				.userType(convertStringIntoUserType(partyDto.getUserType()))
				.build();
	}

	public static PartyDto convertToDto(Party party) {
		if (party == null) {
			return null;
		}
		return PartyDto.builder()
				.id(party.getId())
				.name(party.getName())
				.state(convertPartyStateIntoString(party.getState()))
				.postalAddress(party.getPostalAddress())
				.emailAddress(party.getEmailAddress())
				.mobilePhone(party.getMobilePhone())
				.password(party.getPassword())
				.currency(party.getCurrency())
				.userType(convertUserTypeIntoString(party.getUserType()))
				.build();
	}

	public static PartyState convertStringIntoPartyState(String partyState) {
		return Stream.of(PartyState.values())
				.filter(state -> state.name()
						.equalsIgnoreCase(partyState))
				.findFirst()
				.orElse(PartyState.Initial);
	}

	public static UserType convertStringIntoUserType(String userType) {
		return Stream.of(UserType.values())
				.filter(user -> user.name()
						.equalsIgnoreCase(userType))
				.findFirst()
				.orElse(null);
	}

	public static String convertPartyStateIntoString(PartyState partyState) {
		return partyState == null ? null : partyState.name();
	}

	public static String convertUserTypeIntoString(UserType userType) {
		return userType == null ? null : userType.name();
	}
}
