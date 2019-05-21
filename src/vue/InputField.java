package vue;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class InputField extends JTextField{
	private static final long serialVersionUID = 1L;

	/**
	 * Champs permettant d'entrer des nombres dans la matrice
	 */
	public InputField() {
		super();
		this.setText("0"); //par défaut le champ est à 0
		this.setPreferredSize(new Dimension(200, 200)); //peut-être change les 200 par un calcul en fonction de la taille de la matrice pour donner un truc à peu près responsive 
		this.setFont(new Font(Font.SERIF, 30, 60)); //on change la police
		this.setHorizontalAlignment(SwingConstants.CENTER); //on écrite au centre du champ
	} 
}
