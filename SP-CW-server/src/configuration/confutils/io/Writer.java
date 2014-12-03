package configuration.confutils.io;

import java.io.IOException;

import javax.xml.stream.XMLStreamException;

import configuration.confutils.Config;

/**
 * Надає можливість запису конфігурації у файл.
 * 
 * @author dan
 * 
 */
public interface Writer {

	/**
	 * Записує конфігурацію у файл.
	 * 
	 * @param userdata
	 *            - конфіг, який записується.
	 * @throws XMLStreamException
	 * @throws IOException
	 */
	void save(Config config) throws XMLStreamException, IOException;
}
