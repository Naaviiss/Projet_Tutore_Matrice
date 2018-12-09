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
	
	public PanelGauss(int pTaille, Matrice matrice) {
		chMatrice = matrice;

		//gestionnaire et bordure
		this.setLayout(new BorderLayout());
		this.setBorder(new EmptyBorder(100, 100, 100, 100));
		
		HashMap<Matrice, Matrice> hm = new HashMap<Matrice,Matrice>();
		List<String> hl = new ArrayList<String>();
		List<String> hc = new ArrayList<String>();
		hm.put(new Matrice(3), new Matrice(3));
	    hm.put(new Matrice(3), new Matrice(3));
	    hm.put(new Matrice(3), new Matrice(3));
	    hm.put(new Matrice(3), new Matrice(3));
	    hm.put(new Matrice(3), new Matrice(3));
	    hm.put(new Matrice(3), new Matrice(3));
	    
	    hl.add("c");
	    hl.add("c");
	    hl.add("c");
	    hl.add("c");
	    hl.add("c");
	    hl.add("c");
	    
	    hc.add("c");
	    hc.add("c");
	    hc.add("c");
	    hc.add("c");
	    hc.add("c");
	    hc.add("c");
	    
		
		//instance de la table affichant les matrices
		affichageMatrices = new PanelAffichageMatrices(hm,hl,hc);
		
		//instance du panel avec les commandes
		commandesMatrices = new PanelCommandes(chMatrice);

		
		//ajout des éléments au panel 
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
