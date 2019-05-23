package vue;

import java.awt.CardLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import Controleur.Controleur2;
import Modele.*;

/**
 * PanelChoix est une classe qui permet de choisir entre le mode didacticiel et
 * le mode autonome.
 *
 */
public class PanelChoix extends JPanel {

	/**
	 * Clé de hachage SHA qui identifie de manière unique PanelChoix
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Panel permettant de choisir la taille de la matrice
	 */
	private PanelTaille panTaille;

	/**
	 * Panel affichant les étapes de la méthode de Gauss et les commandes pouvant
	 * être réalisées
	 */
	private PanelGauss panGauss;

	/**
	 * Panel affichant la matrice
	 */
	private PanelMatrice panMatrice;

	/**
	 * Gestionnaire de l'environnement du panelChoix
	 */
	private CardLayout cardLayout;

	/**
	 * Controleur de la classe PanelCHoix
	 */
	private Controleur2 chControleur;

	/**
	 * Gestionnaire de l'ensemble des contraintes de l'affichage
	 */
	public GridBagConstraints contrainte = new GridBagConstraints();

	/**
	 * Bouton correspondant au mode didacticiel
	 */
	private JButton didacticiel = new JButton("Mode didacticiel");

	/**
	 * Bouton correspond au mode autonome
	 */
	private JButton autonome = new JButton("Mode autonome");

	/**
	 * Constructeur par défaut de la classe PanelChoix Crée un objet de type
	 * PanelChoix
	 */
	public PanelChoix() {
		cardLayout = new CardLayout();
		this.setLayout(cardLayout);
		chControleur = new Controleur2(this);

		JPanel pan = new JPanel();
		pan.setLayout(new GridBagLayout());

		contrainte.fill = GridBagConstraints.BOTH;
		contrainte.insets = new Insets(10, 10, 10, 10);
		contrainte.ipady = contrainte.anchor = GridBagConstraints.CENTER;

		didacticiel.addActionListener(chControleur);
		didacticiel.setActionCommand(Data.VALIDER_DIDACTICIEL);

		autonome.addActionListener(chControleur);
		autonome.setActionCommand(Data.VALIDER_AUTONOME);

		contrainte.gridx = 0;
		contrainte.gridy = 0;
		contrainte.gridheight = 2;
		contrainte.gridwidth = 2;
		pan.add(new JLabel("Veuillez choisir un mode à suivre", SwingConstants.CENTER), contrainte);

		contrainte.gridx = 0;
		contrainte.gridy = 2;
		contrainte.gridheight = 1;
		contrainte.gridwidth = 1;
		pan.add(didacticiel, contrainte);

		contrainte.gridx = 1;
		contrainte.gridy = 2;
		contrainte.gridheight = 1;
		contrainte.gridwidth = 1;
		pan.add(autonome, contrainte);

		this.add(pan);
	}

	/**
	 * Rend un panel permettant de choisir la taille de la matrice
	 * 
	 * @return le panel permettant de choisir la taille de la matrice
	 */
	public PanelTaille getPanTaille() {
		return panTaille;
	}

	/**
	 * Change la taille de la matrice au sein du panelTaille
	 * 
	 * @param panTaille
	 *            la nouvelle taille de la matrice au sein du panelTaille
	 */
	public void setPanTaille(PanelTaille panTaille) {
		this.panTaille = panTaille;
	}

	/**
	 * Rend un panel affichant les étapes de la méthode de Gauss et les commandes
	 * pouvant être réalisées
	 * 
	 * @return le panel affichant les étapes de la méthode de Gauss et les commandes
	 *         pouvant être réalisées
	 */
	public PanelGauss getPanGauss() {
		return panGauss;
	}

	/**
	 * Change le panel affichant les étapes de la méthode de Gauss et les commandes
	 * pouvant être réalisées
	 * 
	 * @param panGauss
	 *            le nouveau panel affichant les étapes de la méthode de Gauss et
	 *            les commandes pouvant être réalisées
	 */
	public void setPanGauss(PanelGauss panGauss) {
		this.panGauss = panGauss;
	}

	/**
	 * Rend le panel affichant la matrice
	 * 
	 * @return le panel affichant la matrice
	 */
	public PanelMatrice getPanMatrice() {
		return panMatrice;
	}

	/**
	 * Change le panel affichant la matrice
	 * 
	 * @param panMatrice
	 *            le nouveau panel affichant la matrice
	 */
	public void setPanMatrice(PanelMatrice panMatrice) {
		this.panMatrice = panMatrice;
	}

	/**
	 * Rend le controleur de la classe PanelCHoix
	 * 
	 * @return le controleur de la classe PanelCHoix
	 */
	public Controleur2 getChControleur() {
		return chControleur;
	}

	/**
	 * Change le controleur de la classe PanelCHoix
	 * 
	 * @param chControleur
	 *            le nouveau controleur de la classe PanelCHoix
	 */
	public void setChControleur(Controleur2 chControleur) {
		this.chControleur = chControleur;
	}

	/**
	 * Rend le gestionnaire de l'environnement du panelChoix
	 * 
	 * @return le gestionnaire de l'environnement du panelChoix
	 */
	public CardLayout getCardLayout1() {
		return cardLayout;
	}

	/**
	 * Change le gestionnaire de l'environnement du panelChoix
	 * 
	 * @param cardLayout
	 *            le nouveau gestionnaire de l'environnement du panelChoix
	 */
	public void setCardLayout1(CardLayout cardLayout) {
		this.cardLayout = cardLayout;
	}

	/**
	 * Change le gestionnaire de l'environnement du panelChoix
	 * 
	 * @return le gestionnaire de l'environnement du panelChoix
	 */
	public CardLayout getCardLayout() {
		return cardLayout;
	}

	/**
	 * Change le gestionnaire de l'environnement du panelChoix
	 * 
	 * @param cardLayout
	 *            le nouveau gestionnaire de l'environnement du panelChoix
	 */
	public void setCardLayout(CardLayout cardLayout) {
		this.cardLayout = cardLayout;
	}
}