package core;

import java.sql.ResultSet;
import java.sql.SQLException;

import database.DatabaseHandler;

public class TimeHandler {
	
	private final DatabaseHandler database;
	
	public TimeHandler() {
		this.database = new DatabaseHandler();
	}
	
	public String getTime(Integer projectID) throws SQLException {
		ResultSet timeLogs = this.database.getTimeLogs(projectID);
		Long seconds = 0L;
		while (timeLogs.next()) {
			seconds += (long) timeLogs.getInt("time");
		}
		return Time.convert(seconds);
	}
	
	public void addTime(Integer projectID, Long time) throws SQLException {
		this.database.addTime(projectID, time.intValue());
	}
}
