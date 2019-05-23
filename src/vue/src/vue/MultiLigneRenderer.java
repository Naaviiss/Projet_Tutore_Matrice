package vue;

import java.awt.Component;
import java.awt.Font;

import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.TableCellRenderer;

/**
 * MultiLigneRenderer est une classe qui permet de faire du multiligne au sein
 * du renderer.
 */
public class MultiLigneRenderer extends JTextArea implements TableCellRenderer {

	/**
	 * Clé de hachage SHA qui identifie de manière unique MultiLigneRenderer
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Première taille associée au renderer
	 */
	private int tailleUne;

	/**
	 * Deuxième taille associée au renderer
	 */
	private int tailleDeux;

	/**
	 * Constructeur par défaut de la classe MultiLigneRenderer
	 */
	public MultiLigneRenderer() {
		setLineWrap(true);
		setWrapStyleWord(true);
		tailleUne = 20;
		tailleDeux = 12;
	}

	/**
	 * Autre constructeur de la classe MultiLigneRenderer
	 * 
	 * @param pTune
	 *            une première taille associée au renderer
	 * @param pTdeux
	 *            une deuxième taille associée au rendere
	 */
	public MultiLigneRenderer(int pTune, int pTdeux) {
		setLineWrap(true);
		setWrapStyleWord(true);
		tailleUne = pTune;
		tailleDeux = pTdeux;
	}

	/**
	 * Configure une cellule du tableau.
	 * 
	 * @param table
	 *            la table qui demande au renderer de dessiner la case
	 * @param value
	 *            la valeur de la cellule à configurer
	 * @param isSelected
	 *            vrai si la cellule est selectionnée pour être configurée, sinon
	 *            faux.
	 * @param hasFocus
	 *            vrai si le renderer de la cellule est approprié
	 * @param row
	 *            la ligne correspondant à la cellule devant être configurée
	 * @param column
	 *            la colonne correspondant à la cellule devant être configurée
	 * @return le composant utilisé pour dessiner la cellule
	 */
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {

		if (column == 2)
			setFont(new Font(Font.SERIF, Font.BOLD, tailleUne));
		else if (column == 3)
			setFont(new Font(Font.SERIF, 0, tailleUne));
		else
			setFont(new Font(Font.SERIF, 0, tailleDeux));
		if (value != null)
			setText((String) value.toString());
		else
			setText("");
		return this;
	}
}