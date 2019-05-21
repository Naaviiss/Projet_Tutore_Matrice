package vue;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controleur.Controleur2;
import Modele.Data;

public class PanelAffichage extends JPanel{
	
	/**
	 * Clé de hachage SHA qui identifie de manière unique PanelAffichage
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Création d'un bouton ayant pour nom Simplex
	 */
	private JButton bouton_simplex = new JButton("Simplex");
	
	/**
	 * Création d'un bouton ayant pour nom Matrice
	 */
	private JButton bouton_matrice = new JButton("Matrice");
	
	/**
	 * Création d'un panel pour la gestion des boutons
	 */
	private JPanel panelboutons; // la partie qui concerne la gestion des boutons

	/**
	 * Construit un objet PanelAffichage 
	 */
	public PanelAffichage(){
		panelboutons = new JPanel();	
		this.setBorder(new EmptyBorder(300,300,300,300));

		//On les personnalise
		bouton_simplex.setPreferredSize(new Dimension(350, 250));
		bouton_simplex.setFont(new Font(Font.SERIF, 20, 60));
		bouton_matrice.setPreferredSize(new Dimension(350, 200));
		bouton_matrice.setFont(new Font(Font.SERIF, 20, 60));

		//Le panel sera géré par un BorderLayout
		panelboutons.setLayout(new BorderLayout(20,20));
		
		bouton_simplex.setActionCommand(Data.CHOIX[0]);
		bouton_matrice.setActionCommand(Data.CHOIX[1]);
		
		//On lui ajoute les deux boutons
		panelboutons.add(bouton_simplex,BorderLayout.WEST);
		panelboutons.add(bouton_matrice,BorderLayout.EAST);

		//on ajoute le panel  au PanelAffichage
		this.add(panelboutons);
		
	}//PanelAffichage()
	
	/**
	 * Met les boutons simplex et matrice à l'écoute du Controleur
	 * @param parControleur
	 */
	public void enregistreEcouteur(Controleur2 parControleur) {
		bouton_simplex.addActionListener(parControleur);
		bouton_matrice.addActionListener(parControleur);
	}
}//PanelAffichage
