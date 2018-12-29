package modele;

import javax.swing.JOptionPane;

public class ExceptEntreFraction extends Exception{
	/**
	 * Exception qui va être levée quand la matrice est remplie avec des lettres ou des caractères spéciaux
	 */
	private static final long serialVersionUID = 1L;

	public ExceptEntreFraction() {
		JOptionPane.showMessageDialog(null, "Vous ne pouvez pas rentrer de lettres et de caractères spéciaux dans une fraction !","Erreur",JOptionPane.ERROR_MESSAGE);
	}
}
