package team.asd.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PartyDto {
	private Integer id;
	private String name;
	private String state;
	@JsonProperty("postal_address")
	private String postalAddress;
	@JsonProperty("email_address")
	private String emailAddress;
	@JsonProperty("mobile_phone")
	private String mobilePhone;
	private String password;
	private String currency;
	@JsonProperty("user_type")
	private String userType;
}
