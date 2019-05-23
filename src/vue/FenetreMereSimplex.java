package vue;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

/**
 * FenetreMereSimplex est une classe quie permet
 * l'affichage principale de l'application Simplexe.
 */
public class FenetreMereSimplex extends JFrame {

	/**
	 * Panel correspondant à l'affichage principale de la fenêtre
	 */
	PanelGeneral contentPane;
	
	/**
	 * Constructeur par défaut de la classe. Permet la création de l'affichage
	 * d'accueil de l'application Simplexe.
	 */
	public FenetreMereSimplex() {
		super("API Simplexe");
		
		contentPane = new PanelGeneral();
		this.setContentPane(contentPane);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(1000,600);
		this.setVisible(true);
		this.setLocation(100,100);
		
		//Mise en place de la JMenuBar
		JMenuBar menuBar=new JMenuBar();
		this.setJMenuBar(menuBar);
		menuBar.setLayout(new FlowLayout(FlowLayout.LEFT));
		menuBar.setBackground(Color.WHITE);
		
		//JMenu Création
		JMenu menuCreation=new JMenu("Fichier");
		menuCreation.setBackground(Color.LIGHT_GRAY);
		menuCreation.setMnemonic('F');
		menuBar.add(menuCreation);
		
		String[] itemsCreation = {"Nouveau Simplexe", "Charger Simplexe", "Enregistrer", "Enregistrer sous", "Mode Démo"};
		JMenuItem[] menuItemsCreation = new JMenuItem[itemsCreation.length];
		
		// Les items de la JMenuBar sont ajoutés et mis à l'écoute par le contrôleur
		for(int i=0; i<itemsCreation.length;i++) {
			menuItemsCreation[i]=new JMenuItem(itemsCreation[i]);
			menuCreation.add(menuItemsCreation[i]);
			menuItemsCreation[i].setActionCommand(itemsCreation[i]);
			menuItemsCreation[i].addActionListener((ActionListener)contentPane);
			menuItemsCreation[i].setBackground(Color.WHITE);
			if(i==1)
				menuCreation.addSeparator();
			if(itemsCreation[i] == "Enregistrer")
				menuItemsCreation[i].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,KeyEvent.CTRL_DOWN_MASK));
		}
		
		//Tableau de MenuItem et ajout de raccourci
		String[] items = {"Affichage", "Annuler","PDF", "Quitter", "?"};
		
		KeyStroke affi = KeyStroke.getKeyStroke(KeyEvent.VK_A,KeyEvent.CTRL_DOWN_MASK);
		KeyStroke annu = KeyStroke.getKeyStroke(KeyEvent.VK_Z, KeyEvent.CTRL_DOWN_MASK);
		KeyStroke qui = KeyStroke.getKeyStroke(KeyEvent.VK_Q, KeyEvent.CTRL_DOWN_MASK);
		KeyStroke info = KeyStroke.getKeyStroke(KeyEvent.VK_I, KeyEvent.CTRL_DOWN_MASK);
		KeyStroke pdf = KeyStroke.getKeyStroke(KeyEvent.VK_P, KeyEvent.CTRL_DOWN_MASK);
		KeyStroke[] key = {affi,annu,pdf,qui,info};
		
		JMenuItem[] menuItem = new JMenuItem[items.length];
		for(int i = 0; i<items.length;i++){
			menuItem[i] = new JMenuItem(items[i]);
			menuItem[i].setAccelerator(key[i]);
			menuBar.add(menuItem[i]);
			menuItem[i].setActionCommand(items[i]);
			menuItem[i].addActionListener((ActionListener) contentPane);
			menuItem[i].setBackground(Color.WHITE);
		}
	}
}