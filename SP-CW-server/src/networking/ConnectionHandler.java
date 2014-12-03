package networking;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

import userdata.Passwords;
import configuration.AppConfig;

public class ConnectionHandler extends Thread {
	private Connection connection;

	public ConnectionHandler(Connection connection) {
		this.connection = connection;
	}

	@Override
	public void run() {
		try {
			if (isAuthorized()) {
				setupEncryption();
				String shellName = AppConfig.getInstance().getProperty(
						"shell-name");
				ProcessBuilder builder = new ProcessBuilder(shellName);
				builder.redirectErrorStream(true);
				builder.redirectInput(ProcessBuilder.Redirect.PIPE);
				builder.redirectOutput(ProcessBuilder.Redirect.PIPE);
				Process process = builder.start();
				inheritIO(process.getInputStream(), connection);
				inheritIO(connection, process.getOutputStream());
				System.out.println("exitWith: "+process.waitFor());
			} else {
				connection.send("Unauthorized connection");
			}
			connection.close();
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void setupEncryption() {
		// TODO Auto-generated method stub
		
	}

	private static void inheritIO(final InputStream src,
			final Connection connection) {
		new Thread(new Runnable() {
			public void run() {
				Scanner sc = new Scanner(src);
				while (sc.hasNextLine()) {
					String line = sc.nextLine();
					connection.send(line);
				}
				sc.close();
			}
		}).start();
	}
	
	private static void inheritIO(final Connection connection, final OutputStream out) {
		new Thread(new Runnable() {
			public void run() {
				PrintWriter pw = new PrintWriter(out);
				String line;
				while ((line = connection.nextLine()) != null) {
					pw.println(line);
					pw.flush();
				}
			}
		}).start();
	}

	private boolean isAuthorized() throws IOException {
		String login = connection.nextLine();
		String password = connection.nextLine();
		if (password != null && login != null) {
			if (Passwords.get(login) != null) {
				System.out.println("Connected: "+login);
				return true;
			}
		}
		return false;
	}
}
