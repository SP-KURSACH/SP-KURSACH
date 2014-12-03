package configuration.confutils.io;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import configuration.confutils.Config;

/**
 * Надає можливість зчитувати конфігурацію із файлу.
 * 
 * @author dan
 * 
 */
public interface Reader {

	/**
	 * Завантажує конфігурацію.
	 * 
	 * @param userdata
	 *            - конфіг, який модифікується.
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	void load(Config config) throws ParserConfigurationException, SAXException,
			IOException;

}
