package configuration.confutils.io;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import configuration.confutils.IConfig;

public class SAXParsingStrategy extends DefaultHandler {
	private IConfig config;
	private String curQName;

	public SAXParsingStrategy(IConfig config) {
		this.config = config;
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		curQName = qName;
	}

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		if (!curQName.equals("userdata") && !curQName.equals("")) {
			String value = new String(ch, start, length);
			config.setProperty(curQName, value);
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		curQName = "";
	}
}
