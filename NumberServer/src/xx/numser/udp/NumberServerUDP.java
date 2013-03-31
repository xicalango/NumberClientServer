package xx.numser.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.Arrays;
import java.util.Properties;

import xx.numser.ServerRunner;
import xx.numser.ServerSocketFactory;
import xx.numser.StartStoppable;
import xx.numser.UDPServerSocketFactory;

public class NumberServerUDP extends ServerRunner<DatagramSocket> {

	public NumberServerUDP(Properties serverStartProperties) {
		super(serverStartProperties);
	}

	private ServerSocketFactory<DatagramSocket> serverSocketFactory = new UDPServerSocketFactory();
	
	@Override
	public ServerSocketFactory<DatagramSocket> getServerSocketFactory() {
		return serverSocketFactory;
	}

	@Override
	protected void doMainLoop(DatagramSocket serverSocket)
			throws InterruptedException, IOException {
		
		byte[] buf = new byte[2];
		
		DatagramPacket packet = new DatagramPacket(buf, buf.length);
		
		serverSocket.receive(packet);
		
		System.out.println(Arrays.toString(packet.getData()));
	}
	
}
