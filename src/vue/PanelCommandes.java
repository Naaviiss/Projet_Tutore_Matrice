package vue;

import javax.swing.JButton;
import javax.swing.JLabel;

import modele.Data;

public class PanelCommandes implements Data{
	private JButton valider;
	private JButton[] operations;
	private JLabel expression;
	private JButton constante;
	
	public PanelCommandes() {
		//instance des champs
		valider = new JButton("Valider");
		operations = new JButton[4];
		for (int i = 0; i<Data.OPERATIONS.length;i++) {
			operations[i] = new JButton(Data.OPERATIONS[i]);
		}
		expression = new JLabel();
		constante = new JButton("Constante");
	}
}
