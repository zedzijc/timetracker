package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import controller.TimeController;

public class StopButtonListener implements ActionListener {

	private final TimeController controller;

	
	public StopButtonListener(TimeController controller) {
		this.controller = controller;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		this.controller.stopTiming();
		
	}

}
