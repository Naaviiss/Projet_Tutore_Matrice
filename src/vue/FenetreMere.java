package vue;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import modele.Data;

import java.awt.FlowLayout;
import java.awt.Frame;


public class FenetreMere extends JFrame{
	PanelChoix contentPane;
	
	public FenetreMere (){
		//super(parTitre);
		contentPane = new PanelChoix();
		this.add(contentPane);
		this.setVisible(true);
		
		//test
		//On s'occupe du menu en haut de l'écran
		JMenuBar menuBar = new JMenuBar();
		menuBar.setLayout(new FlowLayout(FlowLayout.LEFT));
		this.setJMenuBar(menuBar);
		for(int i=0;i<Data.TITRE_MATRICE.length;i++){
			if (i==1){
				//Si on est sur le bouton Aide, on crée un menu d'aide
				JMenu menu = new JMenu (Data.TITRE_MATRICE[i]);
				menu.setMnemonic('A');
				menu.addActionListener(contentPane);
				menu.setActionCommand(Data.TITRE_MATRICE[i]);
				menuBar.add(menu);
				//Et on ajoute des items à la suite suivant si on veut
				//de l'aide pour les simplex ou les matrices
				for(int j=0;j<Data.Titre_MATRICE_LISTE.length;j++){
					JMenuItem menuitem = new JMenuItem (Data.Titre_MATRICE_LISTE[j]);
					menuitem.setAccelerator(KeyStroke.getKeyStroke(Data.Titre_MATRICE_LISTE[j].charAt(0),java.awt.Event.CTRL_MASK));
					menuitem.addActionListener(contentPane);
					menuitem.setActionCommand(Data.Titre_MATRICE_LISTE[j]);
					menu.add(menuitem);
				}
			}
			else{
				//Sinon, on ajoute les autres menus
				JMenuItem menu = new JMenuItem (Data.TITRE_MATRICE[i],Data.TITRE_MATRICE[i].charAt(0));
				menu.setAccelerator(KeyStroke.getKeyStroke(Data.TITRE_MATRICE[i].charAt(0),java.awt.Event.CTRL_MASK));
				menu.addActionListener(contentPane);
				menu.setActionCommand(Data.TITRE_MATRICE[i]);
				menuBar.add(menu);
			}
		}
		
		//On fait en sorte que cela s'affiche sur l'écran en entier
		JFrame.setDefaultLookAndFeelDecorated(true);
		this.pack();
		this.setExtendedState(Frame.MAXIMIZED_BOTH);

	}
	public static void main (String []args){
		//new FenetreMere("Projet Simplex et Matrice");
	}// main()
}
