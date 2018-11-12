package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import controller.Controller;

	public class NewProjectButtonListener implements ActionListener {

		private final Controller controller;
		
		public NewProjectButtonListener(Controller controller) {
			this.controller = controller;
		}
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			System.out.println("affs");
			String newProject = JOptionPane.showInputDialog("Name of new project:");
			if (!newProject.isEmpty()) {
				this.controller.newProject(newProject);
			}
		}

	}