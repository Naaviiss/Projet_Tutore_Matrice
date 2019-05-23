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
	 * Cl� de hachage SHA qui identifie de mani�re unique MultiLigneRenderer
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Premi�re taille associ�e au renderer
	 */
	private int tailleUne;

	/**
	 * Deuxi�me taille associ�e au renderer
	 */
	private int tailleDeux;

	/**
	 * Constructeur par d�faut de la classe MultiLigneRenderer
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
	 *            une premi�re taille associ�e au renderer
	 * @param pTdeux
	 *            une deuxi�me taille associ�e au rendere
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
	 *            la valeur de la cellule � configurer
	 * @param isSelected
	 *            vrai si la cellule est selectionn�e pour �tre configur�e, sinon
	 *            faux.
	 * @param hasFocus
	 *            vrai si le renderer de la cellule est appropri�
	 * @param row
	 *            la ligne correspondant � la cellule devant �tre configur�e
	 * @param column
	 *            la colonne correspondant � la cellule devant �tre configur�e
	 * @return le composant utilis� pour dessiner la cellule
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