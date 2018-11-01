package view;

import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainView {

	private JFrame mainFrame;
	private JPanel mainPanel;
	
	public MainView(){
		this.initMainFrame();
		this.initMainPanel();
		this.mainFrame.setVisible(true);
	}
	
	private void initMainFrame(){
		this.mainFrame = new JFrame("Time tracker");
		this.mainFrame.setSize(600,450);
		this.mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.mainFrame.setLocationRelativeTo(null);
	}
	
	private void initMainPanel(){
		this.mainPanel = new JPanel();
		this.mainPanel.setLayout(new GridBagLayout());
	}
	
}
