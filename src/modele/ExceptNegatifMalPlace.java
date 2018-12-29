package modele;

import javax.swing.JOptionPane;

public class ExceptNegatifMalPlace extends Exception{
	/**
	 * Exception qui va être levée quand le signe "-" est mal placé dans la fraction
	 */
	private static final long serialVersionUID = 1L;

	public ExceptNegatifMalPlace() {
		JOptionPane.showMessageDialog(null, "Erreur dans le placement du signe \"-\" !","Erreur",JOptionPane.ERROR_MESSAGE);
	}
}
