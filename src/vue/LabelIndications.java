package vue;

import javax.swing.BorderFactory;
import javax.swing.JLabel;

public class LabelIndications extends JLabel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Construit un objet LabelIndication (qui extends JLabel) permettant l'affichage d'indices sur les échanges à effectuer pour l'utilisateur
	 * @param String str
	 * 
	 */
	public LabelIndications(String str) {
		super(str);
		this.setHorizontalAlignment(CENTER);
		this.setBorder(BorderFactory.createLoweredBevelBorder());
	}

}