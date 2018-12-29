package modele;

import javax.swing.JOptionPane;

public class ExceptZeroDivision extends Exception{
	/**
	 * Exception qui va être levée lorsqu'on divise un nombre par zéro
	 */
	private static final long serialVersionUID = 1L;

	public ExceptZeroDivision() {
		JOptionPane.showMessageDialog(null, "Vous ne pouvez pas diviser un entier par 0 !","Erreur",JOptionPane.ERROR_MESSAGE);
	}
}
