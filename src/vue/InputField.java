package vue;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class InputField extends JTextField{
	private static final long serialVersionUID = 1L;

	public InputField() {
		super();
		this.setText("0"); //par d�faut le champ est � 0
		this.setPreferredSize(new Dimension(200, 200)); //peut-�tre change les 200 par un calcul en fonction de la taille de la matrice pour donner un truc � peu pr�s responsive 
		this.setFont(new Font(Font.SERIF, 30, 60)); //on change la police
		this.setHorizontalAlignment(SwingConstants.CENTER); //on �crite au centre du champ
	} 
}
