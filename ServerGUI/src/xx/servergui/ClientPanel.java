
package xx.servergui;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.border.TitledBorder;

public class ClientPanel extends JPanel{

	private static final long serialVersionUID = 1L;
	
	private JLabel nameLabel;
	private JProgressBar progressBar;
	private JLabel valueLabel;
	
	private ClientGUIModel model;
	
	private TitledBorder border;
	
	public ClientPanel(ClientGUIModel model) {
		
		this.model = model;
		
		setupGUI();
		setupModel();
	}

	private void setupModel() {
		
		if(model.clientName().hasValue()) {
			border.setTitle("Client " + model.clientName());
			nameLabel.setText(model.clientName().toString());
		}
		if(model.maximumValue().hasValue()) {
			progressBar.setMaximum(model.maximumValue().getValue());
		}
		if(model.currentValue().hasValue()) {
			progressBar.setValue(model.currentValue().getValue());
		}
		
		
		model.clientName().addObserver(new PropertyObserver<String>() {
			public void propertyChanged(String value, Object source) {
				nameLabel.setText("Client: " + value);
				border.setTitle("Client " + value);
			}});
		
		model.maximumValue().addObserver(new PropertyObserver<Integer>() {
			public void propertyChanged(Integer value, Object source) {
				progressBar.setMaximum(value);
				
				updateValueLabel();
			}});
		
		model.currentValue().addObserver(new PropertyObserver<Integer>() {
			public void propertyChanged(Integer value, Object source) {
				progressBar.setValue(value);
				updateValueLabel();
			}});
		model.alarm().addObserver(new PropertyObserver<Boolean>() {
			public void propertyChanged(Boolean value, Object source) {
				if(value) {
					progressBar.setBackground(Color.red);
				} else {
					progressBar.setBackground(Color.white);					
				}
			}});
	}

	private void setupGUI() {
		border = BorderFactory.createTitledBorder("Client");
		setBorder(border);
		
		setLayout(new GridLayout(3, 1));
		
		nameLabel = new JLabel("Client");
		add(nameLabel);
		
		valueLabel = new JLabel();
		add(valueLabel);
		
		progressBar = new JProgressBar();
		add(progressBar);
		
	}
	
	private void updateValueLabel() {
		valueLabel.setText(model.currentValue() + "/" + model.maximumValue());
	}
	


}
