package vue;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import modele.Data; 
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionListener;


public class FenetreMere extends JFrame{
	public FenetreMere (String parTitre){
		super(parTitre);
		//On fait en sorte que cela s'affiche sur l'écran en entier
		this.pack();
		JFrame.setDefaultLookAndFeelDecorated(true);
		this.setExtendedState(Frame.MAXIMIZED_BOTH);
		
		//On appelle le PanelAffichage pour gérer
		//Les boutons concernant les simplex et les matrices
		PanelAffichage contentPane = new PanelAffichage();
		this.setContentPane(contentPane);
		setVisible(true);

		//On s'occupe du menu en haut de l'écran
		JMenuBar menuBar = new JMenuBar();
		menuBar.setLayout(new FlowLayout(FlowLayout.LEFT));
		this.setJMenuBar(menuBar);
		for(int i=0;i<Data.Titre_Menu.length;i++){
			if (i==0){
				//Si on est sur le bouton Aide, on crée un menu d'aide
				JMenu menu = new JMenu (Data.Titre_Menu[i]);
			    menu.setMnemonic('A');
				menu.addActionListener((ActionListener) contentPane);
				menu.setActionCommand(Data.Titre_Menu[i]);
				menuBar.add(menu);
				//Et on ajoute des items à la suite suivant si on veut
				//de l'aide pour les simplex ou les matrices
				for(int j=0;j<Data.Titre_Menu_Liste.length;j++){
					JMenuItem menuitem = new JMenuItem (Data.Titre_Menu_Liste[j]);
					menuitem.setAccelerator(KeyStroke.getKeyStroke(Data.Titre_Menu_Liste[j].charAt(0),java.awt.Event.CTRL_MASK));
					menuitem.addActionListener((ActionListener) contentPane);
					menuitem.setActionCommand(Data.Titre_Menu_Liste[j]);
					menu.add(menuitem);
				}
			}
			else{
				//Sinon, on ajoute les autres menus
				JMenuItem menu = new JMenuItem (Data.Titre_Menu[i],Data.Titre_Menu[i].charAt(0));
				menu.setAccelerator(KeyStroke.getKeyStroke(Data.Titre_Menu[i].charAt(0),java.awt.Event.CTRL_MASK));
				menu.addActionListener((ActionListener) contentPane);
				menu.setActionCommand(Data.Titre_Menu[i]);
				menuBar.add(menu);
			}
		}
		

	}//FenetreFrise()
	public static void main (String []args){
		new FenetreMere("Projet Simplex et Matrice");
	}// main()
}//FenetreFrise
