package xx.numser.tcp;

import java.io.IOException;
import java.net.Socket;
import java.util.Arrays;

import xx.numser.DataHandler;


public class Main {

	public static void main(String[] args) throws IOException {
		
		NumberServerTCP nstcp = new NumberServerTCP(6502);
		nstcp.setHandler(new DataHandler() {
			public void dataReceived(Socket client, byte[] data) {
				System.out.println(client+ ": " + Arrays.toString(data));
			}});
		
		nstcp.start();
		
		System.in.read();
		
		nstcp.stop();
	}
	
}
