package vue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;

import Modele.*;

/**
 * PanelAffichageMatrices est une classe qui permet d'afficher les diff�rentes
 * �tapes de la m�thode du pivot de gauss sous la forme d'une table. Cette table
 * est compos�e de 4 colonnes : une pour la matrice, une pour la matrice
 * identit�e, une pour le calcul effectu� et une pour les commentaires.
 * 
 * Cette table ressemble fortement � la feuille de calcul d'un �l�ve.
 *
 */
public class PanelAffichageMatrices extends JPanel implements AdjustmentListener {

	/**
	 * Cl� de hachage SHA qui identifie de mani�re unique PanelAffichageMatrices
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * La table contenant toutes les informations sur la matrice (matrice inverse,
	 * matrice, ligne, commentaires)
	 */
	private JTable tableMatrices;

	/**
	 * Liste contenant les matrices
	 */
	private List<Matrice> chMatrices;

	/**
	 * Liste contenant les matrices identit�es
	 */
	private List<Matrice> chMatricesIdentites;

	/**
	 * Liste contenant les calculs effectu�s/ les lignes
	 */
	private List<String> chLigneModif;

	/**
	 * Liste contenant les commentaires
	 */
	private List<String> chCommentaire;

	/**
	 * Renderer pour faire du multiligne
	 */
	private MultiLigneRenderer renderer;

	/**
	 * Panel avec la jscrollBar
	 */
	private JScrollPane panDefil;

	/**
	 * Mod�le concernant l'affichage la table
	 */
	private ModelAffichageMatrices modele;

