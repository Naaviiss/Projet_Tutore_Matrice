package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import Controleur.Controleur;
import modele.*;

public class PanelGauss extends JPanel{
	private PanelAffichageMatrices affichageMatrices; //là où les matrices seront affichées
	private PanelCommandes commandesMatrices; //là où l'étudiant pourra choisir ses actions
	private Matrice chMatrice;//matrice de départ
	
	public PanelGauss(/*Matrice pMatrice*/) {
		//chMatrice = pMatrice;
		
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
		Matrice m = new Matrice(3);//à supprimer
		commandesMatrices = new PanelCommandes(m);
//		commandesMatrices.setChMatrice(m);
		
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
