package vue;

import java.awt.Component;
import java.awt.Font;

import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.TableCellRenderer;

public class MultiLigneRenderer extends JTextArea implements TableCellRenderer {

	private static final long serialVersionUID = 1L;
	private int tailleUne;
	private int tailleDeux;

	public MultiLigneRenderer() {
		setLineWrap(true);
	    setWrapStyleWord(true);
	    tailleUne = 20;
	    tailleDeux = 12;
	    
	}
	public MultiLigneRenderer(int pTune, int pTdeux) {
		setLineWrap(true);
	    setWrapStyleWord(true);
	    tailleUne = pTune;
	    tailleDeux = pTdeux;
	    
	}
	
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
    	
    	if(column == 2)
    		setFont(new Font(Font.SERIF, Font.BOLD, tailleUne));
    	else if (column == 3)
    		setFont(new Font(Font.SERIF, 0, tailleUne));
    	else {
    		setFont(new Font(Font.SERIF, 0, tailleDeux));
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
