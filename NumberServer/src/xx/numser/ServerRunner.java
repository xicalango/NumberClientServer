package xx.numser;

import java.io.IOException;

public abstract class ServerRunner<T> implements StartStoppable, Runnable {

	private T serverSocket;
	
	private boolean running = false;
	private Thread serverThread;
	private int port;
	private DataHandler handler;
	
	public ServerRunner(int port) {
		this.port = port;
	}

	public DataHandler getHandler() {
		return handler;
	}

	public void setHandler(DataHandler handler) {
		this.handler = handler;
	}
	
	protected abstract T openSocket(int port) throws IOException;
	protected abstract void handleServerSocket(T serverSocket) throws InterruptedException, IOException;
	protected abstract void closeSocket(T socket) throws IOException;

	@Override
	public void start() {
		if(running)
			return;
		
		try {
			serverSocket = openSocket(port);
			
			serverThread = new Thread(this, "ServerThread Port " + port);
			serverThread.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	

	
	
	@Override
	public void run() {
		
		if(!onInitialize()) {
			return;
		}
		
		running = true;
		
		while(running) {
			
			try {
				handleServerSocket(serverSocket);
			} catch (InterruptedException | IOException e) {
				e.printStackTrace();
				
				stop();
			}
		}
		
		stop();
	}


	@Override
	public void stop() {
		onDeinitialize();

		if(!running)
			return;
		
		running = false;
		
		if(serverThread != null)
			serverThread.interrupt();
		
		try {
			closeSocket(serverSocket);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	protected boolean onInitialize() {
		return true;
	}
	protected void onDeinitialize() { }

	public boolean isRunning() {
		return running;
	}
	
}
