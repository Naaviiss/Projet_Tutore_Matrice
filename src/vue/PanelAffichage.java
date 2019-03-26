package vue;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controleur.Controleur2;
//import controleur.Controleur;
import modele.Data;

public class PanelAffichage extends JPanel{

	private static final long serialVersionUID = 1L;
	//On cr�e nos deux boutons
	private JButton bouton_simplex = new JButton("Simplex");
	private JButton bouton_matrice = new JButton("Matrice");
	private JPanel panelboutons; // la partie qui concerne la gestion des boutons

	public PanelAffichage(/*Controleur pControleur*/){
		panelboutons = new JPanel();	
		this.setBorder(new EmptyBorder(300,300,300,300));

		//On les personnalise
		bouton_simplex.setPreferredSize(new Dimension(350, 250));
		bouton_simplex.setFont(new Font(Font.SERIF, 20, 60));
		bouton_matrice.setPreferredSize(new Dimension(350, 200));
		bouton_matrice.setFont(new Font(Font.SERIF, 20, 60));

		//Le panel sera g�r� par un BorderLayout
		panelboutons.setLayout(new BorderLayout(20,20));
		
		bouton_simplex.setActionCommand(Data.CHOIX[0]);
		bouton_matrice.setActionCommand(Data.CHOIX[1]);
		
		//On lui ajoute les deux boutons
		panelboutons.add(bouton_simplex,BorderLayout.WEST);
		panelboutons.add(bouton_matrice,BorderLayout.EAST);

		//on ajoute le panel  au PanelAffichage
		this.add(panelboutons);
		
	}//PanelAffichage()
	
	
	public void enregistreEcouteur(Controleur2 parControleur) {
		bouton_simplex.addActionListener(parControleur);
		bouton_matrice.addActionListener(parControleur);
	}
}//PanelAffichage
