package view;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.Timer;

import controller.Controller;
import core.Project;

public class MainView{

	private JFrame mainFrame;
	private JPanel mainPanel;
	private JLabel timeLabel;
	private Controller controller;
	private JTable table;
	private ArrayList<Project> projects;
	
	public MainView(Controller controller, ArrayList<Project> projects){
		this.initMainFrame();
		this.initMainPanel();
		this.projects = projects;
		this.controller = controller;
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
		startButton.addActionListener(new StartButtonListener(this.controller));
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 0.25;
		gbc.weighty = 0.4;
		gbc.ipadx = this.mainFrame.getWidth()/12;
		gbc.ipady = this.mainFrame.getHeight()/18;
		gbc.anchor = GridBagConstraints.CENTER;
		this.mainPanel.add(startButton, gbc);
		
		JButton stopButton = new JButton("Stop");
		stopButton.addActionListener(new StopButtonListener(this.controller, this));
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.weightx = 0.25;
		gbc.weighty = 0.4;
		gbc.ipadx = this.mainFrame.getWidth()/12;
		gbc.ipady = this.mainFrame.getHeight()/18;
		gbc.anchor = GridBagConstraints.NORTH;
		this.mainPanel.add(stopButton, gbc);
		
		JButton newProjectButton = new JButton("New Project");
		newProjectButton.addActionListener(new NewProjectButtonListener(this.controller));
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.weightx = 0.25;
		gbc.weighty = 0.4;
		gbc.ipadx = this.mainFrame.getWidth()/18;
		gbc.ipady = this.mainFrame.getHeight()/18;
		gbc.anchor = GridBagConstraints.CENTER;
		this.mainPanel.add(newProjectButton, gbc);
		
		this.timeLabel = new JLabel("00:45:15");
		this.timeLabel.setFont(new Font("monospaced", Font.PLAIN, 32));
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.weightx = 0.75;
		gbc.weighty = 0.4;
		gbc.fill = GridBagConstraints.BOTH;
		this.timeLabel.setHorizontalAlignment(JLabel.CENTER);
		this.mainPanel.add(this.timeLabel, gbc);
		
		this.table = new ProjectTable(new ProjectTableModel(rows, cn));
		table.getColumnModel().getColumn(1).setMaxWidth(this.mainFrame.getWidth()/5);
		table.getColumnModel().getColumn(2).setMaxWidth(this.mainFrame.getWidth()/10);
		JScrollPane scrollPane = new JScrollPane(table);
		gbc.gridx = 1;
		gbc.gridy = 2;
		gbc.weightx = 0.75;
		gbc.weighty = 0.6;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = new Insets(0, 0, 20, 20);
		this.mainPanel.add(scrollPane, gbc);
		for(int i = 0; i < projects.size(); i++) {
			Project project = projects.get(i);
			ProjectTableModel tableModel = (ProjectTableModel) table.getModel();
			tableModel.addRow(new Object[]{project.getName(), project.getTime(), project.isActive()});
		}
		this.mainFrame.add(this.mainPanel);
		this.mainPanel.setVisible(true);
	}
	public void setTime(String time){
		this.timeLabel.setText(time);
		this.mainPanel.revalidate();
	}
	
	public int getSelectedProject() {
		return this.projects.get(this.table.getSelectedRow()).getID();
	}
}
