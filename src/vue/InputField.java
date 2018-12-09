package vue;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.KeyEvent;

import javax.swing.JTextField;

public class InputField extends JTextField{
	public InputField() {
		super();
		this.setText("0"); //par défaut le champ est à 0
		this.setPreferredSize(new Dimension(200, 200)); //peut-être change les 200 par un calcul en fonction de la taille de la matrice pour donner un truc à peu près responsive 
		this.setFont(new Font(Font.SERIF, 30, 60)); //on change la police
		this.setHorizontalAlignment(JTextField.CENTER); //on écrite au centre du champ
	} 
}
