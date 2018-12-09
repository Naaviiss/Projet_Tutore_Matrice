package vue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.HashMap;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;

import modele.*;

public class PanelAffichageMatrices extends JPanel{

	private JTable tableMatrices; //String pour l'instant
	private HashMap<Matrice, Matrice> chMatrices; //String pour l'instant
	private List<String> chLigneModif;//pour les calculs effectués
	private List<String> chCommentaire;//pour les commentaires
	
	public PanelAffichageMatrices(HashMap<Matrice, Matrice> pMatrices,List<String> pLigneModif,List<String> pCommentaire) {
		
		chMatrices = pMatrices;
		chLigneModif = pLigneModif;
		chCommentaire = pCommentaire;
		tableMatrices = new JTable();
		tableMatrices.setModel(new ModelAffichageMatrices(chMatrices,chLigneModif,chCommentaire));
		
		MultiLigneRenderer renderer = new MultiLigneRenderer(); //renderer pour faire du multiligne
		tableMatrices.getColumnModel().getColumn(0).setCellRenderer(renderer);
		tableMatrices.getColumnModel().getColumn(1).setCellRenderer(renderer);
		
		//intitules des colonnes
		tableMatrices.getTableHeader().setBackground(new Color(205, 0, 0));
		tableMatrices.getTableHeader().setFont(new Font(Font.SERIF,Font.BOLD,20));
		
		//empecher les redimensionnements et réordonnancements
		tableMatrices.getTableHeader().setResizingAllowed(false);
		tableMatrices.getTableHeader().setReorderingAllowed(false);
		
		//hauteur des lignes
		tableMatrices.setRowHeight(180);
		
		//taille des colonnes et de la table
		tableMatrices.getColumnModel().getColumn(0).setPreferredWidth(240);
		tableMatrices.getColumnModel().getColumn(1).setPreferredWidth(240);
		tableMatrices.getColumnModel().getColumn(2).setPreferredWidth(200);
		tableMatrices.getColumnModel().getColumn(3).setPreferredWidth(200);
		
		//scrollbar
		JScrollPane panDefil = new JScrollPane(tableMatrices,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		tableMatrices.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		panDefil.setPreferredSize(new Dimension(900, 850));
		
		//ajout panneau defilant avec la table
		this.add(panDefil);
	}
	
	public void ajoutMatrice(Matrice M1, Matrice M2) {
		chMatrices.put(M1,M2);
		tableMatrices.repaint();
	}
	
}
