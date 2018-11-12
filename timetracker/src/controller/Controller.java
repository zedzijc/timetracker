package controller;

import java.sql.SQLException;
import java.util.ArrayList;
import core.Project;
import core.ProjectHandler;
import core.Time;
import core.TimeHandler;
import database.DatabaseHandler;
import view.MainView;

public class Controller {

	private MainView mainView;
	private final ProjectHandler projectHandler;
	private final TimeHandler timeHandler;
	private final DatabaseHandler database;
	private ArrayList <Project> projects;
	
	public Controller(){
		database = new DatabaseHandler();
		timeHandler = new TimeHandler(database);
		projectHandler = new ProjectHandler(database, timeHandler);
		loadProjects();
		mainView = new MainView(this, new TimeController(this), projects);
	}

	public void updateTimeInView(String time){
		this.mainView.setTime(time);
	}
	
	private void loadProjects(){
		projects = projectHandler.getProjects();
	}
	
	public void addTime(Long time, int projectID){
		for (Project project : projects) {
			if (project.getID() == projectID) {
				project.addTime(time);
				try {
					timeHandler.addTime(projectID, time);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		loadProjects();
		updateTimeInView(Time.convert(0));
		mainView.update(projects);
	}
	public void newProject(String projectName) {
		try {
			projectHandler.newProject(projectName, true);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		loadProjects();
		mainView.update(projects);
	}
}
