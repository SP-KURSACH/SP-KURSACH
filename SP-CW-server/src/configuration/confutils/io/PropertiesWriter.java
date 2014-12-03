package configuration.confutils.io;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map.Entry;
import java.util.Properties;

import configuration.confutils.Config;

/**
 * Надає можливість запису конфігурації у файл типу properties.
 * 
 * @author dan
 * 
 */
public class PropertiesWriter implements Writer {
	private File file;
	private Properties properties = new Properties();

	public PropertiesWriter(File file) {
		this.file = file;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void save(Config config) throws IOException {
		for (Entry<String, String> entry : config) {
			properties.setProperty(entry.getKey(), entry.getValue());
		}
		properties.store(new FileWriter(file), "");
	}

}
