package vue;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controleur.Controleur2;
import Modele.Data;
import Modele.ExceptCaseVide;
import Modele.ExceptEntreFraction;
import Modele.ExceptNegatifMalPlace;
import Modele.ExceptZeroDivision;
import Modele.Fraction;
import Modele.Matrice;

public class PanelMatrice extends JPanel {

	/**
	 * Clé de hachage SHA qui identifie de manière unique PanelMatrice
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Panel concernant la partie où on entre la matrice
	 */
	private JPanel panelMatrice;

	/**
	 * Panel concernant la partie où se trouve les instructions ainsi que le bouton
	 * valider
	 */
	private JPanel panelInstructions;

	/**
	 * Titre de la partie instruction
	 */
	private JLabel instruction;

	/**
	 * Controleur de la classe PanelMatrice
	 */
	private Controleur2 chControleur;

	/**
	 * Bouton qui correspond au bouton valider
	 */
	private JButton boutonValider = new JButton("Valider");

	/**
	 * La taille de la matrice
	 */
	private int pTailleMatrice;

	/**
	 * Le tableau contenant tous les champs pour remplir la matrice
	 **/
	private InputField[][] champsInput = new InputField[9][9];

	/**
	 * La matrice
	 */
	private Matrice matrice;

	/**
	 * Constructeur par défaut de la classe PanelMatrice
	 * 
	 * @param taille
	 *            la taille de la matrice
	 */
	public PanelMatrice(int taille) {
		pTailleMatrice = taille;
		matrice = new Matrice(pTailleMatrice);
		panelMatrice = new JPanel();
		panelInstructions = new JPanel();
		instruction = new JLabel("Veuillez compléter votre Matrice");

		// ce panel est divisé en 2
		this.setLayout(new BorderLayout());
		this.setBorder(new EmptyBorder(100, 100, 100, 100));

		// le bouton valider
		boutonValider.setPreferredSize(new Dimension(300, 100));
		boutonValider.setFont(new Font(Font.SERIF, 20, 60));

		// on met les boutons à l'écoute
		boutonValider.setActionCommand(Data.VALIDER_PANEL_MATRICE);
		boutonValider.addActionListener(chControleur);

		// prend en paramétre une matrice afin de pouvoir créer le nombre de champs
		// nécessaires pour remplir la matrice
		panelMatrice.setLayout(new GridLayout(pTailleMatrice, pTailleMatrice, 40, 40));

		// on ajoute les champs au panel pour remplir la matrice
		for (int i = 0; i < pTailleMatrice; i++) {
			for (int j = 0; j < pTailleMatrice; j++) {
				champsInput[i][j] = new InputField();
				panelMatrice.add(champsInput[i][j]);
			}
		}
		// le panel instruction sera géré par un border layout
		panelInstructions.setLayout(new BorderLayout(20, 20));
		// on personnalise l'instruction
		instruction.setFont(new Font(Font.SERIF, 20, 30));
		// on lui ajoute le bouton valider et l'instruction
		panelInstructions.add(instruction, BorderLayout.CENTER);
		panelInstructions.add(boutonValider, BorderLayout.SOUTH);
		// on ajoute les panel au panelMatrice
		this.add(panelMatrice, BorderLayout.WEST);
		this.add(panelInstructions, BorderLayout.EAST);
	}

	/**
	 * Met le bouton valider à l'écoute du Controleur
	 * 
	 * @param parControleur
	 */
	public void enregistreEcouteur(Controleur2 parControleur) {
		boutonValider.addActionListener(parControleur);
	}

	/**
	 * Change la taille de la matrice
	 * 
	 * @param taille
	 *            la nouvelle taille de la matrice
	 */
	public void setTaille(int taille) {
		pTailleMatrice = taille;
	}

	/**
	 * Rend la saisie de la matrice
	 * 
	 * @return la matrice saisie dans le panel
	 * @throws ExceptEntreFraction
	 * @throws ExceptZeroDivision
	 * @throws ExceptCaseVide
	 * @throws ExceptNegatifMalPlace
	 */
	public Matrice getMatriceSaisi()
			throws ExceptEntreFraction, ExceptZeroDivision, ExceptCaseVide, ExceptNegatifMalPlace {
		for (int i = 0; i < matrice.getTaille(); i++) {
			for (int j = 0; j < matrice.getTaille(); j++) {
				matrice.setCase(i, j, new Fraction(champsInput[i][j].getText()));
			}
		}
		return matrice;
	}// getMatriceSaisi()
}