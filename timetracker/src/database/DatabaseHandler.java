package database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class DatabaseHandler {
	
	private final String databaseURL;
	
	public DatabaseHandler(){
		this.databaseURL = "jdbc:sqlite:resources/projects.db";
	}
	
	private PreparedStatement getPreparedStatement(String statement) throws SQLException {
		Connection connection = DriverManager.getConnection(this.databaseURL);
		return connection.prepareStatement(statement);
	}
	 
	public void addProject(String name, Integer isActive) throws SQLException {
		String sqlStatement = "INSERT INTO projects(project_id, name, is_active) VALUES(null,?,?)";
		PreparedStatement statement = this.getPreparedStatement(sqlStatement);
		statement.setString(1, name);
		statement.setInt(2, isActive);
		statement.executeUpdate();
		}
		
	
	public void updateProjectStatus(Integer id, Integer isActive) throws SQLException {
		String sqlStatement = "UPDATE projects SET is_active=(?) WHERE project_id=(?)";
		PreparedStatement statement = this.getPreparedStatement(sqlStatement);
		statement.setInt(1, isActive);
		statement.setInt(2, id);
		statement.executeUpdate();
	}
	
	public void addTime(Integer projectID, Integer time) throws SQLException {
		String sqlStatement = "INSERT INTO timelogs(project_id, log_id, time) VALUES(?,null,?)";
		PreparedStatement statement = this.getPreparedStatement(sqlStatement);
		statement.setInt(1, projectID);
		statement.setInt(2, time);
		statement.executeUpdate();
	}
	
	public ResultSet getProjects() throws SQLException {
		String sqlStatement = "SELECT * from projects";
		PreparedStatement statement = this.getPreparedStatement(sqlStatement);
		return statement.executeQuery();
	}
	
	public ResultSet getTimeLogs() throws SQLException {
		String sqlStatement = "SELECT * from timelogs";
		PreparedStatement statement = this.getPreparedStatement(sqlStatement);
		return statement.executeQuery();
	}
	
	public ResultSet getTimeLogs(Integer projectID) throws SQLException {
		String sqlStatement = "SELECT * from timelogs WHERE project_id=(?)";
		PreparedStatement statement = this.getPreparedStatement(sqlStatement);
		statement.setInt(1, projectID);
		return statement.executeQuery();
	}
}