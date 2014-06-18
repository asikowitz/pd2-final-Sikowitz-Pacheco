import java.net.*;
import java.io.*;

public class MiniServer extends Thread {
	private Socket s1, s2;

	public MiniServer(Socket s1, Socket s2) {
		super("MiniServer");
		this.s1 = s1;
		this.s2 = s2;
	}

	public void run() {
		System.out.println("Game made with " + s1.getRemoteSocketAddress() + " and " + s2.getRemoteSocketAddress());
		try {
			ObjectInputStream in1 = new ObjectInputStream(s1.getInputStream());
			ObjectOutputStream out1 = new ObjectOutputStream(s1.getOutputStream());
			ObjectInputStream in2 = new ObjectInputStream(s2.getInputStream());
			ObjectOutputStream out2 = new ObjectOutputStream(s2.getOutputStream());
			
			while (in1 != null) {
				out1.writeObject(in2.readObject());
				out2.writeObject(in1.readObject());
			}
			
			in1.close();
			out1.close();
			in2.close();
			out2.close();
			s1.close();
			s2.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		System.out.println("Game Over");
	}
}