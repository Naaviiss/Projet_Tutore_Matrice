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
	
    @Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
    	
    	if(column == 2)
    		setFont(new Font(Font.SERIF, Font.BOLD, 20));
    	else if (column == 3)
    		setFont(new Font(Font.SERIF, 0, 20));
    	else {
    		/*if(taille == 3) {       les tailles de polices sont déjà bonnes (17, 14 et 12)
    			setFont(new Font(Font.SERIF, 0, 17));
    		}
    		else if(taille == 4) {
    			setFont(new Font(Font.SERIF, 0, 14));
    		}
    		else {
    			setFont(new Font(Font.SERIF, 0, 12));
    		}*/
    		setFont(new Font(Font.SERIF, 0, 12));
    	}
    		
    	
    	if(value!=null){
    		setText(value.toString());
    	}
    	else {
    		setText("");
    	}
    	
    	return this;
	}

}
