package core;

import java.util.concurrent.TimeUnit;

public class Time {
	
	private Long hours, minutes, seconds;
	
	public Time(Long seconds) {
		this.hours = TimeUnit.SECONDS.toHours(seconds);
		this.minutes = TimeUnit.SECONDS.toMinutes(seconds) 
				- TimeUnit.HOURS.toMinutes(TimeUnit.SECONDS.toHours(seconds));
		this.seconds = seconds 
				- TimeUnit.MINUTES.toSeconds(TimeUnit.SECONDS.toMinutes(seconds) 
						- TimeUnit.HOURS.toMinutes(TimeUnit.SECONDS.toHours(seconds))) 
				- TimeUnit.HOURS.toSeconds(TimeUnit.SECONDS.toHours(seconds)); 
	}
	
	public Long getHours() {
		return this.hours;	
	}
	
	public Long getMinutes() {
		return this.minutes;
	}
	
	public Long getSeconds() {
		return this.seconds;
	}
}
