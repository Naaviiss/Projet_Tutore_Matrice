package vue;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Controleur.Controleur;
import modele.Matrice;

public class ChoixLigneMatrice extends JPanel{
	private Matrice chMatrice;//la matrice à afficher
	private JLabel []lignes;//un tableau de jlabel, chaque case va contenir une ligne du tableau
	
	public ChoixLigneMatrice(Matrice pMatrice) {
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));//gestionnaire pour positionner les labels
		
		//instance des labels
		for (int i = 0; i<chMatrice.getTaille();i++) {
			lignes[i] = new JLabel(chMatrice.toStringLigne(1));
			this.add(lignes[i]); //on ajoute la ligne au panel
		}
	}
	
	public void enregistreEcouteur(Controleur pControleur) {
		//on mets les labels à l'écoute du controleur
		for (int i = 0; i<chMatrice.getTaille();i++) {
			lignes[i].addMouseListener(pControleur);
		}
	}
}
