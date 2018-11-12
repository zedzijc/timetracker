package core;

import java.sql.SQLException;

import database.DatabaseHandler;

public class Project {
	
	private final String name;
	private final DatabaseHandler database;
	private final Integer id;
	private Long time;
	private Integer isActive;

	public Project(String name, Integer isActive, Integer id, Long time, DatabaseHandler database) {
		this.name = name;
		this.isActive = isActive;
		this.id = id;
		this.database = database;
		this.time = time;
	}
	
	public Long getTime() {
		return time;
	}
	
	public void addTime(Long seconds) {
		time += seconds;
	}
	
	public void toggleActive() throws SQLException {
		if (isActive == 1){
			isActive = 0;
		}
		else {
			isActive = 1;
		}
		this.database.updateProjectStatus(id, isActive);
	}
	
	public String getName() {
		return name;
	}
	
	public Integer isActiveInteger() {
		return isActive;
	}
	
	public Boolean isActive() {
		return isActive == 1;
	}
	
	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Project)) {
			return false;
		}
		Project otherProject = (Project) o;
		return this.getID() == otherProject.getID();
	}
	
	public Integer getID() {
		return id;
	}
}
