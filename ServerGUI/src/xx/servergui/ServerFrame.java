package xx.servergui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ServerFrame extends JFrame implements GUIModelHandler {

	private static final long serialVersionUID = 1L;
	
	private List<ClientPanel> panels = new ArrayList<>();
	
	private JPanel contentPanel;
	
	private TCPServerAdapter tcpAdapter;
	private UDPServerAdapter udpAdapter;

	private JPanel clientsPanel;
	
	private JButton startTCPButton;
	private JButton startUDPButton;
	private JButton stopButton;
	private JButton resetButton;
	
	public ServerFrame(Properties serverStartProperties) {
		
		tcpAdapter = new TCPServerAdapter(serverStartProperties, this);
		udpAdapter = new UDPServerAdapter(serverStartProperties, this);
		
		clientsPanel = new JPanel(new GridLayout(6, 4));
		
		contentPanel = new JPanel(new BorderLayout());
		contentPanel.add(clientsPanel, BorderLayout.CENTER);
		
		JPanel buttonPanel = new JPanel(new GridLayout(1, 3));

		resetButton = new JButton("Reset");
		resetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tcpAdapter.reset();
				udpAdapter.reset();
				clientsPanel.removeAll();
				panels.clear();
				pack();
			}});
		
		buttonPanel.add(resetButton);

		
		stopButton = new JButton("Stop server");
		stopButton.setEnabled(false);
		stopButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				if(tcpAdapter.isRunning())
					tcpAdapter.stop();
				
				if(udpAdapter.isRunning())
					udpAdapter.stop();
				
				stopButton.setEnabled(false);
				startTCPButton.setEnabled(true);
				startUDPButton.setEnabled(true);
			}
		});
		buttonPanel.add(stopButton);

		startTCPButton = new JButton("Start TCP server");
		startTCPButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				tcpAdapter.start();
				startTCPButton.setEnabled(false);
				startUDPButton.setEnabled(false);
				stopButton.setEnabled(true);
			}
		});
		buttonPanel.add(startTCPButton);
		
		startUDPButton = new JButton("Start UDP server");
		startUDPButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				udpAdapter.start();
				startTCPButton.setEnabled(false);
				startUDPButton.setEnabled(false);
				stopButton.setEnabled(true);
			}
		});
		
		buttonPanel.add(startUDPButton);
		
		
		contentPanel.add(buttonPanel, BorderLayout.SOUTH);
		
		setContentPane(contentPanel);
		pack();
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	@Override
	public void newClientGUIModel(ClientGUIModel newClientGUIModel) {
		ClientPanel clientPanel  = new ClientPanel(newClientGUIModel);
		panels.add(clientPanel);
		clientsPanel.add(clientPanel);
		pack();
	}

	
	
	
	
}
