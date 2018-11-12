package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import controller.TimeController;

public class StartButtonListener implements ActionListener {

	private final TimeController controller;
	private final MainView mainView;
	
	public StartButtonListener(TimeController controller, MainView mainView) {
		this.controller = controller;
		this.mainView = mainView;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		try {
			this.controller.startTiming(this.mainView.getSelectedProject());
		}
		catch (ArrayIndexOutOfBoundsException e) {
		}
	}

}