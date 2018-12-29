package modele;

import javax.swing.JOptionPane;

public class ExceptCaseVide extends Exception{
	/**
	 * Exception qui va être levée quand la matrice n'est pas entièrement remplie
	 */
	private static final long serialVersionUID = 1L;

	public ExceptCaseVide() {
		JOptionPane.showMessageDialog(null, "Vous devez remplir toutes les cases de la matrice !","Erreur",JOptionPane.ERROR_MESSAGE);
	}
}
