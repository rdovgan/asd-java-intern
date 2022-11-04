package team.asd.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import team.asd.constants.PartyState;
import team.asd.constants.UserType;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Party {
	private Integer id;
	private String name;
	private PartyState state;
	private String postalAddress;
	private String emailAddress;
	private String mobilePhone;
	private String password;
	private String currency;
	private UserType userType;
	private LocalDateTime version;
}
