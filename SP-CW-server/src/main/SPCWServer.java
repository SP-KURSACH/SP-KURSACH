package main;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import networking.Network;

import org.xml.sax.SAXException;

import userdata.Passwords;
import utils.R;
import configuration.AppConfig;
import configuration.ConfReaderFactory;
import configuration.ConfWriterFactory;

public class SPCWServer {

	public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException {
		Passwords.load("testFile");
		String file = (String) R.getResource("config");
		AppConfig appConfig = AppConfig.getInstance();
		appConfig.setReader(ConfReaderFactory.getReader(file));
		appConfig.load();
		appConfig.setWriter(ConfWriterFactory.getWriter(file));
		final Network network = new Network(3336);
		Runtime.getRuntime().addShutdownHook(new Thread() {
			public void run() {
				System.out.println("Shutting down...");
				network.cancel();
				network.waitForFinished();
			}
		});
		network.start();
	}

}
