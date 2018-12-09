package vue;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controleur.Controleur;
import modele.Data;

public class PanelTaille extends JPanel{
    private JButton valider = new JButton("valider");
    private JComboBox comboTaille;

    private String taille[]= {"3","4","5"};
    private JLabel monlabel;
	
    public PanelTaille() {
		monlabel = new JLabel("Choisir une taille de matrice entre 3 et 5");
		Font font = new Font(Font.SERIF, 20, 25);
		monlabel.setFont(font);
				
		this.setBorder(new EmptyBorder(300,300,300,300));
		valider.setPreferredSize(new Dimension(120, 40));		
		comboTaille = new JComboBox(taille);
		comboTaille.setFont(font);
		valider.setFont(font);
		valider.setActionCommand(Data.VALIDER_PANEL_TAILLE);
			
		//ajout des champs au panel
		this.add(monlabel);
		this.add(comboTaille, "champs_taille");
		this.add(valider, "valider");
	}
		
	public int getTaille() {
			int poids = comboTaille.getSelectedIndex();
			return poids+3;
		}
		
	public void enregistreEcouteur(Controleur parControleur){
		valider.addActionListener(parControleur);
	}

}