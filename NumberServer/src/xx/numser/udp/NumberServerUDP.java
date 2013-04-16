package xx.numser.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

import xx.numser.ServerRunner;

public class NumberServerUDP extends ServerRunner<DatagramSocket> {

	public NumberServerUDP(int port) {
		super(port);
	}
	
	@Override
	protected DatagramSocket openSocket(int port) throws IOException {
		return new DatagramSocket(port);
	}

	@Override
	protected void handleServerSocket(DatagramSocket serverSocket)
			throws InterruptedException, IOException {
		
		byte[] buf = new byte[2];
		
		DatagramPacket packet = new DatagramPacket(buf, buf.length);
		
		serverSocket.receive(packet);
		
		getHandler().dataReceived(null, packet.getData());
	}

	@Override
	protected void closeSocket(DatagramSocket socket) throws IOException {
		socket.close();
	}
	
}
