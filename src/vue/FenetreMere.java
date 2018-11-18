package vue;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import Controleur.Controleur;
import modele.Data;

import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class FenetreMere extends JFrame{
	PanelAffichage contentPane;
	
	public FenetreMere (String parTitre){
		super(parTitre);
		contentPane = new PanelAffichage();
		contentPane.setLayout(new CardLayout());
		
		//Creation du panel d'affichage de l'interface de choix
		//Les boutons concernant les simplex et les matrices
//		affichage.enregistreEcouteur(this);
		
		//ajout des panels au CardLayout
//		contentPane.add(affichage, "menu_choix");
		//contentPane.add(symplex, "panel_symplex");
//		contentPane.add(gauss, "panel_gauss");
		
		this.add(contentPane);
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
//				menu.addActionListener((ActionListener) contentPane);
				menu.setActionCommand(Data.Titre_Menu[i]);
				menuBar.add(menu);
				//Et on ajoute des items à la suite suivant si on veut
				//de l'aide pour les simplex ou les matrices
				for(int j=0;j<Data.Titre_Menu_Liste.length;j++){
					JMenuItem menuitem = new JMenuItem (Data.Titre_Menu_Liste[j]);
					menuitem.setAccelerator(KeyStroke.getKeyStroke(Data.Titre_Menu_Liste[j].charAt(0),java.awt.Event.CTRL_MASK));
//					menuitem.addActionListener((ActionListener) contentPane);
					menuitem.setActionCommand(Data.Titre_Menu_Liste[j]);
					menu.add(menuitem);
				}
			}
			else{
				//Sinon, on ajoute les autres menus
				JMenuItem menu = new JMenuItem (Data.Titre_Menu[i],Data.Titre_Menu[i].charAt(0));
				menu.setAccelerator(KeyStroke.getKeyStroke(Data.Titre_Menu[i].charAt(0),java.awt.Event.CTRL_MASK));
//				menu.addActionListener((ActionListener) contentPane);
				menu.setActionCommand(Data.Titre_Menu[i]);
				menuBar.add(menu);
			}
		}
		
		//On fait en sorte que cela s'affiche sur l'écran en entier
		JFrame.setDefaultLookAndFeelDecorated(true);
		this.pack();
		this.setExtendedState(Frame.MAXIMIZED_BOTH);

	}
	public static void main (String []args){
		new FenetreMere("Projet Simplex et Matrice");
	}// main()
	
	public void setMenu() {
		((CardLayout) contentPane.getLayout()).show(contentPane,"menu_choix");
	}
	
//	public void actionPerformed(ActionEvent e) {
//		Object source = e.getSource();
//		   if (source == affichage.bouton_matrice)   {
//		          ((CardLayout) contentPane.getLayout()).show(contentPane,"panel_gauss");
//		      }
//		    //if (e.target == affichage.bouton_symplex)   {
//		    //      ((CardLayout) contentPane.getLayout()).show(contentPane,"panel_symplex");
//		    //   }
//		  }//actionPerformed()
}
