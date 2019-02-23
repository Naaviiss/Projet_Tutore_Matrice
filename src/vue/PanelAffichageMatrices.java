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
	public List<Matrice> getChMatricesIdentites() {
		return chMatricesIdentites;
	}

	private JTable tableMatrices; //String pour l'instant
	private List<Matrice> chMatrices; //list avec les matrices
	private List<Matrice> chMatricesIdentites;//liste des matrices identités
	private List<String> chLigneModif;//pour les calculs effectués sous forme de chaine
	private List<String> chCommentaire;//pour les commentaires
	MultiLigneRenderer renderer; //renderer pour faire du multiligne
	private JScrollPane panDefil;//panel avec la jscrollbar
	
	public PanelAffichageMatrices(List<Matrice> pMatrices,List<Matrice> pMatricesID,List<String> pLigneModif,List<String> pCommentaire) {
		
		chMatrices = pMatrices;
		chMatricesIdentites = pMatricesID;
		chLigneModif = pLigneModif;
		chCommentaire = pCommentaire;
		tableMatrices = new JTable();
		tableMatrices.setModel(new ModelAffichageMatrices(chMatrices,chMatricesIdentites,chLigneModif,chCommentaire));
		
		//on applique le renderer
		renderer = new MultiLigneRenderer();
		setRenderer(renderer);
		
		//intitules des colonnes
		tableMatrices.getTableHeader().setBackground(new Color(205, 0, 0));
		tableMatrices.getTableHeader().setFont(new Font(Font.SERIF,Font.BOLD,20));
		
		//empecher les redimensionnements et réordonnancements
		tableMatrices.getTableHeader().setResizingAllowed(false);
		tableMatrices.getTableHeader().setReorderingAllowed(false);
		
		//hauteur des lignes par d�faut
		tableMatrices.setRowHeight(180);
		
		//taille des colonnes et de la table
		tableMatrices.setModel(new ModelAffichageMatrices(chMatrices,chMatricesIdentites,chLigneModif,chCommentaire));
		
		//scrollbar
		panDefil = new JScrollPane(tableMatrices,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		panDefil.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);		
		panDefil.setPreferredSize(new Dimension(900, 850));
		
		//ajout panneau defilant avec la table
		this.add(panDefil);
		tableMatrices.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	}
	
	public JTable getTableMatrices() {
		return tableMatrices;
	}
	
	//cette méthode est utiliée lors d'un ajout d'un calcul à la table
	public void ajoutMatrice(Matrice M1, Matrice M2,String operationChaine,String commentaire) {
		chMatrices.add(M1);//on ajoute la matrice � la liste
		chMatricesIdentites.add(M2);//on ajoute la matrice identit� � la liste
	
		//la chaine correspondant au calcul et le commentaire doivet s'afficher une ligne avant
		if (chLigneModif.size() == 0) {//si aucun calcul n'a �t� effectu�
			chLigneModif.add(chLigneModif.size(),operationChaine);//on ajoute la chaine correspondant au calcul sur la premi�re ligne de la table
			chCommentaire.add(chCommentaire.size(),commentaire);//on ajoute le commentaire sur la premi�re ligne de la table
		}
		else {
			chLigneModif.add(chLigneModif.size()-1,operationChaine);//on ajoute la chaine correspondant au calcul sur la ligne d'avant
			chCommentaire.add(chCommentaire.size()-1,commentaire);//on ajoute le commentaire sur la ligne d'avant
		}
		
		tableMatrices.setModel(new ModelAffichageMatrices(chMatrices,chMatricesIdentites,chLigneModif,chCommentaire));//on raffraichit la table
		
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
		tableMatrices.getColumnModel().getColumn(0).setPreferredWidth(260);
		tableMatrices.getColumnModel().getColumn(1).setPreferredWidth(260);
		tableMatrices.getColumnModel().getColumn(2).setPreferredWidth(180);
		tableMatrices.getColumnModel().getColumn(3).setPreferredWidth(180);
	}
}
