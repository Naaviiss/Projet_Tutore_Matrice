package vue;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Controleur.Controleur;
import modele.Matrice;

public class ChoixLigneMatrice extends JPanel{
	private Matrice chMatrice;//la matrice à afficher
	private JLabel []lignes;//un tableau de jlabel, chaque case va contenir une ligne du tableau
	private JPanel panelGlobal;//va contenir les lignes
	
	public ChoixLigneMatrice(Matrice pMatrice) {
		this.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension(100, 100));
		
		chMatrice = pMatrice;
		lignes = new JLabel[chMatrice.getTaille()];
		panelGlobal = new JPanel();
		
		panelGlobal.setLayout(new BoxLayout(panelGlobal, BoxLayout.PAGE_AXIS));//gestionnaire pour positionner les labels
		
		//instance des labels
		for (int i = 0; i<chMatrice.getTaille();i++) {
			lignes[i] = new JLabel(chMatrice.toStringLigne(i));
			lignes[i].setFont(new Font(Font.SERIF, 0, 30));
			panelGlobal.add(lignes[i]);
		}
		
		this.add(panelGlobal,BorderLayout.CENTER);
	}
	
	public void enregistreEcouteur(Controleur pControleur) {
		//on mets les labels à l'écoute du controleur
		for (int i = 0; i<chMatrice.getTaille();i++) {
			lignes[i].addMouseListener(pControleur);
		}
	}
}
