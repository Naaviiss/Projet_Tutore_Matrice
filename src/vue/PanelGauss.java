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
	private PanelAffichageMatrices affichageMatrices; //l� o� les matrices seront affich�es
	private PanelCommandes commandesMatrices; //l� o� l'�tudiant pourra choisir ses actions
	private Matrice chMatrice;
	private Matrice chMatriceID;//matrice identit�
	
	public PanelGauss(int pTaille, Matrice matrice) {
		chMatrice = matrice;
		chMatriceID = chMatrice.identite(chMatrice.getTaille());

		//gestionnaire et bordure
		this.setLayout(new BorderLayout());
		this.setBorder(new EmptyBorder(100, 100, 100, 100));
		
//		HashMap<Matrice, Matrice> hm = new HashMap<Matrice,Matrice>();
		List<String> hl = new ArrayList<String>();
		List<String> hc = new ArrayList<String>();
		
		//instance de la table affichant les matrices
		affichageMatrices = new PanelAffichageMatrices(hl,hc);
		
		affichageMatrices.ajoutMatrice(chMatrice, chMatriceID);
		
		//instance du panel avec les commandes
		commandesMatrices = new PanelCommandes(chMatrice);

		
		//ajout des �l�ments au panel 
		this.add(affichageMatrices,BorderLayout.WEST);
		this.add(commandesMatrices,BorderLayout.EAST);
		
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
