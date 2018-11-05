package controller;

import core.Time;
import view.MainView;

public class Controller {

	private MainView mainView;
	
	public Controller(){
		this.mainView = new MainView();
		this.mainView.setTime(Time.convert(3815L));
	}
}
