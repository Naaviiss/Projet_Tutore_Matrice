package vue;

import java.awt.Component;

import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.TableCellRenderer;

public class MultiLigneRenderer extends JTextArea implements TableCellRenderer {

	private static final long serialVersionUID = 1L;

	public MultiLigneRenderer() {
		setLineWrap(true);
	    setWrapStyleWord(true);
	}
	
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
    	if(value!=null){
    		setText((String)value.toString());
    	}
    	else {
    		setText("");
    	}
    	
    	return this;
	}

}
