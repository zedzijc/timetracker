package view;

import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class ProjectTableModel extends DefaultTableModel {

	public ProjectTableModel(Object[][] data, Object[] columnNames){
		super(data, columnNames);
	}

	public boolean isCellEditable(int row, int column) {
		return column == 2;
	}
}
