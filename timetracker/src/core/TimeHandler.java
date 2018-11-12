package core;

import java.sql.ResultSet;
import java.sql.SQLException;

import database.DatabaseHandler;

public class TimeHandler {
	
	private final DatabaseHandler database;
	
	public TimeHandler(DatabaseHandler database) {
		this.database = database;
	}
	
	public Long getTime(Integer projectID) throws SQLException {
		ResultSet timeLogs = database.getTimeLogs(projectID);
		Long seconds = 0L;
		while (timeLogs.next()) {
			seconds += new Long(timeLogs.getInt("time"));
		}
		return seconds;
	}
	
	public void addTime(Integer projectID, Long time) throws SQLException {
		database.addTime(projectID, time.intValue());
	}
}
