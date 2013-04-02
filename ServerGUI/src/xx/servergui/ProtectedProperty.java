package xx.servergui;

public class ProtectedProperty<T> {

	private T value;
	
	public ProtectedProperty() {
		this(null);
	}
	
	public ProtectedProperty(T value) {
		this.value = value;
	}

	public T getValue() {
		return value;
	}
	
	public void setValue(T value) {
		this.value = value;
	}
	
	public boolean hasValue() {
		return value != null;
	}
	
	@Override
	public String toString() {
		if(hasValue()) 
			return value.toString();
		else
			return "[null]";
	}
	
}
