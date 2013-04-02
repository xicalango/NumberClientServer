package xx.numser;

import java.io.IOException;
import java.util.Properties;

public abstract class ServerRunner<T> implements StartStoppable, Runnable {

	private T socket;
	
	private boolean running = false;
	private Thread serverThread;
	
	private Properties serverStartProperties;
	
	private DataHandler handler;
	
	public DataHandler getHandler() {
		return handler;
	}

	public void setHandler(DataHandler handler) {
		this.handler = handler;
	}

	public ServerRunner(Properties serverStartProperties) {
		this.serverStartProperties = serverStartProperties;
	}
	
	public abstract ServerSocketFactory<T> getServerSocketFactory();
	
	@Override
	public void start() {
		try {
			socket = getServerSocketFactory().openSocket(Integer.valueOf(serverStartProperties.getProperty("port")));
			
			serverThread = new Thread(this, "ServerThread");
			serverThread.start();
			
			
		} catch (NumberFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void stop() {
		
		running = false;
		if(serverThread != null)
			serverThread.interrupt();
		
		try {
			getServerSocketFactory().close(socket);
		} catch (IOException e) {
			// TODO Auto-generated catch block
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
				doMainLoop(socket);
			} catch (InterruptedException | IOException e) {
				e.printStackTrace();
				
				stop();
			}
			
		}
		
		stop();
		
		onDeinitialize();
	}


	protected abstract void doMainLoop(T serverSocket) throws InterruptedException, IOException;

	protected boolean onInitialize() {
		return true;
	}
	protected void onDeinitialize() { }

	public boolean isRunning() {
		return running;
	}
	
}
