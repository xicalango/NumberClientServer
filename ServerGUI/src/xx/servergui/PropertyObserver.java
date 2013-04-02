package xx.servergui;

public interface PropertyObserver<T> {

	void propertyChanged( T value, Object source );
	
}
