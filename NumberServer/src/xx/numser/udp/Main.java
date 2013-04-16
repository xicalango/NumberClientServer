package xx.numser.udp;

import java.io.IOException;
import java.net.Socket;
import java.util.Arrays;

import xx.numser.DataHandler;

public class Main {

	public static void main(String[] args) throws IOException {
		NumberServerUDP nsudp = new NumberServerUDP(6502);
		nsudp.setHandler(new DataHandler() {
			public void dataReceived(Socket client, byte[] data) {
				System.out.println(Arrays.toString(data));
			}});
		
		nsudp.start();
		
		System.in.read();
		
		nsudp.stop();
		
		
	}
	
}
