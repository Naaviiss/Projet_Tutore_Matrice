package vue;

import javax.swing.BorderFactory;
import javax.swing.JLabel;

/**
 * LabelIndications est une classe qui permet d'afficher une indication sur la
 * d�marche � suivre durant les calculs du simplexe.
 */
public class LabelIndications extends JLabel {

	/**
	 * Cl� de hachage SHA qui identifie de mani�re unique LabelIndications
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Construit un objet LabelIndication (qui extends JLabel) permettant
	 * l'affichage d'indices sur les �changes � effectuer pour l'utilisateur
	 * 
	 * @param str une cha�ne correspondant � l'indice
	 * 
	 */
	public LabelIndications(String str) {
		super(str);
		this.setHorizontalAlignment(CENTER);
		this.setBorder(BorderFactory.createLoweredBevelBorder());
	}
}