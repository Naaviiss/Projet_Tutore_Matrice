package vue;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controleur.Controleur2;
import Modele.*;

/**
 * PanelGauss est une classe qui permet l'affichage du panel concernant
 * l'affichage de la matrice en cours en haut à droite de l'écran mais aussi de
 * l'affichage des instructions possibles à réaliser sur cette matrice.
 */
public class PanelGauss extends JPanel {

	/**
	 * Clé de hachage SHA qui identifie de manière unique PanelGauss
	 **/
	private static final long serialVersionUID = 1L;

	/**
	 * Panel où les matrices sont affichées
	 */
	private PanelAffichageMatrices affichageMatrices;

	/**
	 * Panel où l'utilisateur pourra choisir ses actions à entreprendre sur la
	 * matrice
	 */
	private PanelCommandes commandesMatrices;

	/**
	 * La matrice de l'utilisateur
	 */
	private Matrice chMatrice;

	/**
	 * Constructeur par défaut de la classe PanelGauss
	 * 
	 * @param matrice
	 *            la matrice entrée par l'utilisateur
	 */
	public PanelGauss(Matrice matrice) {
		chMatrice = matrice;
		// gestionnaire et bordure
		this.setLayout(new BorderLayout());
		this.setBorder(new EmptyBorder(100, 100, 100, 100));
		// instance du panel avec les commandes
		commandesMatrices = new PanelCommandes(chMatrice);
		// ajout des éléments au panel
		this.add(commandesMatrices, BorderLayout.EAST);
	}

	/**
	 * Rend le panel où les matrices sont affichées
	 * 
	 * @return le panel où les matrices sont affichées
	 */
	public PanelAffichageMatrices getPanelAffichageMatrices() {
		return affichageMatrices;
	}

	/**
	 * Change le panel où les matrices sont affichées
	 * 
	 * @param affichageMatrices
	 *            le nouveau panel où les matrices sont affichées
	 */
	public void setAffichageMatrices(PanelAffichageMatrices affichageMatrices) {
		this.affichageMatrices = affichageMatrices;
		this.add(affichageMatrices, BorderLayout.WEST);
	}

	/**
	 * Rend le panel où l'utilisateur pourra choisir ses actions à entreprendre sur
	 * la matrice
	 * 
	 * @return le panel où l'utilisateur pourra choisir ses actions à entreprendre
	 *         sur la matrice
	 */
	public PanelCommandes getPanelCommandes() {
		return commandesMatrices;
	}

	/**
	 * Met le panel concernant les actions que l'utilisateur pourra faire à l'écoute
	 * du Controleur
	 * 
	 * @param pControleur
	 */
	public void enregistreEcouteur(Controleur2 pControleur) {
		commandesMatrices.enregistreEcouteur(pControleur);
	}
}