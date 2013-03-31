package xx.numcli;

import java.io.IOException;
import java.io.OutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.List;

public class NumberClientUDP implements Runnable {

	private DatagramSocket destinationSocket;
	
	private int valueMax = 256;
	private int number;
	
	private Thread thread;
	
	

	public NumberClientUDP(int number) {
		super();
		this.number = number;
	}

	public void start() {
		thread = new Thread(this, "Client");
		thread.start();
	}
	
	@Override
	public void run() {
		
		try {
			destinationSocket = new DatagramSocket();
			
			byte[] buf = new byte[2];
			
			InetAddress address = InetAddress.getLocalHost();
			
			buf[0] = (byte)number;
			
			for( int i = 0; i < valueMax; i++) {
				
				buf[1] = (byte)i;
				
				DatagramPacket packet = new DatagramPacket(buf, buf.length, address, 6502);
				
				destinationSocket.send(packet);
			}
			
			
			destinationSocket.close();
			
		} catch ( IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
	public static void main(String[] args) {
		
		List<NumberClientUDP> ncs = new ArrayList<>();
		
		for( int i = 0; i < 24; i++ ) {
			NumberClientUDP nc = new NumberClientUDP(i);
			nc.start();
			ncs.add(nc);
		}
		
		for(NumberClientUDP nc : ncs) {
			try {
				nc.thread.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		
		
	}
	
	
	
}
