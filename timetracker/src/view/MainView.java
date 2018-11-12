package view;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import controller.Controller;
import controller.TimeController;
import core.Project;

public class MainView{

	private JFrame mainFrame;
	private JPanel mainPanel;
	private JLabel timeLabel;
	private Controller controller;
	private TimeController timeController;
	private JTable table;
	private ArrayList<Project> projects;
	
	public MainView(Controller controller, TimeController timeController, ArrayList<Project> projects){
		this.projects = projects;
		this.controller = controller;
		this.timeController = timeController;
		initMainFrame();
		initMainPanel();
		mainFrame.setVisible(true);
		
	}
	
	private void initMainFrame(){
		mainFrame = new JFrame("Time tracker");
		mainFrame.setSize(500,300);
		mainFrame.addWindowListener(new MainWindowAdapter(mainFrame, timeController));
		mainFrame.setLocationRelativeTo(null);
	}
	
	private void initMainPanel(){
		mainPanel = new JPanel();
		mainPanel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		JButton startButton = new JButton("Start");
		startButton.addActionListener(new StartButtonListener(timeController, this));
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 0.25;
		gbc.weighty = 0.4;
		gbc.ipadx = mainFrame.getWidth()/12;
		gbc.ipady = mainFrame.getHeight()/18;
		gbc.anchor = GridBagConstraints.CENTER;
		mainPanel.add(startButton, gbc);
		
		JButton stopButton = new JButton("Stop");
		stopButton.addActionListener(new StopButtonListener(timeController));
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.weightx = 0.25;
		gbc.weighty = 0.4;
		gbc.ipadx = mainFrame.getWidth()/12;
		gbc.ipady = mainFrame.getHeight()/18;
		gbc.anchor = GridBagConstraints.NORTH;
		this.mainPanel.add(stopButton, gbc);
		
		JButton newProjectButton = new JButton("New Project");
		newProjectButton.addActionListener(new NewProjectButtonListener(controller));
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.weightx = 0.25;
		gbc.weighty = 0.4;
		gbc.ipadx = mainFrame.getWidth()/18;
		gbc.ipady = mainFrame.getHeight()/18;
		gbc.anchor = GridBagConstraints.CENTER;
		mainPanel.add(newProjectButton, gbc);
		
		timeLabel = new JLabel("00:00:00");
		timeLabel.setFont(new Font("monospaced", Font.PLAIN, 38));
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.weightx = 0.75;
		gbc.weighty = 0.4;
		gbc.fill = GridBagConstraints.BOTH;
		timeLabel.setHorizontalAlignment(JLabel.CENTER);
		mainPanel.add(this.timeLabel, gbc);
		
		String[] cn = {"Project", "Time"};
		table = new ProjectTable(new ProjectTableModel(null, cn));
		table.getColumnModel().getColumn(1).setMaxWidth(mainFrame.getWidth()/5);
		DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
		cellRenderer.setHorizontalAlignment(JLabel.CENTER);
		table.getColumnModel().getColumn(1).setCellRenderer(cellRenderer);
		//table.getColumnModel().getColumn(2).setMaxWidth(mainFrame.getWidth()/10);
		JScrollPane scrollPane = new JScrollPane(table);
		gbc.gridx = 1;
		gbc.gridy = 2;
		gbc.weightx = 0.75;
		gbc.weighty = 0.6;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = new Insets(0, 0, 20, 20);
		mainPanel.add(scrollPane, gbc);
		updateTable();
		mainFrame.add(mainPanel);
		mainPanel.setVisible(true);
	}
	
	private void updateTable(){
		ProjectTableModel tableModel = (ProjectTableModel) table.getModel();
		tableModel.setRowCount(0);
		for(int i = 0; i < projects.size(); i++) {
			Project project = projects.get(i);
			tableModel.addRow(new Object[]{project.getName(), project.getTimeString()});
		}
	}
	
	public void update(){
		mainPanel.revalidate();
	}
	
	public void update(ArrayList<Project> projects){
		this.projects = projects;
		updateTable();
		update();
	}
	
	public void setTime(String time){
		timeLabel.setText(time);
		update();
	}
	
	public int getSelectedProject() throws ArrayIndexOutOfBoundsException {
			return projects.get(table.getSelectedRow()).getID();
	}
}
