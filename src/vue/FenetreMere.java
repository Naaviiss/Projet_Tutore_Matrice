package vue;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

import modele.Data;

import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;


public class FenetreMere extends JFrame{
	private static final long serialVersionUID = 1L;
	PanelChoix contentPane;
	public FenetreMere (){
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
				//Cr�ation menu OUTILS
				//Si on est sur le bouton Aide, on crée un menu d'aide
				JMenu menu = new JMenu (Data.TITRE_MATRICE[i]);
				menu.addActionListener(contentPane);
				menu.setActionCommand(Data.TITRE_MATRICE[i]);
				menuBar.add(menu);
				//Et on ajoute des items à la suite suivant si on veut
				//de l'aide pour les simplex ou les matrices

				//Et on ajoute des items Ã  la suite
				for(int j=0;j<Data.TITRE_MATRICE_LISTE.length;j++){
					JMenuItem menuitem = new JMenuItem (Data.TITRE_MATRICE_LISTE[j]);
					menuitem.addActionListener(contentPane);
					menuitem.setActionCommand(Data.TITRE_MATRICE_LISTE[j]);
					// Commande supplÃ©mentaires
					menuitem.setAccelerator(KeyStroke.getKeyStroke(Data.TITRE_MATRICE_LISTE[j].charAt(0),java.awt.Event.CTRL_MASK));
					menuitem.setMnemonic('A');

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
		
		//On fait en sorte que cela s'affiche sur l'�cran en entier
		JFrame.setDefaultLookAndFeelDecorated(true);
		this.pack();
		this.setExtendedState(Frame.MAXIMIZED_BOTH);
	}
	
}
