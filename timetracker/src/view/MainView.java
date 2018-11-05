package view;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class MainView {

	private JFrame mainFrame;
	private JPanel mainPanel;
	private JLabel timeLabel;
	
	public MainView(){
		this.initMainFrame();
		this.initMainPanel();
		this.mainFrame.setVisible(true);
	}
	
	private void initMainFrame(){
		this.mainFrame = new JFrame("Time tracker");
		this.mainFrame.setSize(500,300);
		this.mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.mainFrame.setLocationRelativeTo(null);
	}
	
	private void initMainPanel(){
		this.mainPanel = new JPanel();
		this.mainPanel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		String[] cn = {"Project", "Time", "Active"};
		Object[][] rows = {{"Snow Elf", "10h 15min", false},
				{"Snow Elf", "105h 15min", false},
				{"Snow Elf", "10h 15min", true},
				{"Snow Elf", "10h 15min", true},
				{"Snow Elf", "10h 15min", false},
				{"Snow Elf", "10h 15min", false},
				{"Snow Elf", "10h 15min", false},
				{"Snow Elf", "10h 15min", false},
				{"Snow Elf", "10h 15min", false}};
		
		JButton startButton = new JButton("Start");
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 0.25;
		gbc.weighty = 0.4;
		//Add listeners to when mainframe resizes
		gbc.ipadx = this.mainFrame.getWidth()/12;
		gbc.ipady = this.mainFrame.getHeight()/18;
		gbc.anchor = GridBagConstraints.CENTER;
		//gbc.insets = new Insets(20, 10, 0, 10);
		this.mainPanel.add(startButton, gbc);
		
		JButton stopButton = new JButton("Stop");
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.weightx = 0.25;
		gbc.weighty = 0.4;
		gbc.ipadx = this.mainFrame.getWidth()/12;
		gbc.ipady = this.mainFrame.getHeight()/18;
		gbc.anchor = GridBagConstraints.NORTH;
		//gbc.insets = new Insets(20, 10, 20, 10);
		this.mainPanel.add(stopButton, gbc);
		
		this.timeLabel = new JLabel("00:45:15");
		this.timeLabel.setFont(new Font("monospaced", Font.PLAIN, 32));
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.weightx = 0.75;
		gbc.weighty = 0.4;
		gbc.fill = GridBagConstraints.BOTH;
		this.timeLabel.setHorizontalAlignment(JLabel.CENTER);
		this.mainPanel.add(this.timeLabel, gbc);
		
		JTable table = new ProjectTable(new ProjectTableModel(rows, cn));

		table.getColumnModel().getColumn(1).setMaxWidth(this.mainFrame.getWidth()/5);
		table.getColumnModel().getColumn(2).setMaxWidth(this.mainFrame.getWidth()/10);
		JScrollPane scrollPane = new JScrollPane(table);
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.weightx = 0.75;
		gbc.weighty = 0.6;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = new Insets(0, 0, 20, 20);
		this.mainPanel.add(scrollPane, gbc);
		//this.mainPanel.add(table, gbc);
		this.mainFrame.add(this.mainPanel);
		this.mainPanel.setVisible(true);
	}
	public void setTime(String time){
		this.timeLabel.setText(time);
	}
}
