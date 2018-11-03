package core;

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
			return System.currentTimeMillis() - this.startTime;
		}
		return 0L;
	}
	
	public Long getTime() {
		if (this.active) {
			return System.currentTimeMillis() - this.startTime;
		}
		return 0L;
	}
	
	public Boolean isActive() {
		return this.active;
	}
	
}
