package vue;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controleur.Controleur;
import modele.*;

public class PanelGauss extends JPanel{
	private static final long serialVersionUID = 1L;
	private PanelAffichageMatrices affichageMatrices; //l� o� les matrices seront affich�es
	private PanelCommandes commandesMatrices; //l� o� l'�tudiant pourra choisir ses actions
	private Matrice chMatrice;
	
	public PanelGauss(/*int pTaille, */Matrice matrice) {
		chMatrice = matrice;

		//gestionnaire et bordure
		this.setLayout(new BorderLayout());
		this.setBorder(new EmptyBorder(100, 100, 100, 100));
		
		//instance du panel avec les commandes
		commandesMatrices = new PanelCommandes(chMatrice);

		//ajout des �l�ments au panel 
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
	
	public void enregistreEcouteur(Controleur pControleur) {
		commandesMatrices.enregistreEcouteur(pControleur);
	}
	
}
