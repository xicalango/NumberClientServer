package xx.servergui;

import java.net.DatagramSocket;
import java.util.Properties;

import xx.numser.ServerRunner;
import xx.numser.udp.NumberServerUDP;

public class UDPServerAdapter extends ServerAdapter<DatagramSocket> {

	public UDPServerAdapter(Properties serverStartProperties,
			GUIModelHandler guiModelHandler) {
		super(serverStartProperties, guiModelHandler);
	}

	@Override
	protected ServerRunner<DatagramSocket> createServer(
			Properties serverStartProperties) {
		return new NumberServerUDP(Integer.valueOf(serverStartProperties.getProperty("port","6502")));
	}



}
