package controller;

import java.util.concurrent.TimeUnit;

import core.Time;
import core.Timer;
import view.MainView;

public class UpdateTimeRunnable implements Runnable {

	private boolean scheduledStop;
	private TimeController controller;
	private Timer timer;
	
	public UpdateTimeRunnable(TimeController controller) {
		this.controller = controller;
		timer = new Timer();
	}
	
	public synchronized long stop() {
		scheduledStop = true;
		Long endTime = timer.stop();
		controller.updateTime(Time.convert(endTime));
		return endTime;
	}
	
	private synchronized boolean scheduledToRun() {
		return scheduledStop == false;
	}
	
	@Override
	public void run() {
		timer.start();
		while(scheduledToRun()) {
			controller.updateTime(Time.convert(timer.getTime()));			
			try {
	        	TimeUnit.SECONDS.sleep(1);
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
		}
	}

}
