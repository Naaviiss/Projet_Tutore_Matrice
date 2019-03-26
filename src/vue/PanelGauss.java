package vue;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controleur.Controleur2;
import modele.*;

public class PanelGauss extends JPanel{
	private static final long serialVersionUID = 1L;
	private PanelAffichageMatrices affichageMatrices; //là où les matrices seront affichées
	private PanelCommandes commandesMatrices; //là où l'utilisateur pourra choisir ses actions
	private Matrice chMatrice;
	
	public PanelGauss(/*int pTaille, */Matrice matrice) {
		chMatrice = matrice;

		//gestionnaire et bordure
		this.setLayout(new BorderLayout());
		this.setBorder(new EmptyBorder(100, 100, 100, 100));
		
		//instance du panel avec les commandes
		commandesMatrices = new PanelCommandes(chMatrice);

		//ajout des éléments au panel 
		this.add(commandesMatrices,BorderLayout.EAST);
		
	}
	
	public void setAffichageMatrices(PanelAffichageMatrices affichageMatrices) {
		this.affichageMatrices = affichageMatrices;
		this.add(affichageMatrices,BorderLayout.WEST);
	}

	public PanelCommandes getPanelCommandes() {
		return commandesMatrices;
	}
	
	public PanelAffichageMatrices getPanelAffichageMatrices() {
		return affichageMatrices;
	}
	
	public void enregistreEcouteur(Controleur2 pControleur) {
		commandesMatrices.enregistreEcouteur(pControleur);
	}
	
}
