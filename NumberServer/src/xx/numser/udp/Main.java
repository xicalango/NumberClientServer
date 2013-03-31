package xx.numser.udp;

import java.io.IOException;
import java.util.Properties;

public class Main {

	public static void main(String[] args) throws IOException {
		
		Properties startProperties = new Properties();
		startProperties.put("port", "6502");
		
		NumberServerUDP nsudp = new NumberServerUDP(startProperties);
		
		nsudp.start();
		
		System.in.read();
		
		nsudp.stop();
		
		
	}
	
}
