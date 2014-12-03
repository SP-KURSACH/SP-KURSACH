package configuration.confutils.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map.Entry;
import java.util.Properties;

import configuration.confutils.Config;

/**
 * Надає можливість зчитувати конфігурацію із файлу типу properties.
 * 
 * @author dan
 * 
 */
public class PropertiesReader implements Reader {
	private Properties properties = new Properties();
	private File file;

	public PropertiesReader(File file) {
		this.file = file;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void load(Config config) throws FileNotFoundException, IOException {
		properties.load(new FileReader(file));
		for (Entry<Object, Object> entry : properties.entrySet()) {
			config.setProperty(entry.getKey().toString(), entry.getValue()
					.toString());
		}
		properties.clear();
	}
}
