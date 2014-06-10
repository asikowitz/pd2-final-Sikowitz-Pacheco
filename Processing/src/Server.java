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
				System.out.println("Just connected to " + sockets[c].getRemoteSocketAddress());
				c++;
				
				if (c - finished >= 2) {
					System.out.println("Game made");
					MiniServer mini = new MiniServer(sockets[finished], sockets[finished+1]);
					mini.start();
					finished = finished + 2;
				}
			}
			System.out.println("Goodbye");
			serverSocket.close();
		}
	}
}