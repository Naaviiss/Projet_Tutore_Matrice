package vue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;

import modele.*;

public class PanelAffichageMatrices extends JPanel{

	private static final long serialVersionUID = 1L;
	public List<Matrice> getChMatricesIdentités() {
		return chMatricesIdentités;
	}

	private JTable tableMatrices; //String pour l'instant
	private List<Matrice> chMatrices; //list avec les matrices
	private List<Matrice> chMatricesIdentités;//liste des matrices identités
	private List<String> chLigneModif;//pour les calculs effectués sous forme de chaine
	private List<String> chCommentaire;//pour les commentaires
	MultiLigneRenderer renderer = new MultiLigneRenderer(); //renderer pour faire du multiligne
	private JScrollPane panDefil;//panel avec la jscrollbar
	
	public PanelAffichageMatrices(List<Matrice> pMatrices,List<Matrice> pMatricesID,List<String> pLigneModif) {
		
		chMatrices = pMatrices;
		chMatricesIdentités = pMatricesID;
		chLigneModif = pLigneModif;
//		chCommentaire = pCommentaire;
		tableMatrices = new JTable();
		tableMatrices.setModel(new ModelAffichageMatrices(chMatrices,chMatricesIdentités,chLigneModif,chCommentaire));
		
		//on applique le renderer
		setRenderer(renderer);
		
		//intitules des colonnes
		tableMatrices.getTableHeader().setBackground(new Color(205, 0, 0));
		tableMatrices.getTableHeader().setFont(new Font(Font.SERIF,Font.BOLD,20));
		
		//empecher les redimensionnements et réordonnancements
		tableMatrices.getTableHeader().setResizingAllowed(false);
		tableMatrices.getTableHeader().setReorderingAllowed(false);
		
		//hauteur des lignes par défaut
		tableMatrices.setRowHeight(180);
		
		//taille des colonnes et de la table
		tableMatrices.setModel(new ModelAffichageMatrices(chMatrices,chMatricesIdentités,chLigneModif,chCommentaire));
		
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
	
	//cette méthode est utilisée lors d'un ajout d'un calcul à la table
	public void ajoutMatrice(Matrice M1, Matrice M2,String operationChaine) {
		chMatrices.add(M1);//on ajoute la matrice à la liste
		chMatricesIdentités.add(M2);//on ajoute la matrice identité à la liste
	
		//la chaine correspondant au calcul doit s'afficher une ligne avant
		if (chLigneModif.size() == 0)//si aucun calcul n'a été effectué
			chLigneModif.add(chLigneModif.size(),operationChaine);//on ajoute la chaine correspondant au calcul sur la première ligne de la table
		else
			chLigneModif.add(chLigneModif.size()-1,operationChaine);//on ajoute la chaine correspondant au calcul sur la ligne d'avant
		
		tableMatrices.setModel(new ModelAffichageMatrices(chMatrices,chMatricesIdentités,chLigneModif,chCommentaire));//on raffraichit la table
		
		//hauteur des lignes en focntion de la taille de la matrice
		int tailleMatrice = chMatrices.get(0).getTaille();
		tableMatrices.setRowHeight(90*tailleMatrice);
		
		setTailleCol();
		setRenderer(renderer);
	}

	public List<Matrice> getChMatrices() {
		return chMatrices;
	}
	
	public void setRenderer(MultiLigneRenderer renderer) {
		for(int i = 0; i<tableMatrices.getColumnCount();i++) {
			tableMatrices.getColumnModel().getColumn(i).setCellRenderer(renderer);
		}
	}
	
	public void setTableMatrices(JTable tableMatrices) {
		this.tableMatrices = tableMatrices;
	}
	
	public void setTailleCol() {
		tableMatrices.getColumnModel().getColumn(0).setPreferredWidth(240);
		tableMatrices.getColumnModel().getColumn(1).setPreferredWidth(240);
		tableMatrices.getColumnModel().getColumn(2).setPreferredWidth(200);
		tableMatrices.getColumnModel().getColumn(3).setPreferredWidth(200);
	}
}
