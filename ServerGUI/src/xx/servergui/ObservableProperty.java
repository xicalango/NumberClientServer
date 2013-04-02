package xx.servergui;

import java.util.ArrayList;
import java.util.Collection;

public class ObservableProperty<T> extends ProtectedProperty<T> {

	private Collection< PropertyObserver<T> > propertyObservers = new ArrayList<>();
	
	@Override
	public void setValue(T value) {
		super.setValue(value);
		notifyObservers(null);
	}
	
	public void setValue(T value, Object source) {
		super.setValue(value);
		notifyObservers(source);
	}
	
	public ObservableProperty() {
		super();
	}

	public ObservableProperty(T value) {
		super(value);
	}

	private void notifyObservers(Object source) {
		for( PropertyObserver<T> observers : propertyObservers) {
			observers.propertyChanged(getValue(), source);
		}
	}
	
	public void addObserver( PropertyObserver<T> observer ) {
		propertyObservers.add(observer);
	}

	public void removeObserver( PropertyObserver<T> observer ) {
		propertyObservers.remove(observer);
	}

}
