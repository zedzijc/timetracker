package core;

import java.sql.SQLException;

import database.DatabaseHandler;

public class Project {
	
	private final String name;
	private final DatabaseHandler database;
	private final Integer id;
	private Integer isActive;

	public Project(String name, Integer isActive, Integer id) {
		this.name = name;
		this.isActive = isActive;
		this.id = id;
		this.database = new DatabaseHandler();
	}
	
	public void toggleActive() throws SQLException {
		if (this.isActive == 1){
			this.isActive = 0;
		}
		else {
			this.isActive = 1;
		}
		this.database.updateProjectStatus(this.id, this.isActive);
	}
	
	public String getName() {
		return this.name;
	}
	
	public Integer isActiveInteger() {
		return this.isActive;
	}
	
	public Boolean isActive() {
		return this.isActive == 1;
	}
	
	public Integer getID() {
		return this.id;
	}
}
