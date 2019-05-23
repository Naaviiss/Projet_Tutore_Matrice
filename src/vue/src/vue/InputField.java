package vue;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 * InputField est une classe qui permet d'afficher un espace n�cessaire pour
 * remplir sa matrice. De base, les champs sont tous � 0 et l'�criture est
 * centr�e.
 */
public class InputField extends JTextField {
	/**
	 * Cl� de hachage SHA qui identifie de mani�re unique InputField
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Contructeur par d�faut de la classe InputField Champs permettant d'entrer des
	 * nombres dans la matrice
	 */
	public InputField() {
		super();
		this.setText("0"); // par d�faut le champ est � 0
		this.setPreferredSize(new Dimension(200, 200)); // peut-�tre change les 200 par un calcul en fonction de la
														// taille de la matrice pour donner un truc �peu pr�s
														// responsive
		this.setFont(new Font(Font.SERIF, 30, 60)); // on change la police
		this.setHorizontalAlignment(SwingConstants.CENTER); // on �crite au centre du champ
	}
}