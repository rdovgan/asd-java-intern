package team.asd.tutorials.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.junit.jupiter.api.Test;
import team.asd.tutorials.entities.BaseLocation;
import team.asd.tutorials.entities.IsLocation;
import team.asd.tutorials.entities.IsLocationSearchRequest;
import team.asd.tutorials.entities.TestLocation;
import team.asd.tutorials.entities.TestLocationSearchRequest;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

class MessageBuilderTest {

	// <T> MessageBuilder add(T value, String fieldName)

	@Test
	void testAddFieldAndValueWithNull() {
		MessageBuilder message = new MessageBuilder().add(null, null);
		assertTrue(StringUtils.isEmpty(message.getMessage()), "Message should be empty");
		assertNotNull(message.getMessage(), "Message should not be null");
	}

	@Test
	void testAddFieldAndValueWithNullField() {
		String blankString = " ";
		MessageBuilder message = new MessageBuilder().add(blankString, null);
		assertTrue(StringUtils.isEmpty(message.getMessage()), "Message should be empty");
		assertNotNull(message.getMessage(), "Message should not be null");

		String valueString = "value";
		assertEquals(valueString, message.add(valueString, null)
				.getMessage(), "Value should be added");
	}

	@Test
	void testAddFieldAndValueWithNullValue() {
		MessageBuilder message = new MessageBuilder().add(null, "fieldWithNull");
		assertEquals("fieldWithNull: null", message.getMessage(), "Null should be added");
	}

	@Test
	void testAddFieldAndValue() {
		MessageBuilder message = new MessageBuilder().add("str", "fieldWithString");
		assertEquals("fieldWithString: \"str\"", message.getMessage(), "Field and value with double quotes should be added");
		message.add(5, "fieldWithNonString");
		assertEquals("fieldWithString: \"str\" fieldWithNonString: 5", message.getMessage(), "Field and value should be added");
	}

	// <T> MessageBuilder add(T value)

	@Test
	void testAddNullValue() {
		String nullString = null;
		MessageBuilder message = new MessageBuilder().add(nullString);
		assertTrue(StringUtils.isEmpty(message.getMessage()), "Message should be empty");
		assertNotNull(message.getMessage(), "Message should not be null");
	}

	@Test
	void testAddBlankValue() {
		MessageBuilder message = new MessageBuilder().add(" ");
		assertTrue(StringUtils.isEmpty(message.getMessage()), "Message should be empty");
		assertNotNull(message.getMessage(), "Message should not be null");
	}

	@Test
	void testAddValue() {
		MessageBuilder message = new MessageBuilder().add("stringValue")
				.add(777);
		assertEquals("stringValue 777", message.getMessage(), "Value should be added");
	}

	// MessageBuilder add(Throwable exception)

	@Test
	void testAddExceptionWithNull() {
		MessageBuilder message = new MessageBuilder().add((Throwable) null);
		assertTrue(StringUtils.isEmpty(message.getMessage()), "Message should be empty");
		assertNotNull(message.getMessage(), "Message should not be null");
	}

	@Test
	void testAddException() {
		MessageBuilder message = new MessageBuilder().add(new RuntimeException("runtime exception"));
		assertEquals("exception java.lang.RuntimeException: runtime exception", message.getMessage(), "Exception should be added");
	}

	// MessageBuilder add(IsLocationSearchRequest locationSearchRequest)

	@Test
	void testAddLocationSearchRequestWithNull() {
		IsLocationSearchRequest nullLocationSearchRequest = null;
		MessageBuilder message = new MessageBuilder().add(nullLocationSearchRequest);
		assertTrue(StringUtils.isEmpty(message.getMessage()), "Message should be empty");
		assertNotNull(message.getMessage(), "Message should not be null");
	}

	@Test
	void testAddLocationSearchRequest() {
		IsLocationSearchRequest locationSearchRequest = new TestLocationSearchRequest("Paris", "France", "Paris Region", "2.352222", "48.856613", "75008");
		String locationSearchRequestMessage = "name: \"Paris\" country: \"France\" region: \"Paris Region\" longitude: \"2.352222\" latitude: \"48.856613\" zip code: \"75008\"";
		MessageBuilder message = new MessageBuilder().add(locationSearchRequest);
		assertEquals(locationSearchRequestMessage, message.getMessage(), "Location request should be added");
	}

	// MessageBuilder add(IsLocation location)

	@Test
	void testAddLocationWithNull() {
		IsLocation nullLocation = null;
		MessageBuilder message = new MessageBuilder().add(nullLocation);
		assertTrue(StringUtils.isEmpty(message.getMessage()), "Message should be empty");
		assertNotNull(message.getMessage(), "Message should not be null");
	}

	@Test
	void testAddLocation() {
		IsLocation location = new TestLocation("Paris", "France", "Paris Region", "2.352222", "48.856613", "75008");
		String locationMessage = "name: \"Paris\" country: \"France\" region: \"Paris Region\" longitude: \"2.352222\" latitude: \"48.856613\" zip code: \"75008\"";
		MessageBuilder message = new MessageBuilder().add(location);
		assertEquals(locationMessage, message.getMessage(), "Location should be added");
	}

	// void changeBaseLocation(BaseLocation baseLocation, String changeTo)

	@Test
	void testChangeBaseLocation() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
		BaseLocation baseLocation = new BaseLocation();
		String newLocation = "UK";

		Method changeBaseLocationMethod = MessageBuilder.class.getDeclaredMethod("changeBaseLocation", BaseLocation.class, String.class);
		changeBaseLocationMethod.setAccessible(true);
		changeBaseLocationMethod.invoke(new MessageBuilder(), baseLocation, newLocation);
		assertEquals(newLocation, FieldUtils.readDeclaredField(baseLocation, "baseLocation", true), "Base location should be changed");
	}
}
