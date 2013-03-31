package xx.numser;

import java.io.IOException;
import java.net.ServerSocket;

public class TCPServerSocketFactory implements ServerSocketFactory<ServerSocket>{

	@Override
	public ServerSocket openSocket(int port) throws IOException {
		return new ServerSocket(port);
	}

	@Override
	public void close(ServerSocket socket) throws IOException {
		socket.close();
	}

}
