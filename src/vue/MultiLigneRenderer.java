package vue;

import java.awt.Component;
import java.awt.ComponentOrientation;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.table.TableCellRenderer;

import modele.Matrice;

public class MultiLigneRenderer extends JTextArea implements TableCellRenderer {
	
	public MultiLigneRenderer() {
		setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		setLineWrap(true);
	    setWrapStyleWord(true);
	}
	
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
    	setText((String)value.toString());
		return this;
	}

}
