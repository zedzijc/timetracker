package core;

import java.util.concurrent.TimeUnit;

public class Time {
	
	public static String convert(Long seconds) {
		Long hours = TimeUnit.SECONDS.toHours(seconds);
		Long minutes = TimeUnit.SECONDS.toMinutes(seconds) 
				- TimeUnit.HOURS.toMinutes(TimeUnit.SECONDS.toHours(seconds));
		seconds = seconds 
				- TimeUnit.MINUTES.toSeconds(TimeUnit.SECONDS.toMinutes(seconds) 
						- TimeUnit.HOURS.toMinutes(TimeUnit.SECONDS.toHours(seconds))) 
				- TimeUnit.HOURS.toSeconds(TimeUnit.SECONDS.toHours(seconds));
		
		return String.format("%02d", hours) + ":" + String.format("%02d", minutes) + ":" + String.format("%02d", seconds);
	}
}
