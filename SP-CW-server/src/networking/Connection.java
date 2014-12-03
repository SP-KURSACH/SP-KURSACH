package networking;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Connection {
	private Socket socket;
	private Scanner sc;
	private PrintWriter pw;

	public Connection(Socket socket) throws IOException {
		this.socket = socket;
		sc = new Scanner(socket.getInputStream());
		pw = new PrintWriter(socket.getOutputStream());
	}
	
	public void send(String massage) {
		pw.println(massage);
		pw.flush();
	}
	
	public String nextLine() {
		if (sc.hasNextLine()) {
			return sc.nextLine();
		}
		return null;
	}
	
	public void close() throws IOException {
		pw.close();
		sc.close();
		socket.close();
	}
	
	
}
