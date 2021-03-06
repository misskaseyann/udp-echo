import java.io.*;
import java.net.*;
import java.nio.*;
import java.nio.channels.*;
import java.util.*;

class udpserver {
	public static void main(String args[]) {
		try {
			DatagramChannel c = DatagramChannel.open();
			// cant time out the socket so we use the selector to do it
			Selector s = Selector.open(); // use and test channel to see if it can read data from it
			c.configureBlocking(false); // only works if channel is non blocking
			c.register(s, SelectionKey.OP_READ);
			c.bind(new InetSocketAddress(9876));
			while(true) {
				int n = s.select(5000); // 5 seconds
				if (n == 0) {
					System.out.println("Got a timeout");
				} else {
					Iterator i = s.selectedKeys().iterator();
					while(i.hasNext()) {
						SelectionKey k = (SelectionKey)i.next();
						ByteBuffer buf = ByteBuffer.allocate(4096);
						SocketAddress clientaddr = c.receive(buf);
						String message = new String(buf.array());
						System.out.println(message);
						i.remove();
					}
				}
			}
		} catch (IOException e) {
			System.out.println("You should probably handle exceptions better.");
		}
	}
}