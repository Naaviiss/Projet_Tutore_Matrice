package vue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.AdjustmentEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;

import modele.*;

public class PanelAffichageMatrices extends JPanel{

	private JTable tableMatrices; //String pour l'instant
	private List<Matrice> chMatrices; //list avec les matrices
	private List<Matrice> chMatricesIdentités;//liste des matrices identités
	private List<String> chLigneModif;//pour les calculs effectués
	private List<String> chCommentaire;//pour les commentaires
	MultiLigneRenderer renderer = new MultiLigneRenderer(); //renderer pour faire du multiligne
	private JScrollPane panDefil;//panel avec la jscrollbar
	
	public PanelAffichageMatrices(List<Matrice> pMatrices,List<Matrice> pMatricesID) {
		
		chMatrices = pMatrices;
		chMatricesIdentités = pMatricesID;
//		chLigneModif = pLigneModif;
//		chCommentaire = pCommentaire;
		tableMatrices = new JTable();
//		tableMatrices.setModel(new ModelAffichageMatrices(chMatrices,chMatricesIdentités,chLigneModif,chCommentaire));
		
		//on applique le renderer
		setRenderer(renderer);
		
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
		panDefil = new JScrollPane(tableMatrices,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		tableMatrices.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		panDefil.setPreferredSize(new Dimension(900, 850));
		
		//ajout panneau defilant avec la table
		this.add(panDefil);
	}
	
	public JTable getTableMatrices() {
		return tableMatrices;
	}

	public void ajoutMatrice(Matrice M1, Matrice M2) {
		System.out.println(chMatrices.size());
		chMatrices.add(M1);
		chMatricesIdentités.add(M2);
		System.out.println(chMatrices.size());
//		tableMatrices.setModel(new ModelAffichageMatrices(chMatrices,chMatricesIdentités,chLigneModif,chCommentaire));
		setRenderer(renderer);
	}

	public List<Matrice> getChMatrices() {
		return chMatrices;
	}

	public void setChMatrices (List<Matrice> chMatrices) {
		this.chMatrices = chMatrices;
	}
	
	public void setTable(){
		//Méthode qui permet d'actualiser
		tableMatrices.setModel(new ModelAffichageMatrices(chMatrices,chMatricesIdentités,chLigneModif,chCommentaire));
	}//setTable()

	public void setRenderer(MultiLigneRenderer renderer) {
		for(int i = 0; i<tableMatrices.getColumnCount();i++) {
			tableMatrices.getColumnModel().getColumn(i).setCellRenderer(renderer);
		}
	}

	//créer la table
	public void genereTable() {
		tableMatrices.setModel(new ModelAffichageMatrices(chMatrices,chMatricesIdentités,chLigneModif,chCommentaire));
		
	}
	
	public void setTableMatrices(JTable tableMatrices) {
		this.tableMatrices = tableMatrices;
	}
	
	
}
