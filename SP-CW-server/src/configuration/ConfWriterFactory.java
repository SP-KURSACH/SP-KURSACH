package configuration;

import java.io.File;
import java.io.IOException;

import configuration.confutils.io.PropertiesWriter;
import configuration.confutils.io.Writer;
import configuration.confutils.io.XMLWriter;

/**
 * Цей клас надає можливість отримати необхідний об'єкт спадкоємеця класу
 * Writer, залежно від файлу конфіґурації.
 * 
 * @author Andrey
 * 
 */
public class ConfWriterFactory {
	public static Writer getWriter(String file) throws IOException {
		if (file.endsWith(".xml")) {
			return new XMLWriter(new File(file));
		} else if (file.endsWith(".properties")) {
			return new PropertiesWriter(new File(file));
		} else {
			throw new IOException();
		}
	}
}
