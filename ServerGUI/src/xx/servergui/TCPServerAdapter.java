package xx.servergui;

import java.net.ServerSocket;
import java.util.Properties;

import xx.numser.ServerRunner;
import xx.numser.tcp.NumberServerTCP;

public class TCPServerAdapter extends ServerAdapter<ServerSocket> {


	public TCPServerAdapter(Properties serverStartProperties,
			GUIModelHandler guiModelHandler) {
		super(serverStartProperties, guiModelHandler);
	}

	@Override
	protected ServerRunner<ServerSocket> createServer(
			Properties serverStartProperties) {
		return new NumberServerTCP(Integer.valueOf(serverStartProperties.getProperty("port","6502")));
	}
	
	
	
}
