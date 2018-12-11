package vue;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controleur.Controleur;
import modele.*;

public class PanelGauss extends JPanel{
	private PanelAffichageMatrices affichageMatrices; //là où les matrices seront affichées
	private PanelCommandes commandesMatrices; //là où l'étudiant pourra choisir ses actions
	private Matrice chMatrice;
	private Matrice chMatriceID;//matrice identité
	
	public PanelGauss(/*int pTaille, */Matrice matrice) {
		chMatrice = matrice;
		chMatriceID = Matrice.identite(chMatrice.getTaille());

		//gestionnaire et bordure
		this.setLayout(new BorderLayout());
		this.setBorder(new EmptyBorder(100, 100, 100, 100));
		
		//instance de la table affichant les matrices
//		affichageMatrices = new PanelAffichageMatrices(hl,hc);
				
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
	
	public void enregistreEcouteur(Controleur pControleur) {
		commandesMatrices.enregistreEcouteur(pControleur);
	}
	
}
