package vue;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;

import Modele.Data;

/**
 * API est une classe qui représente l'affichage principale de l'application
 * Elle permet le choix entre deux applications distintes : l'application
 * Simplex et l'application Matrice
 *
 */
public class API extends JFrame implements ActionListener {

	/**
	 * Clé de hachage SHA qui identifie de manière unique API
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Bouton ouvrant l'application Simplexe
	 */
	JButton simp = new JButton("Simplexe");

	/**
	 * Bouton ouvrant l'application Matrice
	 */
	JButton mat = new JButton("Matrice");

	/**
	 * Gestionnaire de l'ensemble des contraintes de l'affichage
	 */
	public GridBagConstraints contrainte = new GridBagConstraints();

	/**
	 * Construit un objet API correspondant à un GridBagLayout qui est l'accueil de
	 * l'application
	 */
	public API() {
		super("Accueil de l'API");
		//Création du panel
		JPanel pan = new JPanel();
		//Association du layout au panel
		pan.setLayout(new GridBagLayout());

		//Création des contraintes
		contrainte.fill = GridBagConstraints.BOTH;
		contrainte.insets = new Insets(10, 10, 10, 10);
		contrainte.ipady = contrainte.anchor = GridBagConstraints.CENTER;

		//On gère les actions des deux boutons
		simp.addActionListener(this);
		simp.setActionCommand("simplexe");
		mat.addActionListener(this);
		mat.setActionCommand("matrice");

		//Mise en place des contraintes sur les différents éléments de l'affichage
		contrainte.gridx = 0;
		contrainte.gridy = 0;
		contrainte.gridheight = 2;
		contrainte.gridwidth = 2;
		pan.add(new JLabel("Bienvenue dans l'API Simplexe-Matrice", SwingConstants.CENTER), contrainte);

		contrainte.gridx = 0;
		contrainte.gridy = 2;
		contrainte.gridheight = 1;
		contrainte.gridwidth = 1;
		pan.add(simp, contrainte);

		contrainte.gridx = 1;
		contrainte.gridy = 2;
		contrainte.gridheight = 1;
		contrainte.gridwidth = 1;
		pan.add(mat, contrainte);

		this.add(pan);

		//MENU EN HAUT A GAUCHE DE L AFFICHAGE
		JMenuBar menuBar = new JMenuBar();
		menuBar.setLayout(new FlowLayout(FlowLayout.LEFT));
		this.setJMenuBar(menuBar);
		for (int i = 0; i < Data.TITRE_MENU.length; i++) {
			// Si on est sur le bouton Aide, on crée un menu d'aide
			if (i == 0) {
				JMenu menu = new JMenu(Data.TITRE_MENU[i]);
				menu.setMnemonic('A');
				menu.addActionListener(this);
				menu.setActionCommand(Data.TITRE_MENU[i]);
				menuBar.add(menu);
				// Et on ajoute des items à la suite suivant si on veut
				// de l'aide pour les simplex ou les matrices
				for (int j = 0; j < Data.TITRE_MENU_LISTE.length; j++) {
					JMenuItem menuitem = new JMenuItem(Data.TITRE_MENU_LISTE[j]);
					menuitem.setAccelerator(
							KeyStroke.getKeyStroke(Data.TITRE_MENU_LISTE[j].charAt(0), java.awt.Event.CTRL_MASK));
					menuitem.addActionListener(this);
					menuitem.setActionCommand(Data.TITRE_MENU_LISTE[j]);
					menu.add(menuitem);
				}
			} 
			// Sinon c'est le cas général, on ajoute le reste au menu
			else {
				JMenuItem menu = new JMenuItem(Data.TITRE_MENU[i], Data.TITRE_MENU[i].charAt(0));
				menu.setAccelerator(KeyStroke.getKeyStroke(Data.TITRE_MENU[i].charAt(0), java.awt.Event.CTRL_MASK));
				menu.addActionListener(this);
				menu.setActionCommand(Data.TITRE_MENU[i]);
				menuBar.add(menu);
			}
		}
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(800, 400);
	}

	public static void main(String[] args) {
		new API();
	}

	/**
	 * L'utilisateur peut, grâce à cette méthode, faire le choix d'utiliser
	 * l'application dédiée aux Matrices ou au Simplexe
	 * 
	 * @param ActionEvent
	 *            ev
	 */
	public void actionPerformed(ActionEvent ev) {
		// L'action concernant le bouton Retour Menu Principal
		if (ev.getActionCommand().equals(Data.TITRE_MENU[1])) {
			System.exit(0);
		} 
		// L'action concernant l'aide pour les simplex
		if (ev.getActionCommand().equals(Data.TITRE_MENU_LISTE[0])) {
			String texte = new String("Pour travailler sur les simplexes, veuillez cliquer sur le bouton 'Simplexe'");
			JOptionPane.showMessageDialog(null, texte, "Aide d'utilisation", JOptionPane.INFORMATION_MESSAGE);
		}
		//L'action concernant le clique sur le bouton simplexe
		if (ev.getActionCommand().equals("simplexe")) {
			new FenetreMereSimplex();
		}
		// L'action concernant l'aide pour les matrices
		if (ev.getActionCommand().equals(Data.TITRE_MENU_LISTE[1])) {
			String texte = new String(
					"Pour travailler sur la méthode du pivot de Gauss, veuillez cliquer sur le bouton 'Matrice'");
			JOptionPane.showMessageDialog(null, texte, "Aide d'utilisation", JOptionPane.INFORMATION_MESSAGE);
		}
		//L'action concernant le clique sur le bouton matrice
		if (ev.getActionCommand().equals("matrice")) {
			new FenetreMere();
		}
	}
}