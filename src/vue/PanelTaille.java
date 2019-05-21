package vue;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controleur.Controleur2;
import Modele.Data;

/**
 * PanelTaille est une classe qui permet la gestion de la taille de la matrice.
 * Cette taille ne peut être comprise qu'entre 3 et 5 inclus.
 */
public class PanelTaille extends JPanel {

	/**
	 * Clé de hachage SHA qui identifie de manière unique PanelTaille
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Création d'un bouton ayant pour nom Valider
	 */
	private JButton valider = new JButton("valider");

	/**
	 * Création d'un panneau déroulant pour le choix des tailles
	 */
	private JComboBox<String> comboTaille;

	/**
	 * Création d'un tableau de String ayant pour valeur 3, 4 et 5
	 */
	private String taille[] = { "3", "4", "5" };

	/**
	 * Création d'une étiquette
	 */
	private JLabel monlabel;

	/**
	 * Création d'un PanelTaille permettant de choisir la taille d'une matrice
	 */
	public PanelTaille() {
		monlabel = new JLabel("Choisir une taille de matrice entre 3 et 5");
		Font font = new Font(Font.SERIF, 20, 25);
		monlabel.setFont(font);

		this.setBorder(new EmptyBorder(300, 300, 300, 300));
		valider.setPreferredSize(new Dimension(120, 40));
		comboTaille = new JComboBox<String>(taille);
		comboTaille.setFont(font);
		valider.setFont(font);
		valider.setActionCommand(Data.VALIDER_PANEL_TAILLE);

		// ajout des champs au panel
		this.add(monlabel);
		this.add(comboTaille, "champs_taille");
		this.add(valider, "valider");
	}

	/**
	 * Rend la taille de la matrice selectionnée
	 * 
	 * @return la taille de la matrice selectionnée
	 */
	public int getTaille() {
		int poids = comboTaille.getSelectedIndex();
		return poids + 3;
	}

	/**
	 *	Met le bouton valider à l'écoute du Controleur
	 * 
	 * @param parControleur
	 *            le controleur de l'application Matrice
	 */
	public void enregistreEcouteur(Controleur2 parControleur) {
		valider.addActionListener(parControleur);
	}
}