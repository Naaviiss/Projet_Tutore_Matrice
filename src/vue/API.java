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

import modele.Data;

public class API extends JFrame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JButton simp = new JButton("Simplexe");
	JButton mat = new JButton("Matrice");
	
	public GridBagConstraints contrainte = new GridBagConstraints() ;
	
	public API() {
		super("Accueil de l'API");
		JPanel pan = new JPanel();
		pan.setLayout(new GridBagLayout());
		
		contrainte.fill = GridBagConstraints.BOTH; contrainte.insets = new Insets(10,10,10,10);
		contrainte.ipady = contrainte.anchor = GridBagConstraints.CENTER;
		
		simp.addActionListener(this);
		simp.setActionCommand("simplexe");
		
		mat.addActionListener(this);
		mat.setActionCommand("matrice");
		
		contrainte.gridx = 0; contrainte.gridy = 0;
		contrainte.gridheight = 2; contrainte.gridwidth = 2;
		pan.add(new JLabel("Bienvenue dans l'API Simplexe-Matrice",JLabel.CENTER),
				contrainte);
		
		contrainte.gridx = 0; contrainte.gridy = 2;
		contrainte.gridheight = 1; contrainte.gridwidth = 1;
		pan.add(simp,contrainte);
		
		contrainte.gridx = 1; contrainte.gridy = 2;
		contrainte.gridheight = 1; contrainte.gridwidth = 1;
		pan.add(mat,contrainte);
		
		this.add(pan);
		
		//On s'occupe du menu en haut de l'�cran
		JMenuBar menuBar = new JMenuBar();
		menuBar.setLayout(new FlowLayout(FlowLayout.LEFT));
		this.setJMenuBar(menuBar);
		for(int i=0;i<Data.TITRE_MENU.length;i++){
			if (i==0){
				//Si on est sur le bouton Aide, on cr�e un menu d'aide
				JMenu menu = new JMenu (Data.TITRE_MENU[i]);
			    menu.setMnemonic('A');
				menu.addActionListener(this);
				menu.setActionCommand(Data.TITRE_MENU[i]);
				menuBar.add(menu);
				//Et on ajoute des items � la suite suivant si on veut
				//de l'aide pour les simplex ou les matrices
				for(int j=0;j<Data.TITRE_MENU_LISTE.length;j++){
					JMenuItem menuitem = new JMenuItem (Data.TITRE_MENU_LISTE[j]);
					menuitem.setAccelerator(KeyStroke.getKeyStroke(Data.TITRE_MENU_LISTE[j].charAt(0),java.awt.Event.CTRL_MASK));
					menuitem.addActionListener(this);
					menuitem.setActionCommand(Data.TITRE_MENU_LISTE[j]);
					menu.add(menuitem);
				}
			}
			else{
				//Sinon, on ajoute les autres menus
				JMenuItem menu = new JMenuItem (Data.TITRE_MENU[i],Data.TITRE_MENU[i].charAt(0));
				menu.setAccelerator(KeyStroke.getKeyStroke(Data.TITRE_MENU[i].charAt(0),java.awt.Event.CTRL_MASK));
				menu.addActionListener(this);
				menu.setActionCommand(Data.TITRE_MENU[i]);
				menuBar.add(menu);
			}
		}
		
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(800,400);
	}

	public static void main(String[] args) {
		new API();

	}
	public void actionPerformed(ActionEvent ev) {
		if (ev.getActionCommand().equals(Data.TITRE_MENU[2])){
			System.exit(0);
		}//L'action concernant le bouton Retour Menu Principal
//		if (actionCommand.equals(Data.Titre_Menu[1])){
//			fenetre.setMenu();
//		}
		//L'action concernant l'aide pour les simplex
		else if (ev.getActionCommand().equals(Data.TITRE_MENU_LISTE[0])){
			String texte = new String("Texte pour comprendre simplex");
			JOptionPane.showMessageDialog(null, texte, "Aide d'utilisation", JOptionPane.INFORMATION_MESSAGE);
		}
		
		//L'action concernant l'aide pour les matrices
		else if (ev.getActionCommand().equals(Data.TITRE_MENU_LISTE[1])){
			String texte = new String("Texte pour comprendre matrice");
			JOptionPane.showMessageDialog(null, texte, "Aide d'utilisation", JOptionPane.INFORMATION_MESSAGE);
		}
		//else if (pEvt.getActionCommand().equals(Data.CHOIX[1])){

		//}
		
		else if(ev.getActionCommand().equals("simplexe")){
			//new FenetreMere();
		}
		else if(ev.getActionCommand().equals("matrice")){
			new FenetreMere();
		}
	}
}