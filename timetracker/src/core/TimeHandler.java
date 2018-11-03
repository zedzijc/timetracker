package core;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import database.DatabaseHandler;

public class TimeHandler {
	
	private DatabaseHandler database;
	
	public TimeHandler() {
		this.database = new DatabaseHandler();
	}
	
	public Time getTime(Integer projectID) throws SQLException {
		ResultSet timeLogs = this.database.getTimeLogs(projectID);
		Long seconds = 0L;
		while (timeLogs.next()) {
			seconds += (long) timeLogs.getInt("time");
		}
		return new Time(seconds);
	}
	
	public void addTime(Integer projectID, Long time) throws SQLException {
		this.database.addTime(projectID, time.intValue());
	}
	
}
