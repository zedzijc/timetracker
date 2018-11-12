package core;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.DatabaseHandler;

public class ProjectHandler {
	
	private final DatabaseHandler database;
	private final TimeHandler timeHandler;

	public ProjectHandler(DatabaseHandler database, TimeHandler timeHandler) {
		this.database = database;
		this.timeHandler = timeHandler;
	}
	
	public ArrayList<Project> getProjects(){
		ArrayList<Project> projectList = new ArrayList<Project>();
		ResultSet projects;
			try {
				projects = database.getProjects();
				while (projects.next()) {
					String name = projects.getString("name");
					Integer isActive = projects.getInt("is_active");
					Integer id = projects.getInt("rowid");
					Long time = timeHandler.getTime(id);
					projectList.add(new Project(name, isActive, id, time, database));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return projectList;
	}

	public void newProject(String name, boolean isActive) throws SQLException {
		int active = 0;
		if (isActive) {
			active = 1;
		}
		this.database.addProject(name, active);
	}

	
}
