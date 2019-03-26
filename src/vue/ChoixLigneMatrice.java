package vue;

import java.awt.BorderLayout;

import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Controleur.Controleur2;
import Modele.Data;
import Modele.Matrice;

public class ChoixLigneMatrice extends JPanel{
	private static final long serialVersionUID = 1L;
	private Matrice chMatrice;//la matrice � afficher
	private JLabel []lignes;//un tableau de jlabel, chaque case va contenir une ligne du tableau
	private JPanel panelGlobal;//va contenir les lignes
	
	public ChoixLigneMatrice(Matrice pMatrice) {
		this.setLayout(new BorderLayout());
		
		chMatrice = pMatrice;
		lignes = new JLabel[chMatrice.getTaille()];
		panelGlobal = new JPanel();
		
		panelGlobal.setLayout(new BoxLayout(panelGlobal, BoxLayout.PAGE_AXIS));//gestionnaire pour positionner les labels
		
		//instance des labels
		for (int i = 0; i<chMatrice.getTaille();i++) {
			lignes[i] = new JLabel(chMatrice.toStringLigne(i));
			lignes[i].setFont(new Font(Font.SERIF, 0, 30));
			lignes[i].setName(Data.LIGNES[i]);
			panelGlobal.add(lignes[i]);
		}
		
		this.add(panelGlobal,BorderLayout.CENTER);
	}
	
	public void enregistreEcouteur(Controleur2 pControleur) {
		//on mets les labels à l'écoute du controleur
		for (int i = 0; i<chMatrice.getTaille();i++) {
			lignes[i].addMouseListener(pControleur);
		}
	}

}

