package xx.numser;

import java.io.IOException;

public interface ServerSocketFactory<T> {
	T openSocket(int port) throws IOException;
	
	void close(T socket) throws IOException;
}
