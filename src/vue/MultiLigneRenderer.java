package vue;

import java.awt.Component;
import java.awt.Font;

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
    	
    	if(column == 2)
    		setFont(new Font(Font.SERIF, Font.BOLD, 20));
    	else if (column == 3)
    		setFont(new Font(Font.SERIF, 0, 20));
    	else {
    		/*if(taille == 3) {
    			setFont(new Font(Font.SERIF, 0, 14));
    		}
    		else if(taille == 4) {
    			setFont(new Font(Font.SERIF, 0, 13));
    		}
    		else {
    			setFont(new Font(Font.SERIF, 0, 12));
    		}*/
    		setFont(new Font(Font.SERIF, 0, 12));
    	}
    		
    	
    	if(value!=null){
    		setText((String)value.toString());
    	}
    	else {
    		setText("");
    	}
    	
    	return this;
	}

}
