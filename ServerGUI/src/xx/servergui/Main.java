package xx.servergui;

import java.util.Properties;

public class Main {

	
	public static void main(String[] args) {
		
		Properties serverStartProperties = new Properties();
		serverStartProperties.setProperty("port", "6502");
		
		ServerFrame serverFrame = new ServerFrame(serverStartProperties);
		
		serverFrame.setVisible(true);
		
	}
	
}
