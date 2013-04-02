package xx.numser.tcp;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.server.SocketSecurityException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import xx.numser.DataHandler;
import xx.numser.ServerRunner;
import xx.numser.ServerSocketFactory;
import xx.numser.TCPServerSocketFactory;

public class NumberServerTCP extends ServerRunner<ServerSocket>{
	
	private List<ClientHandleThread> clients = new ArrayList<>();
	private ServerSocketFactory<ServerSocket> factory = new TCPServerSocketFactory();
	
	public NumberServerTCP(Properties serverStartProperties) {
		super(serverStartProperties);
	}


	@Override
	protected void doMainLoop(ServerSocket serverSocket)
			throws InterruptedException, IOException {
		
		Socket clientSocket = serverSocket.accept();
		
		ClientHandleThread newClientThread = new ClientHandleThread(clientSocket, getHandler());
		newClientThread.start();
		clients.add( newClientThread );
	}

	@Override
	public ServerSocketFactory<ServerSocket> getServerSocketFactory() {
		return factory;
	}
	
	@Override
	protected void onDeinitialize() {
		
		for(ClientHandleThread clientThread : clients) {
			clientThread.stop();
		}
		
		super.onDeinitialize();
	}
	
}
