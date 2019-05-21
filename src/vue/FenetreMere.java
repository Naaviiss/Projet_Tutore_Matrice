package vue;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import Modele.Data;

import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.KeyEvent;

/**
 * FenetreMere correspond à l'affichage principale de l'application Matrice.
 * Cette classe permet de choisir entre le mode didactitiel de la méthode du
 * pivot de Gauss ou le mode autonome.
 *
 */
public class FenetreMere extends JFrame {

	/**
	 * Clé de hachage SHA qui identifie de manière unique API
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Panel correspondant à l'affichage principale de la fenêtre
	 */
	PanelChoix contentPane;

	/**
	 * Constructeur par défaut de la classe. Permet la création de l'affichage
	 * d'accueil de l'application Matrice.
	 */
	public FenetreMere() {
		contentPane = new PanelChoix();
		this.add(contentPane);
		this.setVisible(true);

		// On s'occupe du menu en haut de l'écran
		JMenuBar menuBar = new JMenuBar();
		menuBar.setLayout(new FlowLayout(FlowLayout.LEFT));
		this.setJMenuBar(menuBar);

		for (int i = 0; i < Data.TITRE_MATRICE.length; i++) {
			if (i == 1) {
				// Création menu OUTILS
				// Si on est sur le bouton Aide, on crée un menu d'aide
				JMenu menu = new JMenu(Data.TITRE_MATRICE[i]);
				menu.addActionListener(contentPane.getChControleur());
				menu.setActionCommand(Data.TITRE_MATRICE[i]);
				menuBar.add(menu);
				// Et on ajoute des items à la suite suivant si on veut
				// de l'aide pour les simplexes ou les matrices

				// Et on ajoute des items à la suite
				for (int j = 0; j < Data.TITRE_MATRICE_LISTE.length; j++) {
					JMenuItem menuitem = new JMenuItem(Data.TITRE_MATRICE_LISTE[j]);
					menuitem.addActionListener(contentPane.getChControleur());
					menuitem.setActionCommand(Data.TITRE_MATRICE_LISTE[j]);
					switch (j) {
					// Concernant le retour en arrière
					case 0:
						menuitem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, java.awt.Event.CTRL_MASK));
						break;
					// Pour agrandir la taille de l'écran
					case 1:
						menuitem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G, java.awt.Event.CTRL_MASK));
						break;
					// Pour rétrécir la taille de l'écran
					case 2:
						menuitem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_MINUS, java.awt.Event.CTRL_MASK));
						break;
					// Pour revenir au tout premier calcul
					case 3:
						menuitem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, java.awt.Event.CTRL_MASK));
						break;
					// Pour exporter le travail
					case 4:
						menuitem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, java.awt.Event.CTRL_MASK));
						break;
					}
					// On ajoute le tout au menu
					menu.add(menuitem);
				}
			} else {
				// Sinon, on ajoute les autres menus
				JMenuItem menu = new JMenuItem(Data.TITRE_MATRICE[i], Data.TITRE_MATRICE[i].charAt(0));
				menu.setAccelerator(KeyStroke.getKeyStroke(Data.TITRE_MATRICE[i].charAt(0), java.awt.Event.CTRL_MASK));
				menu.addActionListener(contentPane.getChControleur());
				menu.setActionCommand(Data.TITRE_MATRICE[i]);
				menuBar.add(menu);
			}
		}
		// On fait en sorte que cela s'affiche sur l'écran en entier
		JFrame.setDefaultLookAndFeelDecorated(true);
		this.pack();
		this.setExtendedState(Frame.MAXIMIZED_BOTH);
	}
}