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
	private List<Matrice> chMatricesIdentites;//liste des matrices identit�s
	private List<String> chLigneModif;//pour les calculs effectu�s sous forme de chaine
	private List<String> chCommentaire;//pour les commentaires
	MultiLigneRenderer renderer = new MultiLigneRenderer(); //renderer pour faire du multiligne
	private JScrollPane panDefil;//panel avec la jscrollbar
	private ModelAffichageMatrices modele; 

	
	public PanelAffichageMatrices(List<Matrice> pMatrices,List<Matrice> pMatricesID,List<String> pLigneModif,List<String> pCommentaire) {
		
		chMatrices = pMatrices;
		chMatricesIdentites = pMatricesID;
		chLigneModif = pLigneModif;
		chCommentaire = pCommentaire;
		tableMatrices = new JTable();
		modele  = new ModelAffichageMatrices(chMatrices,chMatricesIdentites,chLigneModif,chCommentaire);
		tableMatrices.setModel(modele);
		//on applique le renderer
		setRenderer(renderer);
		
		//intitules des colonnes
		tableMatrices.getTableHeader().setBackground(new Color(205, 0, 0));
		tableMatrices.getTableHeader().setFont(new Font(Font.SERIF,Font.BOLD,20));
		
		//empecher les redimensionnements et r�ordonnancements
		tableMatrices.getTableHeader().setResizingAllowed(false);
		tableMatrices.getTableHeader().setReorderingAllowed(false);
		
		//hauteur des lignes par d�faut
		tableMatrices.setRowHeight(180);
		
		//taille des colonnes et de la table
		tableMatrices.setModel(modele);
		
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
		modele  = new ModelAffichageMatrices(chMatrices,chMatricesIdentites,chLigneModif,chCommentaire);

		tableMatrices.setModel(modele);
		
		//hauteur des lignes en focntion de la taille de la matrice
		int tailleMatrice = chMatrices.get(0).getTaille();
		tableMatrices.setRowHeight(90*tailleMatrice);
		
		setTailleCol();
		setRenderer(renderer);
	}

	public void viderListe(){
		this.chMatrices.clear();
		this.chMatricesIdentites.clear();
		this.chCommentaire.clear();
		this.chLigneModif.clear();
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

	public ModelAffichageMatrices getModel() {
		return modele;
	}
	
	public static void clearTable(final JTable table) { 
	    for (int i = 1; i < table.getRowCount(); i++){ 
	    	for(int j = 0; j < table.getColumnCount(); j++) {
	    		table.setValueAt("", i, j); 
	    	} 
		}
	table.setValueAt("",0,2);
	}
	
	public static void deleteAllRows(ModelAffichageMatrices model) { 
	    for(int i = model.getRowCount() - 1; i >= 0; i--) { 
	     model.removeRow(i); 
	    } 
	} 
}
