package xx.numser.tcp;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import xx.numser.ServerRunner;

public class NumberServerTCP extends ServerRunner<ServerSocket>{

	private List<ClientHandleThread> clients = new ArrayList<>();
	
	public NumberServerTCP(int port) {
		super(port);
	}


	@Override
	protected ServerSocket openSocket(int port) throws IOException {
		return new ServerSocket(port);
	}
	
	@Override
	protected void handleServerSocket(ServerSocket serverSocket)
			throws InterruptedException, IOException {
		
		Socket clientSocket = serverSocket.accept();
		
		ClientHandleThread newClientThread = new ClientHandleThread(clientSocket, getHandler());
		newClientThread.start();
		clients.add( newClientThread );
	}
	
	@Override
	protected void closeSocket(ServerSocket socket) throws IOException {
		socket.close();
	}
	
	
	
	
	@Override
	protected void onDeinitialize() {
		
		for(ClientHandleThread clientThread : clients) {
			clientThread.stop();
		}
		
		super.onDeinitialize();
	}



	
}
