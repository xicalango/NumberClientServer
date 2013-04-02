package xx.servergui;

public class ClientGUIModel {

	private ObservableProperty<String> clientName = new ObservableProperty<>();
	private ObservableProperty<Integer> maximumValue = new ObservableProperty<>(255);
	private ObservableProperty<Integer> currentValue = new ObservableProperty<>();
	private ObservableProperty<Boolean> alarm = new ObservableProperty<Boolean>(false);
	
	public ObservableProperty<String> clientName() {
		return clientName;
	}
	
	public ObservableProperty<Integer> maximumValue() {
		return maximumValue;
	}
	
	public ObservableProperty<Integer> currentValue() {
		return currentValue;
	}

	public ObservableProperty<Boolean> alarm() {
		return alarm;
	}
	
}
