package core;

import java.util.concurrent.TimeUnit;

public class Timer {
	
	private long startTime;
	private Boolean active;

	public Timer() {
		this.startTime = 0L;
		this.active = false;
	}
	
	public void start() {
		if (!this.active) {
			this.startTime = System.currentTimeMillis();
		}
	}
	
	public Long stop() {
		if (this.active) {
			this.active = false;
			return TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis() - this.startTime);
		}
		return TimeUnit.MILLISECONDS.toSeconds(0L);
	}
	
	public Long getTime() {
		if (this.active) {
			return TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis() - this.startTime);
		}
		return TimeUnit.MILLISECONDS.toSeconds(0L);
	}
	
	public Boolean isActive() {
		return this.active;
	}
	
}
