package view;

import javax.swing.JTable;
import javax.swing.table.TableModel;

@SuppressWarnings("serial")
public class ProjectTable extends JTable {
	
	public ProjectTable (TableModel tm){
		super(tm);
	}
	
	public Class getColumnClass(int column) {
        switch (column) {
            case 0:
                return String.class;
            case 1:
                return String.class;
            case 2:
            	return Boolean.class;
            default:
                return Boolean.class;
        }
    }
}
