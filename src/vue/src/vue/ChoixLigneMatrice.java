package vue;

import java.awt.BorderLayout;

import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Controleur.Controleur2;
import Modele.Data;
import Modele.Matrice;

public class ChoixLigneMatrice extends JPanel {

	/**
	 * Clé de hachage SHA qui identifie de manière unique ChoixLigneMatrice
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * La matrice à afficher
	 */
	private Matrice chMatrice;

	/**
	 * Un tableau de label où chaque case va contenir une ligne du tableau
	 */
	private JLabel[] lignes;

	/**
	 * Panel contenant les lignes
	 */
	private JPanel panelGlobal;

	/**
	 * Constructeur par défaut de la classe ChoixLigneMatrice
	 * 
	 * @param pMatrice
	 *            la matrice à afficher
	 */
	public ChoixLigneMatrice(Matrice pMatrice) {
		this.setLayout(new BorderLayout());

		chMatrice = pMatrice;
		lignes = new JLabel[chMatrice.getTaille()];
		panelGlobal = new JPanel();

		panelGlobal.setLayout(new BoxLayout(panelGlobal, BoxLayout.PAGE_AXIS));// gestionnaire pour positionner les
																				// labels
		// instance des labels
		for (int i = 0; i < chMatrice.getTaille(); i++) {
			lignes[i] = new JLabel(chMatrice.toStringLigne(i));
			lignes[i].setFont(new Font(Font.SERIF, 0, 30));
			lignes[i].setName(Data.LIGNES[i]);
			panelGlobal.add(lignes[i]);
		}
		this.add(panelGlobal, BorderLayout.CENTER);
	}

	/**
	 * Se met à l'écoute du controleur
	 * 
	 * @param pControleur
	 */
	public void enregistreEcouteur(Controleur2 pControleur) {
		// on mets les labels à l'écoute du controleur
		for (int i = 0; i < chMatrice.getTaille(); i++) {
			lignes[i].addMouseListener(pControleur);
		}
	}
}