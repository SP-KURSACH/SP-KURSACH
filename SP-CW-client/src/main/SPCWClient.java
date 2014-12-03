package main;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import networking.Connection;

public class SPCWClient {

	private static boolean shellRunning = true;
	private static String hostPrompt;

	public static void main(String[] args) throws NumberFormatException,
			UnknownHostException, IOException {
		Scanner sc = new Scanner(System.in);
		String username = null;
		String password = null;
		String hostPort = null;
		for (int i = 0; i < args.length; i++) {
			if (args[i].equals("-u")) {
				i++;
				if (i < args.length) {
					username = args[i];
				}
			} else if (args[i].equals("-p")) {
				i++;
				if (i < args.length) {
					password = args[i];
				}
			} else if (args[i].equals("-h")) {
				i++;
				if (i < args.length) {
					hostPort = args[i];
				}
			}
		}
		if (username == null || password == null || hostPort == null) {
			System.out
					.println("Usage: SPCWClient -u username -p password -h host:port");
			return;
		}
		String[] hostWithPort = hostPort.split(":");
		hostPrompt = username + '@' + hostWithPort[0] + ':';
		Socket socket = new Socket(hostWithPort[0],
				Integer.valueOf(hostWithPort[1]));
		Connection connection = new Connection(socket);
		inheritIO(System.in, connection);
		inheritIO(connection, System.out, hostWithPort[0]);
		connection.send(username);
		connection.send(password);
		if (System.getProperty("os.name").equals("Linux")) {
			connection.send("echo SPCW_PROMPT\"$(pwd)> \"");
		}
		setupEncryption(connection);
	}

	private static void setupEncryption(Connection connection) {
		// TODO Auto-generated method stub
		
	}

	private static void inheritIO(final InputStream src,
			final Connection connection) {
		new Thread(new Runnable() {
			public void run() {
				Scanner sc = new Scanner(src);
				while (sc.hasNextLine()) {
					String line = sc.nextLine();
					if (shellRunning && System.getProperty("os.name").equals("Linux")) {
						if (line.trim().equals("")) {
							connection.send("echo SPCW_PROMPT\"$(pwd)> \"");
						} else {
							connection.send(line
									+ ";echo SPCW_PROMPT\"$(pwd)> \"");
						}
						shellRunning = false;
					} else {
						connection.send(line); // ("echo SPCW_PROMPT\"$(pwd)> \";"+line);
					}
				}
				connection.send("exit");
				System.out.println();
				sc.close();
				try {
					connection.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}

	private static void inheritIO(final Connection connection,
			final OutputStream dest, final String prompt) {
		new Thread(new Runnable() {
			public void run() {
				PrintWriter pw = new PrintWriter(dest);
				String line;
				while ((line = connection.nextLine()) != null) {
					String corrected = correctLine(line);
					if (corrected == line) {
						pw.println(line);
					} else {
						pw.print(corrected);
						shellRunning = true;
					}
					pw.flush();
				}
			}

			private String correctLine(String line) {
				String promptMark = "SPCW_PROMPT";
				if (line.startsWith(promptMark)) {
					StringBuilder builder = new StringBuilder(line);
					builder.replace(0, promptMark.length(), hostPrompt);
					return builder.toString();
				}
				return line;
			}
		}).start();
	}

}
