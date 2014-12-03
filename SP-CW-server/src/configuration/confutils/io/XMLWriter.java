package configuration.confutils.io;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map.Entry;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import configuration.confutils.Config;

/**
 * Надає можливість запису конфігурації у файл типу xml.
 * 
 * @author dan
 * 
 */
public class XMLWriter implements Writer {
	private XMLOutputFactory factory = XMLOutputFactory.newInstance();
	private File file;

	public XMLWriter(File file) {
		this.file = file;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void save(Config config) throws XMLStreamException, IOException {

		XMLStreamWriter writer = factory.createXMLStreamWriter(new FileWriter(
				file));
		writer.writeStartDocument("UTF-8", "1.0");
		writer.writeCharacters("\n");
		writer.writeStartElement("userdata");
		writer.writeCharacters("\n");
		for (Entry<String, String> entry : config) {
			writer.writeCharacters("    ");
			writer.writeStartElement(entry.getKey());
			writer.writeCharacters(entry.getValue());
			writer.writeEndElement();
			writer.writeCharacters("\n");
		}
		writer.writeEndElement();
		writer.writeEndDocument();
		writer.writeCharacters("\n");
		writer.flush();
		writer.close();
	}

}
