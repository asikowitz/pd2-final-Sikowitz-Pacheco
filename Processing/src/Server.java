import java.net.*;
import java.io.IOException;

public class Server {	
	public static void main(String[] args) throws IOException {
		Socket[] sockets = new Socket[10];
		int c = 0;
		int finished = 0;

		System.out.println("Starting");
		
		while (c < 10) {
			ServerSocket serverSocket = null;
			boolean listeningSocket = true;
			
			try {
				serverSocket = new ServerSocket(6066);
			} catch (IOException e) {
				System.err.println("Could not listen on port: 6066");
			}
	
			while (listeningSocket) {
				sockets[c] = serverSocket.accept();
				c++;
			}
			serverSocket.close();
			
			if (finished - c >= 2) {
				MiniServer mini = new MiniServer(sockets[c], sockets[c+1]);
				mini.start();
				finished = finished + 2;
			}
		}
	}
}
