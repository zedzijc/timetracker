package view;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import controller.TimeController;

public class MainWindowAdapter extends WindowAdapter{

	private final TimeController controller;
	private final JFrame frame;
	
	public MainWindowAdapter (JFrame frame, TimeController controller){
		this.frame = frame;
		this.controller = controller;
	}
	
	@Override
	public void windowClosing(WindowEvent e) {
		if (controller.isRunning()){
			if (JOptionPane.OK_OPTION == JOptionPane.showConfirmDialog(frame, "Shut down with running timer?", "Confirm shutdown", JOptionPane.YES_NO_OPTION,
		            JOptionPane.QUESTION_MESSAGE)){
				controller.handleShutDown();
				JOptionPane.showMessageDialog(frame, "Successfully saved time! :-)");
				System.exit(0);
			}
			frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		}
		
	}
	
}
