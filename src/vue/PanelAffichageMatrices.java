package vue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.ScrollPane;
import java.util.HashMap;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;

public class PanelAffichageMatrices extends JPanel{

	private JTable tableMatrices; //String pour l'instant
	private HashMap<String, String> chMatrices; //String pour l'instant
	
	public PanelAffichageMatrices(HashMap<String, String> pMatrices) {
		chMatrices = pMatrices;
		tableMatrices = new JTable();
		tableMatrices.setModel(new ModelAffichageMatrices(chMatrices));
		
		//intitules des colonnes
		tableMatrices.getTableHeader().setBackground(new Color(205, 0, 0));
		tableMatrices.getTableHeader().setFont(new Font(Font.SERIF,Font.BOLD,20));
		
		//empecher les redimensionnements et réordonnancements
		tableMatrices.getTableHeader().setResizingAllowed(false);
		tableMatrices.getTableHeader().setReorderingAllowed(false);
		
		//hauteur des lignes
		tableMatrices.setRowHeight(80);
		
		//scrollbar
		tableMatrices.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		JScrollPane panDefil = new JScrollPane(tableMatrices,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		//taille des colonnes et de la table
		tableMatrices.getColumnModel().getColumn(0).setPreferredWidth(340);
		tableMatrices.getColumnModel().getColumn(1).setPreferredWidth(340);
		panDefil.setPreferredSize(new Dimension(700, 850));
		
		//ajout panneau defilant avec la table
		this.add(panDefil);
		
	}
	
}
