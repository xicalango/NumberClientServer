package xx.numser;

import java.io.IOException;
import java.net.DatagramSocket;

public class UDPServerSocketFactory implements ServerSocketFactory<DatagramSocket>{

	@Override
	public DatagramSocket openSocket(int port) throws IOException {
		return new DatagramSocket(port);
	}

	@Override
	public void close(DatagramSocket socket) throws IOException {
		socket.close();
	}

}
