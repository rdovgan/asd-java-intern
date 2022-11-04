package team.asd.entity;

import team.asd.constants.PartyState;
import team.asd.constants.UserType;

import java.time.LocalDateTime;

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

	public Party() {
	}

	public Party(Integer id, String name, PartyState state, String postalAddress, String emailAddress, String mobilePhone, String password, String currency,
			UserType userType, LocalDateTime version) {
		this.id = id;
		this.name = name;
		this.state = state;
		this.postalAddress = postalAddress;
		this.emailAddress = emailAddress;
		this.mobilePhone = mobilePhone;
		this.password = password;
		this.currency = currency;
		this.userType = userType;
		this.version = version;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public PartyState getState() {
		return state;
	}

	public void setState(PartyState state) {
		this.state = state;
	}

	public String getPostalAddress() {
		return postalAddress;
	}

	public void setPostalAddress(String postalAddress) {
		this.postalAddress = postalAddress;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	public LocalDateTime getVersion() {
		return version;
	}

	public void setVersion(LocalDateTime version) {
		this.version = version;
	}
}
