package configuration;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map.Entry;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;

import org.xml.sax.SAXException;

import configuration.confutils.Config;
import configuration.confutils.IConfig;
import configuration.confutils.io.Reader;
import configuration.confutils.io.Writer;

/**
 * Цей клас надає доступ до конфігурації додадку, надає методи до упраління нею.
 * 
 * @author Andrey
 * 
 */
public class AppConfig implements IConfig {
	private static AppConfig instance;
	private IConfig config = new Config();

	private AppConfig() {
	}

	synchronized public static AppConfig getInstance() {
		if (instance == null) {
			instance = new AppConfig();
		}
		return instance;
	}

	@Override
	public void setReader(Reader reader) {
		config.setReader(reader);
	}

	@Override
	public void setWriter(Writer writer) {
		config.setWriter(writer);
	}

	@Override
	public String getProperty(String name) {
		return config.getProperty(name);
	}

	@Override
	public void setProperty(String property, String value) {
		config.setProperty(property, value);
	}

	/**
	 * Записує конфігурацію у файл.
	 */
	@Override
	public void save() throws XMLStreamException, IOException {
		config.save();
	}

	/**
	 * Зчитує кофігурацію із файлу.
	 */
	@Override
	public void load() throws ParserConfigurationException, SAXException,
			IOException {
		config.load();
	}

	@Override
	public String toString() {
		return config.toString();
	}

	@Override
	public Iterator<Entry<String, String>> iterator() {
		return config.iterator();
	}
}
