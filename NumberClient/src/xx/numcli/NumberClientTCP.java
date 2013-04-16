package xx.numcli;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class NumberClientTCP implements Runnable {

	private Socket destinationSocket;
	
	private int valueMax = 256;
	private int clientNumber;
	
	private Thread thread;
	
	
	
	public NumberClientTCP(int clientNumber) {
		super();
		this.clientNumber = clientNumber;
	}


	public void start() {
		thread = new Thread(this, "Client");
		thread.start();
	}


	@Override
	public void run() {
		
		try {
			destinationSocket = new Socket("localhost", 6502);
			
			byte[] buf = new byte[2];
			buf[0] = (byte)clientNumber;
			
			OutputStream os = destinationSocket.getOutputStream();
			
			for( int i = 0; i < valueMax; i++) {
				buf[1] = (byte)i;
				
				os.write(buf);
				Thread.sleep(50);
			}
			
			
			destinationSocket.close();
			
		} catch ( IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
	public static void main(String[] args) {
		
		List<NumberClientTCP> ncs = new ArrayList<>();
		
		for( int i = 0; i < 24; i++ ) {
			NumberClientTCP nc = new NumberClientTCP(i);
			
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			nc.start();
			ncs.add(nc);
		}
		
		for(NumberClientTCP nc : ncs) {
			try {
				nc.thread.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		
		
	}
	
}
