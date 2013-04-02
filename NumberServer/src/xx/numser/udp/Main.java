package xx.numser.udp;

import java.io.IOException;
import java.net.Socket;
import java.util.Arrays;
import java.util.Properties;

import xx.numser.DataHandler;

public class Main {

	public static void main(String[] args) throws IOException {
		
		Properties startProperties = new Properties();
		startProperties.put("port", "6502");
		
		NumberServerUDP nsudp = new NumberServerUDP(startProperties);
		nsudp.setHandler(new DataHandler() {
			public void dataReceived(Socket client, byte[] data) {
				System.out.println(Arrays.toString(data));
			}});
		
		nsudp.start();
		
		System.in.read();
		
		nsudp.stop();
		
		
	}
	
}
