package controller;

public class TimeController {
	
	private final Controller controller;
	private UpdateTimeRunnable timeRunnable;
	private Thread timeThread;
	private int timedProjectID;
	
	public TimeController(Controller controller){
		this.controller = controller;
		timeRunnable = new UpdateTimeRunnable(this);
		timeThread = new Thread(timeRunnable);
	}
	
	private void timeProject(int projectID){
		timedProjectID = projectID;
	}
	
	private int getTimedProjectID(){
		return timedProjectID;
	}
	
	public void handleShutDown(){
		stopTiming();
	}
	
	public boolean isRunning(){
		return timeThread.isAlive();
	}
	
	public void startTiming(int projectID) {
		if(!timeThread.isAlive()) {
			timeProject(projectID);
			timeRunnable = new UpdateTimeRunnable(this);
			timeThread = new Thread(timeRunnable);
			timeThread.start();
		}
	}
	
	public void updateTime(String time){
		controller.updateTimeInView(time);
	}
	public void stopTiming() {
		if(timeThread.isAlive()) {
			Long seconds = timeRunnable.stop();
			controller.addTime(seconds, getTimedProjectID());
		}
	}
}
