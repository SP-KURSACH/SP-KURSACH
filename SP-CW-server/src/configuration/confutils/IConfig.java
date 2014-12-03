package configuration.confutils;

import java.io.IOException;
import java.util.Map.Entry;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;

import org.xml.sax.SAXException;

import configuration.confutils.io.Reader;
import configuration.confutils.io.Writer;

/**
 * Загальний інтерфейс класів управління гунфігурацією.
 * 
 * @author dan
 * 
 */
public interface IConfig extends Iterable<Entry<String, String>> {
	String getProperty(String name);

	void setProperty(String property, String value);

	void setReader(Reader reader);

	void setWriter(Writer writer);

	void save() throws XMLStreamException, IOException;

	void load() throws ParserConfigurationException, SAXException, IOException;
}
