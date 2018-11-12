package controller;

import java.util.concurrent.TimeUnit;

import core.Time;
import core.Timer;
import view.MainView;

public class UpdateTimeRunnable implements Runnable {

	private boolean scheduledStop;
	private MainView mainView;
	private Timer timer;
	
	public UpdateTimeRunnable(MainView mainView) {
		this.mainView = mainView;
		this.timer = new Timer();
	}
	
	public synchronized Long stop() {
		this.scheduledStop = true;
		Long endTime = this.timer.stop();
		this.mainView.setTime(Time.convert(endTime));
		return endTime;
	}
	
	private synchronized boolean scheduledToRun() {
		return this.scheduledStop == false;
	}
	
	@Override
	public void run() {
		this.timer.start();
		while(scheduledToRun()) {
			this.mainView.setTime(Time.convert(this.timer.getTime()));
			System.out.println(this.timer.getTime().toString());
			
			try {
	        	TimeUnit.SECONDS.sleep(1);
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
		}
	}

}
