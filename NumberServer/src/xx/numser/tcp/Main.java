package xx.numser.tcp;

import java.io.IOException;
import java.util.Properties;


public class Main {

	public static void main(String[] args) throws IOException {
		
		
		Properties startProperties = new Properties();
		startProperties.put("port", "6502");
		
		NumberServerTCP nstcp = new NumberServerTCP(startProperties);
		
		nstcp.start();
		
		System.in.read();
		
		nstcp.stop();
	}
	
}
