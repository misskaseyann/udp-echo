import java.io.*;
import java.net.*;
import java.nio.*;
import java.nio.channels.*;

class udpclient {
	public static void main(String args[]) {
		try {
			DatagramChannel sc = DatagramChannel.open();
			Console cons = System.console();
			//String m = cons.readLine("Enter your message: ");
			//ByteBuffer buf = ByteBuffer.wrap(m.getBytes());
			
			boolean isint = false;
			int p = 0;

			// Ask for an IP address.
			String a = "127.0.0.1";
			String m = cons.readLine("Give an IP address: ");
			if (m.matches("(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)")) a = m;
			else System.out.println("Not an address. Using default.");

			// Ask for a port number.
			while(!isint) {
				m = cons.readLine("Give a port number: ");
				if (m.matches("^-?\\d+$")) { 
					p = Integer.parseInt(m);
					isint = true;
				} else System.out.println("Not a number.");
			}

			m = cons.readLine("Enter your message: ");
			ByteBuffer buf = ByteBuffer.wrap(m.getBytes());

			sc.send(buf, new InetSocketAddress(a, p));

			ByteBuffer buf2 = ByteBuffer.allocate(5000);
			sc.receive(buf2);
			String message = new String(buf2.array()); // convert to string
			System.out.println("Got from server: " + message);
		} catch (IOException e) {
			System.out.println("You should probably handle exceptions better.");
		}
	}
}