package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import controller.Controller;

public class StartButtonListener implements ActionListener {

	private final Controller controller;
	
	public StartButtonListener(Controller controller) {
		this.controller = controller;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		this.controller.startTiming();
	}

}