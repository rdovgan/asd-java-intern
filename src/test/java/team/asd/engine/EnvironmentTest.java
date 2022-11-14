package team.asd.engine;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.util.Properties;

public class EnvironmentTest {

	@Test
	public void testPropertyEnvironmentValue() {

		Properties properties = new Properties();

		try {
			ClassLoader classLoader = EnvironmentTest.class.getClassLoader();
			InputStream applicationPropertiesStream = classLoader.getResourceAsStream("application.properties");
			properties.load(applicationPropertiesStream);
		} catch (Exception e) {
			// process the exception
		}

		String value = properties.getProperty("test.property");
		Assertions.assertNotNull(value);
		Assertions.assertEquals("v@lue", value);
	}

}
