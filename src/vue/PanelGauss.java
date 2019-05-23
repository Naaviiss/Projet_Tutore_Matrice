package vue;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controleur.Controleur2;
import Modele.*;

/**
 * PanelGauss est une classe qui permet l'affichage du panel concernant
 * l'affichage de la matrice en cours en haut � droite de l'�cran mais aussi de
 * l'affichage des instructions possibles � r�aliser sur cette matrice.
 */
public class PanelGauss extends JPanel {

	/**
	 * Cl� de hachage SHA qui identifie de mani�re unique PanelGauss
	 **/
	private static final long serialVersionUID = 1L;

	/**
	 * Panel o� les matrices sont affich�es
	 */
	private PanelAffichageMatrices affichageMatrices;

	/**
	 * Panel o� l'utilisateur pourra choisir ses actions � entreprendre sur la
	 * matrice
	 */
	private PanelCommandes commandesMatrices;

	/**
	 * La matrice de l'utilisateur
	 */
	private Matrice chMatrice;

	/**
	 * Constructeur par d�faut de la classe PanelGauss
	 * 
	 * @param matrice
	 *            la matrice entr�e par l'utilisateur
	 */
	public PanelGauss(Matrice matrice) {
		chMatrice = matrice;
		// gestionnaire et bordure
		this.setLayout(new BorderLayout());
		this.setBorder(new EmptyBorder(100, 100, 100, 100));
		// instance du panel avec les commandes
		commandesMatrices = new PanelCommandes(chMatrice);
		// ajout des �l�ments au panel
		this.add(commandesMatrices, BorderLayout.EAST);
	}

	/**
	 * Rend le panel o� les matrices sont affich�es
	 * 
	 * @return le panel o� les matrices sont affich�es
	 */
	public PanelAffichageMatrices getPanelAffichageMatrices() {
		return affichageMatrices;
	}

	/**
	 * Change le panel o� les matrices sont affich�es
	 * 
	 * @param affichageMatrices
	 *            le nouveau panel o� les matrices sont affich�es
	 */
	public void setAffichageMatrices(PanelAffichageMatrices affichageMatrices) {
		this.affichageMatrices = affichageMatrices;
		this.add(affichageMatrices, BorderLayout.WEST);
	}

	/**
	 * Rend le panel o� l'utilisateur pourra choisir ses actions � entreprendre sur
	 * la matrice
	 * 
	 * @return le panel o� l'utilisateur pourra choisir ses actions � entreprendre
	 *         sur la matrice
	 */
	public PanelCommandes getPanelCommandes() {
		return commandesMatrices;
	}

	/**
	 * Met le panel concernant les actions que l'utilisateur pourra faire � l'�coute
	 * du Controleur
	 * 
	 * @param pControleur
	 */
	public void enregistreEcouteur(Controleur2 pControleur) {
		commandesMatrices.enregistreEcouteur(pControleur);
	}
}