package team.asd.entities;

public class TestLocation implements IsLocation {
	private final String name;
	private final String country;
	private final String region;
	private final String longitude;
	private final String latitude;
	private final String zipCode;

	public TestLocation(String name, String country, String region, String longitude, String latitude, String zipCode) {
		this.name = name;
		this.country = country;
		this.region = region;
		this.longitude = longitude;
		this.latitude = latitude;
		this.zipCode = zipCode;
	}

	@Override
	public String toString() {
		return new StringBuilder().append("name: \"")
				.append(name)
				.append("\" country: \"")
				.append(country)
				.append("\" region: \"")
				.append(region)
				.append("\" longitude: \"")
				.append(longitude)
				.append("\" latitude: \"")
				.append(latitude)
				.append("\" zip code: \"")
				.append(zipCode)
				.append("\"")
				.toString();
	}
}
