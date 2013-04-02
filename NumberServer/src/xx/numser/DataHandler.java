package xx.numser;

import java.net.Socket;

public interface DataHandler {
	void dataReceived( Socket client, byte[] data );
}