	/**
	 * Constructeur par d�faut de la classe PanelAffichageMatrices
	 * 
	 * @param pMatrices
	 *            la liste contenant les matrices
	 * @param pMatricesID
	 *            la liste contenant les matrices identit�es
	 * @param pLigneModif
	 *            la liste contenant les op�rations
	 * @param pCommentaire
	 *            la liste contenant les commentaires
	 */
	public PanelAffichageMatrices(List<Matrice> pMatrices, List<Matrice> pMatricesID, List<String> pLigneModif,
			List<String> pCommentaire) {
		chMatrices = pMatrices;
		chMatricesIdentites = pMatricesID;
		chLigneModif = pLigneModif;
		chCommentaire = pCommentaire;
		tableMatrices = new JTable();
		modele = new ModelAffichageMatrices(chMatrices, chMatricesIdentites, chLigneModif, chCommentaire);
		tableMatrices.setModel(modele);
		// on applique le renderer
		renderer = new MultiLigneRenderer();
		setRenderer(renderer);

		// intitules des colonnes
		tableMatrices.getTableHeader().setBackground(new Color(205, 0, 0));
		tableMatrices.getTableHeader().setFont(new Font(Font.SERIF, Font.BOLD, 20));

		// empecher les redimensionnements et r�ordonnancements
		tableMatrices.getTableHeader().setResizingAllowed(false);
		tableMatrices.getTableHeader().setReorderingAllowed(false);

		// hauteur des lignes par d�faut
		tableMatrices.setRowHeight(180);

		// taille des colonnes et de la table
		tableMatrices.setModel(modele);

		// scrollbar
		panDefil = new JScrollPane(tableMatrices, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		panDefil.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		panDefil.setPreferredSize(new Dimension(850, 800));

		final JScrollBar scrollBar = panDefil.getHorizontalScrollBar();
		scrollBar.setValue(scrollBar.getValue());
		scrollBar.addAdjustmentListener(this);

		// ajout panneau defilant avec la table
		this.add(panDefil);
		tableMatrices.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	}

	/**
	 * Rend la table contenant toutes les informations sur la matrice (matrice
	 * inverse, matrice, ligne, commentaires)
	 * 
	 * @return la table contenant toutes les informations sur la matrice (matrice
	 *         inverse, matrice, ligne, commentaires)
	 */
	public JTable getTableMatrices() {
		return tableMatrices;
	}

	/**
	 * Permet l'ajout d'un calcul � la table
	 * 
	 * @param M1
	 *            la matrice de l'utilisateur
	 * @param M2
	 *            la matrice identit�e de l'utilisateur
	 * @param operationChaine
	 *            la cha�ne correspond au calcul voulu
	 * @param commentaire
	 *            le commentaire associ�
	 */
	public void ajoutMatrice(Matrice M1, Matrice M2, String operationChaine, String commentaire) {
		chMatrices.add(M1);// on ajoute la matrice � la liste
		chMatricesIdentites.add(M2);// on ajoute la matrice identit� � la liste

		// la chaine correspondant au calcul et le commentaire doivet s'afficher une
		// ligne avant
		if (chLigneModif.size() == 0) {// si aucun calcul n'a �t� effectu�
			chLigneModif.add(chLigneModif.size(), operationChaine);// on ajoute la chaine correspondant au calcul sur la
			// premi�re ligne de la table
			chCommentaire.add(chCommentaire.size(), commentaire);// on ajoute le commentaire sur la premi�re ligne de la
			// table
		} else {
			chLigneModif.add(chLigneModif.size() - 1, operationChaine);// on ajoute la chaine correspondant au calcul
			// sur la ligne d'avant
			chCommentaire.add(chCommentaire.size() - 1, commentaire);// on ajoute le commentaire sur la ligne d'avant
		}
		modele = new ModelAffichageMatrices(chMatrices, chMatricesIdentites, chLigneModif, chCommentaire);

		tableMatrices.setModel(modele);

		// hauteur des lignes en focntion de la taille de la matrice
		int tailleMatrice = chMatrices.get(0).getTaille();
		tableMatrices.setRowHeight(90 * tailleMatrice);

		setTailleCol();
		setRenderer(renderer);
	}

	/**
	 * Rend la liste contenant les calculs effectu�s/ les lignes
	 * 
	 * @return la liste contenant les calculs effectu�s/ les lignes
	 */
	public List<String> getChLigneModif() {
		return chLigneModif;
	}

	/**
	 * Rend la liste contenant les commentaires
	 * 
	 * @return la liste contenant les commentaires
	 */
	public List<String> getChCommentaire() {
		return chCommentaire;
	}

	/**
	 * Met tout ce que poss�de les diff�rentes listes (matrice, matrice identit�,
	 * ligne, commentaires) � vide
	 */
	public void viderListe() {
		this.chMatrices.clear();
		this.chMatricesIdentites.clear();
		this.chCommentaire.clear();
		this.chLigneModif.clear();
	}

	/**
	 * Rend la liste contenant les matrices
	 * 
	 * @return la liste contenant les matrices
	 */
	public List<Matrice> getChMatrices() {
		return chMatrices;
	}

	/**
	 * Change le renderer de la table
	 * 
	 * @param renderer
	 *            le nouveau renderer associ� � la table
	 */
	public void setRenderer(MultiLigneRenderer renderer) {
		for (int i = 0; i < tableMatrices.getColumnCount(); i++) {
			tableMatrices.getColumnModel().getColumn(i).setCellRenderer(renderer);
		}
	}

	/**
	 * Change la table contenant toutes les informations sur la matrice (matrice
	 * inverse, matrice, ligne, commentaires)
	 * 
	 * @param tableMatrices
	 *            la nouvelle table contenant toutes les informations sur la matrice
	 *            (matrice inverse, matrice, ligne, commentaires)
	 */
	public void setTableMatrices(JTable tableMatrices) {
		this.tableMatrices = tableMatrices;
	}

	/**
	 * Associe une taille pour chaque colonne de la table
	 */
	public void setTailleCol() {
		tableMatrices.getColumnModel().getColumn(0).setPreferredWidth(260);
		tableMatrices.getColumnModel().getColumn(1).setPreferredWidth(260);
		tableMatrices.getColumnModel().getColumn(2).setPreferredWidth(180);
		tableMatrices.getColumnModel().getColumn(3).setPreferredWidth(180);
	}

	/**
	 * Rend le mod�le concernant l'affichage la table
	 * 
	 * @return le mod�le concernant l'affichage la table
	 */
	public ModelAffichageMatrices getModel() {
		return modele;
	}

	/**
	 * Efface tout ce que poss�de la table
	 * 
	 * @param table
	 *            la table contenant toutes les informations sur la matrice (matrice
	 *            inverse, matrice, ligne, commentaires)
	 */
	public static void clearTable(final JTable table) {
		for (int i = 1; i < table.getRowCount(); i++) {
			for (int j = 0; j < table.getColumnCount(); j++) {
				table.setValueAt("", i, j);
			}
		}
		table.setValueAt("", 0, 2);
	}

	/**
	 * Efface la derni�re ligne de la table
	 * 
	 * @param table
	 *            la table contenant toutes les informations sur la matrice (matrice
	 *            inverse, matrice, ligne, commentaires)
	 * @param endroit
	 *            l'entier correspondant � la derni�re ligne utilis�e.
	 */
	public static void clearTableAt(final JTable table, int endroit) {
		for (int i = endroit; i < table.getRowCount(); i++) {
			for (int j = 0; j < table.getColumnCount(); j++) {
				table.setValueAt("", i, j);
			}
		}
		// Sur la ligne encore au dessus.
		// Pour le calcul
		table.setValueAt("", endroit - 1, 2);
		// Pour le commentaire
		table.setValueAt("", endroit - 1, 3);
	}

	/**
	 * Rend la liste contenant les matrices identit�es
	 * 
	 * @return la liste contenant les matrices identit�es
	 */
	public List<Matrice> getChMatricesID() {
		return chMatricesIdentites;
	}

	/**
	 * Change la liste contenant les matrices identit�es
	 * 
	 * @param chMatricesIdentites
	 *            la nouvelle liste contenant les matrices identit�es
	 */
	public void setChMatricesIdentites(List<Matrice> chMatricesIdentites) {
		this.chMatricesIdentites = chMatricesIdentites;
	}

	/**
	 * Change la liste contenant les matrices
	 * 
	 * @param chMatrices
	 *            la nouvelle liste contenant les matrices
	 */
	public void setChMatrices(List<Matrice> chMatrices) {
		this.chMatrices = chMatrices;
	}

	/**
	 * Change la liste contenant les calculs effectu�s/ les lignes
	 * 
	 * @param chLigneModif
	 *            la nouvelle liste contenant les calculs effectu�s/ les lignes
	 */
	public void setChLigneModif(List<String> chLigneModif) {
		this.chLigneModif = chLigneModif;
	}

	/**
	 * Supprimer toutes les lignes de la table
	 * 
	 * @param model
	 *            le mod�le concernant l'affichage la table
	 */
	public static void deleteAllRows(ModelAffichageMatrices model) {
		for (int i = model.getRowCount() - 1; i >= 0; i--) {
			model.removeRow(i);
		}
	}

	/**
	 * Change la liste contenant les commentaires
	 * 
	 * @param chCommentaires
	 *            la nouvelle liste contenant les commentaires
	 */
	public void setChCommentaire(List<String> chCommentaires) {
		this.chCommentaire = chCommentaires;

	}

	/**
	 * Rend le renderer pour faire du multiligne
	 * 
	 * @return le renderer pour faire du multiligne
	 */
	public MultiLigneRenderer getRenderer() {
		return renderer;
	}

	@Override
	public void adjustmentValueChanged(AdjustmentEvent arg0) {
		// TODO Auto-generated method stub
	}
}