package team.asd.tutorials.engine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

public class ServicesScannerUtils<T> {

	protected static Logger LOG = LoggerFactory.getLogger(ServicesScannerUtils.class);

	public T defineServiceImplementations(Class<? extends T> classItem) {
		try {
			return classItem.getConstructor().newInstance();
		} catch (Exception e) {
			LOG.error(String.format("Couldn't initialize %s implementation of %s.", classItem.getName(),
					Arrays.stream(classItem.getInterfaces()).findFirst().map(Class::getName).orElse("interface")));
			return null;
		}
	}

}
