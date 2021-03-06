package core;

import java.util.concurrent.TimeUnit;

public class Timer {
	
	private long startTime;
	private Boolean active;

	public Timer() {
		startTime = 0L;
		active = false;
	}
	
	public void start() {
		if (!active) {
			startTime = System.currentTimeMillis();
			active = true;
		}
	}
	
	public long stop() {
		if (active) {
			active = false;
			return TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis() - startTime);
		}
		return TimeUnit.MILLISECONDS.toSeconds(0L);
	}
	
	public long getTime() {
		if (active) {
			return TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis() - startTime);
		}
		return TimeUnit.MILLISECONDS.toSeconds(0L);
	}
	
	public boolean isActive() {
		return active;
	}
	
}
