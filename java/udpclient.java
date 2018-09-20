import java.io.*;
import java.net.*;
import java.nio.*;
import java.nio.channels.*;

class udpclient {
	public static void main(String args[]) {
		try {
			DatagramChannel sc = DatagramChannel.open();
			Console cons = System.console();
			String m = cons.readLine("Enter your message: ");
			ByteBuffer buf = ByteBuffer.wrap(m.getBytes());
			sc.send(buf, new InetSocketAddress("127.0.0.1", 9876));
		} catch (IOException e) {
			System.out.println("You should probably handle exceptions better.");
		}
	}
}