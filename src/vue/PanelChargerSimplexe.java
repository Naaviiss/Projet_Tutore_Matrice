package vue;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Controleur.Controleur;

/**
 * PanelChargerSimplexe est une classe qui permet de charger un simplexe d�j�
 * existant au sein de l'application simplexe.
 */
public class PanelChargerSimplexe extends JPanel {
	/**
	 * Bouton permettant de parcourir pour chercher un simplexe d�j� existant
	 */
	private JButton parcourir;

	/**
	 * Construit un objet PanelChargerSimplexe permettant � l'utilisateur de charger
	 * un Simplexe d�j� existant
	 */
	public PanelChargerSimplexe() {
		// GridBagLayout
		this.setLayout(new GridBagLayout());
		GridBagConstraints contrainteEvenement = new GridBagConstraints();
		contrainteEvenement.insets = new Insets(3, 3, 3, 3);

		// Cr�ation d'une etiquette
		JLabel nomDuFichier = new JLabel("Choisissez un fichier :");

		// Bouton et ActionCommand
		parcourir = new JButton("Parcourir");
		parcourir.setActionCommand("Charger");

		// Ajout des �l�ments dans le GridBagLayout
		contrainteEvenement.gridx = 0;
		contrainteEvenement.gridy = 0;
		this.add(nomDuFichier, contrainteEvenement);

		contrainteEvenement.gridx = 0;
		contrainteEvenement.gridy = 1;
		contrainteEvenement.insets = new Insets(20, 3, 3, 3);
		this.add(parcourir, contrainteEvenement);
	}

	/**
	 * Met le controleur en param�tre � l'�coute du bouton Charger
	 * 
	 * @param parControleur
	 *            le controleur
	 */
	public void enregistreEcouteur(Controleur parControleur) {
		parcourir.addActionListener(parControleur);
	}
}
