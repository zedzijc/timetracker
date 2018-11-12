package controller;

import java.sql.SQLException;
import java.util.ArrayList;
import core.Project;
import core.ProjectHandler;
import core.TimeHandler;
import database.DatabaseHandler;
import view.MainView;

public class Controller {

	private MainView mainView;
	private final ProjectHandler projectHandler;
	private final TimeHandler timeHandler;
	private final DatabaseHandler database;
	private UpdateTimeRunnable timeRunnable;
	private Thread timeThread;
	private ArrayList <Project> projects;
	
	public Controller(){
		database = new DatabaseHandler();
		timeHandler = new TimeHandler(database);
		projectHandler = new ProjectHandler(database, timeHandler);
		projects = this.projectHandler.getProjects();
		mainView = new MainView(this, projects);
		timeRunnable = new UpdateTimeRunnable(mainView);
		timeThread = new Thread(timeRunnable);

	}
	public void startTiming() {
		if(!timeThread.isAlive()) {
			timeRunnable = new UpdateTimeRunnable(mainView);
			timeThread = new Thread(timeRunnable);
			timeThread.start();
		}
	}
	
	public void stopTiming(Integer projectID) {
		if(timeThread.isAlive()) {
			Long seconds = timeRunnable.stop();
			for (Project project : projects) {
				if (project.getID() == projectID) {
					project.addTime(seconds);
					try {
						timeHandler.addTime(projectID, seconds);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
	}
	
	public void newProject(String projectName) {
		try {
			projectHandler.newProject(projectName, true);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//And refresh the view to show also the new project. 
	}
}
