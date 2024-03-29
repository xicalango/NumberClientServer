package xx.numser.tcp;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.logging.Logger;

import xx.numser.DataHandler;
import xx.numser.StartStoppable;

public class ClientHandleThread implements Runnable, StartStoppable {
	
	private static final Logger LOGGER = Logger.getLogger(ClientHandleThread.class.getName());
	
	private Thread thread;
	private Socket clientSocket;
	private boolean running = false;
	private InputStream clientInputStream;
	
	private DataHandler handler;
	
	public ClientHandleThread(Socket clientSocket, DataHandler handler) {
		this.clientSocket = clientSocket;
		this.handler = handler;
		
		LOGGER.info("ClientThread created for: " + clientSocket);
	}
	
	public void start() {
		thread = new Thread(this, clientSocket.toString());
		thread.start();
		
		LOGGER.info("ClientThread started for: " + clientSocket);
	}

	@Override
	public void run() {
		running = true;
		try {
			clientInputStream = clientSocket.getInputStream();
		} catch (IOException e1) {
			e1.printStackTrace();
			stop();
			return;
		}
		
		while(running) {
			byte[] buf = new byte[2];
			
			int readBytes;
			try {
				
				readBytes = clientInputStream.read(buf);
				
				if(readBytes == -1) {
					LOGGER.info("Client " + clientSocket + " departed");
					stop();
				}
				else {
					handler.dataReceived(clientSocket, buf);
				}
				
			} catch (IOException e) {
				e.printStackTrace();
				stop();
			}
			
		}
		
	}

	@Override
	public void stop() {
		running = false;
		thread.interrupt();
		try {
			thread.join();
		} catch (InterruptedException e) {
			// Do nothing
		}
	}
	

}
