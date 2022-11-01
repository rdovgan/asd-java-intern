package team.asd.util;

import org.apache.commons.lang3.StringUtils;
import team.asd.entities.BaseLocation;
import team.asd.entities.IsLocation;
import team.asd.entities.IsLocationSearchRequest;

import java.lang.reflect.Field;

public class MessageBuilder {
	private final StringBuilder messageBuilder = new StringBuilder();
	private String exceptionInformation = null;

	public <T> MessageBuilder add(T value, String fieldName) {
		if (fieldName == null) {
			return add(value);
		}
		addSpaceIfNeeded();
		messageBuilder.append(fieldName).append(": ");
		if (value == null) {
			messageBuilder.append("null");
		} else {
			if (value instanceof String) {
				messageBuilder.append('"').append(value).append('"');
			} else {
				messageBuilder.append(value);
			}
		}
		return this;
	}

	public <T> MessageBuilder add(T value) {
		if (value instanceof String && StringUtils.isBlank((String) value)) {
				return this;
		}
		if (value != null) {
			addSpaceIfNeeded();
			messageBuilder.append(value);
		}
		return this;
	}

	public MessageBuilder add(Throwable exception) {
		if (exception != null) {
			exceptionInformation = exception.getClass().getCanonicalName() + ": " + exception.getMessage();
		}
		return this;
	}

	private void addSpaceIfNeeded() {
		if (messageBuilder.length() != 0) {
			messageBuilder.append(' ');
		}
	}

	public String getMessage() {
		if (exceptionInformation != null) {
			addSpaceIfNeeded();
			messageBuilder.append("exception ").append(exceptionInformation);
		}
		return messageBuilder.toString();
	}

	public MessageBuilder add(IsLocationSearchRequest locationSearchRequest) {
		if (locationSearchRequest == null) {
			return this;
		}
		return add(locationSearchRequest.getName(), "name").add(locationSearchRequest.getCountry(), "country").add(locationSearchRequest.getRegion(), "region")
				.add(locationSearchRequest.getLongitude(), "longitude").add(locationSearchRequest.getLatitude(), "latitude")
				.add(locationSearchRequest.getZipCode(), "zip code");
	}

	public MessageBuilder add(IsLocation location) {
		if (location == null) {
			return this;
		}
		return add(location.toString());
	}

	/**
	 * Change string value in BaseLocation using reflection.
	 * ! Warning ! do not use in real projects.
	 * @param baseLocation object that should be changed
	 * @param changeTo String value to what should be change baseLocation
	 */
	private void changeBaseLocation(BaseLocation baseLocation, String changeTo) throws Exception{
		Field stringValue = baseLocation.getClass().getDeclaredField("baseLocation");
		stringValue.setAccessible(true);
		stringValue.set(baseLocation, changeTo);
	}
}

