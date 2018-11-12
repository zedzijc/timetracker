package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import controller.Controller;

public class StopButtonListener implements ActionListener {

	private final Controller controller;
	private final MainView mainView;
	
	public StopButtonListener(Controller controller, MainView mainView) {
		this.controller = controller;
		this.mainView = mainView;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		this.controller.stopTiming(this.mainView.getSelectedProject());
		
	}

}
