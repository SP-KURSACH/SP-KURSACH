package configuration.confutils.io;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

import configuration.confutils.Config;

/**
 * Надає можливість зчитувати конфігурацію із файлу типу xml.
 * 
 * @author dan
 * 
 */
public class XMLReader implements Reader {
	private File file;

	public XMLReader(File file) {
		this.file = file;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void load(Config config) throws ParserConfigurationException,
			SAXException, IOException {
		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser parser = factory.newSAXParser();
		SAXParsingStrategy parseStrategy = new SAXParsingStrategy(config);
		parser.parse(file, parseStrategy);
	}
}
