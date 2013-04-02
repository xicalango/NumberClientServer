package xx.servergui;

import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import xx.numser.DataHandler;
import xx.numser.ServerRunner;

public abstract class ServerAdapter<T> implements DataHandler {

	private ServerRunner<T> server;
	private GUIModelHandler guiModelHandler;
	private Map<Integer, ClientGUIModel> clientGuiModels;

	
	public ServerAdapter(Properties serverStartProperties, GUIModelHandler guiModelHandler) {
		
		server = createServer(serverStartProperties);
		server.setHandler(this);
		this.guiModelHandler = guiModelHandler;
		
		clientGuiModels = new HashMap<>();
		
	}
	
	protected abstract ServerRunner<T> createServer(Properties serverStartProperties);
	
	public void start() {
		server.start();
	}
	
	public void stop() {
		server.stop();
	}
	
	public boolean isRunning() {
		return server.isRunning();
	}

	@Override
	public void dataReceived(Socket client, byte[] data) {
		
		int clientNumber = data[0] & 0xFF;
		int value = data[1]& 0xFF;
		
		if(!clientGuiModels.containsKey(clientNumber)) {
			addNewClient(clientNumber);
		}
		
		updateClientValue(clientGuiModels.get(clientNumber), value);
	}
	
	protected void updateClientValue(ClientGUIModel clientGUIModel, int value) {
		
		if(clientGUIModel.currentValue().hasValue()) {
			if(clientGUIModel.currentValue().getValue() >= value) {
				clientGUIModel.alarm().setValue(true);
			}
		}
		
		clientGUIModel.currentValue().setValue(value);
	}

	protected void addNewClient(int clientNumber) {
		ClientGUIModel newClientGUIModel = new ClientGUIModel();
		newClientGUIModel.clientName().setValue(""+clientNumber);
		
		guiModelHandler.newClientGUIModel(newClientGUIModel);
		
		clientGuiModels.put(clientNumber, newClientGUIModel);
	}
	
	public void reset() {
		clientGuiModels.clear();
	}
	
}
