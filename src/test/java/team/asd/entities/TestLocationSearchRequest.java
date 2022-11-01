package team.asd.entities;

public class TestLocationSearchRequest implements IsLocationSearchRequest {
	private String name;
	private String country;
	private String region;
	private String longitude;
	private String latitude;
	private String zipCode;

	public TestLocationSearchRequest(String name, String country, String region, String longitude, String latitude, String zipCode) {
		this.name = name;
		this.country = country;
		this.region = region;
		this.longitude = longitude;
		this.latitude = latitude;
		this.zipCode = zipCode;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getName() {
		return name;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Override
	public String getCountry() {
		return country;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	@Override
	public String getRegion() {
		return region;
	}

	@Override
	public String getLongitude() {
		return longitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	@Override
	public String getLatitude() {
		return latitude;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	@Override
	public String getZipCode() {
		return zipCode;
	}
}
