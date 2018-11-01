package vue;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

//import controleur.Controleur;
import modele.Data;

public class PanelAffichage extends JPanel implements ActionListener{
	//On crée nos deux boutons
	private JButton bouton_simplex = new JButton("Simplex");
	private JButton bouton_matrice = new JButton("Matrice");
	
	private JPanel panelboutons; // la partie qui concerne la gestion des boutons

	public PanelAffichage(){
		
		panelboutons = new JPanel();
		//this.setLayout(new BorderLayout());	
		this.setBorder(new EmptyBorder(300,300,300,300));

		//On les personnalise
		bouton_simplex.setPreferredSize(new Dimension(350, 250));
		bouton_simplex.setFont(new Font(Font.SERIF, 20, 60));
		bouton_matrice.setPreferredSize(new Dimension(350, 200));
		bouton_matrice.setFont(new Font(Font.SERIF, 20, 60));

		//Le panel sera géré par un BorderLayout
		panelboutons.setLayout(new BorderLayout(20,20));
		
		bouton_simplex.setActionCommand(Data.Choix[0]);
		bouton_matrice.setActionCommand(Data.Choix[0]);
		
		//On lui ajoute les deux boutons
		panelboutons.add(bouton_simplex,BorderLayout.WEST);
		panelboutons.add(bouton_matrice,BorderLayout.EAST);

		//on ajoute le panel à au PanelAffichage
		this.add(panelboutons);
		
		//Le controleur
		//new Controleur(panelgauss, panelsimplex);
		
	}//PanelAffichage()
	
	public void actionPerformed(ActionEvent evt){
		String actionCommand = evt.getActionCommand();
		
		//L'action concernant le bouton quitter
		if (actionCommand.equals(Data.Titre_Menu[1])){
			System.exit(0);
		}
		
		//L'action concernant l'aide pour les simplex
		if (actionCommand.equals(Data.Titre_Menu_Liste[0])){
			String texte = new String("Texte pour comprendre simplex");
			JOptionPane.showMessageDialog(null, texte, "Aide d'utilisation", JOptionPane.INFORMATION_MESSAGE);
		}
		
		//L'action concernant l'aide pour les matrices
		if (actionCommand.equals(Data.Titre_Menu_Liste[1])){
			String texte = new String("Texte pour comprendre matrice");
			JOptionPane.showMessageDialog(null, texte, "Aide d'utilisation", JOptionPane.INFORMATION_MESSAGE);
		}
	}//actionPerformed()
//	public void enregistreEcouteur(Controleur parControleur){
//		bouton_simplex.addActionListener(parControleur);
//		bouton_matrice.addActionListener(parControleur);

//	}//enregistreEcouteur()
}//PanelAgenda
