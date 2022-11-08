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
	@JsonProperty("id")
	private Integer id;
	@JsonProperty("name")
	private String name;
	@JsonProperty("state")
	private String state;
	@JsonProperty("postal_address")
	private String postalAddress;
	@JsonProperty("email_address")
	private String emailAddress;
	@JsonProperty("mobile_phone")
	private String mobilePhone;
	@JsonProperty("password")
	private String password;
	@JsonProperty("currency")
	private String currency;
	@JsonProperty("user_type")
	private String userType;
}
