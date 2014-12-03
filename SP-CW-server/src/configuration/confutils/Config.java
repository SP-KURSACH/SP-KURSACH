package configuration.confutils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;

import org.xml.sax.SAXException;

import configuration.confutils.io.Reader;
import configuration.confutils.io.Writer;

/**
 * Конкретнй клас управління конфігурацією.
 * 
 * @author dan
 * 
 */
public class Config implements IConfig {
	private HashMap<String, String> config = new HashMap<>();
	private Reader reader;
	private Writer writer;

	public Config() {
	}

	public void setReader(Reader reader) {
		this.reader = reader;
	}

	public void setWriter(Writer writer) {
		this.writer = writer;
	}

	@Override
	public String getProperty(String key) {
		return config.get(key);
	}

	@Override
	public void setProperty(String key, String value) {
		config.put(key, value);
	}

	@Override
	public void load() throws ParserConfigurationException, SAXException,
			IOException {
		reader.load(this);
	}

	@Override
	public void save() throws XMLStreamException, IOException {
		writer.save(this);
	}

	@Override
	public String toString() {
		StringBuilder res = new StringBuilder();
		for (Entry<String, String> entry : config.entrySet()) {
			res.append(entry.getKey());
			res.append(" = ");
			res.append(entry.getValue());
			res.append('\n');
		}
		return res.toString();
	}

	@Override
	public Iterator<Entry<String, String>> iterator() {
		return config.entrySet().iterator();
	}
}
