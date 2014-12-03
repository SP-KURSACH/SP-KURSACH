package configuration;

import java.io.File;
import java.io.IOException;

import configuration.confutils.io.PropertiesReader;
import configuration.confutils.io.Reader;
import configuration.confutils.io.XMLReader;

/**
 * Цей клас надає можливість отримати необхідний об'єкт спадкоємеця класу
 * Reader, залежно від файлу конфіґурації.
 * 
 * @author Andrey
 * 
 */
public class ConfReaderFactory {
	public static Reader getReader(String file) throws IOException {
		if (file.endsWith(".xml")) {
			return new XMLReader(new File(file));
		} else if (file.endsWith(".properties")) {
			return new PropertiesReader(new File(file));
		} else {
			throw new IOException();
		}
	}
}
