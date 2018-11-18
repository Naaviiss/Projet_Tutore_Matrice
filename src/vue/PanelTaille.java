package vue;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Controleur.Controleur;
import modele.Data;

public class PanelTaille extends JPanel{
	JButton valider = new JButton("valider");
	JNumberTextField taille = new JNumberTextField();
	
	public PanelTaille(){
		valider.setPreferredSize(new Dimension(50, 75));
		taille.setPreferredSize(new Dimension(50, 20));
		valider.setFont(new Font(Font.SERIF, 20, 60));
		valider.setActionCommand(Data.VALIDER_PANEL_TAILLE);
		
		//ajout des champs au panel
		this.add(new JLabel("Choisir une taille de matrice entre 3 et 5"));
		this.add(taille, "champs_taille");
		this.add(valider, "valider");
	}
	
	public int getTaille() {
		Integer i = taille.getNumber() != null ? taille.getNumber().intValue() : null;
		return i;
	}
	
	public void enregistreEcouteur(Controleur parControleur){
		valider.addActionListener(parControleur);
	}
	
	
	
	
}