package networking;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class Network {
	private ServerSocket servSocket;
	private volatile boolean canceled;
	private volatile boolean finished;

	public Network(int port) throws IOException {
		servSocket = new ServerSocket(port);
	}

	public void start() throws IOException {
		servSocket.setSoTimeout(500);
		while (!canceled) {
			try {
				Socket socket = servSocket.accept();
				ConnectionHandler handler = new ConnectionHandler(
						new Connection(socket));
				handler.start();
			} catch (SocketTimeoutException e) {
			}
		}
		finished = true;
	}

	public void cancel() {
		canceled = true;
	}

	public void waitForFinished() {
		while (!finished) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
