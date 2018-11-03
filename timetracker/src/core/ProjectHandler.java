package core;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.DatabaseHandler;

public class ProjectHandler {
	
	private final DatabaseHandler database;

	public ProjectHandler() {
		this.database = new DatabaseHandler();
	}
	
	public ArrayList<Project> getProjects() throws SQLException {
		ArrayList<Project> projectList = new ArrayList<Project>();
		ResultSet projects;
			projects = this.database.getProjects();
			while (projects.next()) {
				String name = projects.getString("name");
				Integer isActive = projects.getInt("is_active");
				Integer id = projects.getInt("rowid");
				projectList.add(new Project(name, isActive, id));
			}
		return projectList;
	}

	public void newProject(String name, Integer isActive) throws SQLException {
		this.database.addProject(name, isActive);
	}

	
}
