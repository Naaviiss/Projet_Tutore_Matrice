package Controleur;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JLabel;

import javax.swing.JOptionPane;

import modele.Data;
import modele.Fraction;
import modele.Matrice;
import vue.PanelAffichageMatrices;
import vue.PanelChoix;
import vue.PanelCommandes;

import vue.PanelGauss;
import vue.PanelMatrice;

public class Controleur implements ActionListener,MouseListener{
	
	private PanelMatrice chPanMatrice;
	private PanelChoix chPanelChoix;
	private PanelGauss chPanGauss;
	private PanelAffichageMatrices chPanAffichageMatrices;
	private String[] operation = new String[5]; //tableau correspondant au calcul de l'utilisateur
	
	
	public Controleur(PanelChoix pPanChoix) {
		//on instancie le tableau de string correspondant au calcul de l'utilisateur
		for(int i=0;i<operation.length;i++) {
			operation[i]= ""; //au départ, le tableau est vide
		}
		
		chPanelChoix = pPanChoix;
		List<Matrice> chMatrices = new ArrayList<Matrice>();//list des matrices
		List<Matrice> chMatricesID = new ArrayList<Matrice>();//liste des matrices identités
		chPanAffichageMatrices = new PanelAffichageMatrices(chMatrices, chMatricesID);//on créé le panel affichage
	}
	
	public void actionPerformed(ActionEvent pEvt) {
		if(pEvt.getActionCommand().equals(Data.CHOIX[1])) {//choix du programme matrice
			chPanelChoix.getCardLayout().show(chPanelChoix, "panel_taille");
		}
		
		if(pEvt.getActionCommand().equals(Data.VALIDER_PANEL_TAILLE)) {//choix de la taille de la matrice
			chPanMatrice = new PanelMatrice(chPanelChoix.getPanTaille().getTaille());
			chPanelChoix.add(chPanMatrice, "panel_matrice");
			chPanMatrice.enregistreEcouteur(this);
			chPanelChoix.getCardLayout().show(chPanelChoix, "panel_matrice");
		}
		

		if(pEvt.getActionCommand().equals(Data.VALIDER_PANEL_MATRICE)) {
			Matrice M1 = chPanMatrice.getMatriceSaisi();//création de la matrice
			Matrice M2 = Matrice.identite(M1.getTaille());//création de la matrice identité
			chPanGauss = new PanelGauss(M1);
			chPanAffichageMatrices.ajoutMatrice(M1, M2);
			chPanGauss.setAffichageMatrices(chPanAffichageMatrices);
			chPanelChoix.add(chPanGauss, "panel_gauss");
			chPanGauss.enregistreEcouteur(this);
			chPanelChoix.getCardLayout().show(chPanelChoix, "panel_gauss");
		}
		

		if(pEvt.getActionCommand().equals(Data.VALIDER_PANEL_COMMANDES)) {//on valide son opération
//			chPanelChoix.getPanGauss().getPanelAffichageMatrices().ajoutMatrice(M1,M2);
		}
		
		if(Arrays.asList(Data.OPERATIONS).contains(pEvt.getActionCommand())) { //si la commande de la source est un opérateur
			if(operation[3].equals("")) {
				chPanGauss.getPanelCommandes().getLabel(3).setText(pEvt.getActionCommand());
				operation[3] = pEvt.getActionCommand();	
			}
		}
		
		if(Arrays.asList(Data.FLECHES).contains(pEvt.getActionCommand())) { //si la commande de la source est une flèche
			System.out.print("Je clique sur le bouton "+pEvt.getActionCommand());
			PanelCommandes panCom = chPanGauss.getPanelCommandes();//on recupere le panel commande
			panCom.getLabel(1).setText(pEvt.getActionCommand());
			operation[1]= pEvt.getActionCommand();
			affichageOperation();
		}

		//si on clique sur le bouton effacer, on efface le calcul en cours
		if(pEvt.getActionCommand().equals(Data.EFFACER)) {
			JLabel calcul[] = chPanGauss.getPanelCommandes().getCalcul(); //on récupère l'expression affichée par l'utilisateur
			for (int i=0;i<calcul.length;i++) {
				calcul[i].setText("");
				operation[i] = "";
			}
		}
		
		if(pEvt.getActionCommand().equals(Data.VALIDER_PANEL_COMMANDES)) {
			System.out.print("Je clique sur le bouton valider\n");
			Fraction[][] tab = {{new Fraction(1,2),new Fraction(2,2),new Fraction(3,2)},{new Fraction(4,7),new Fraction(5,5),new Fraction(6,2)},{new Fraction(7,3),new Fraction(8,4),new Fraction(9,16)}};
			Matrice M1 = new Matrice(tab);
			Fraction[][] tab2 = {{new Fraction(1),new Fraction(2),new Fraction(3)},{new Fraction(4),new Fraction(5),new Fraction(6)},{new Fraction(7),new Fraction(8),new Fraction(9)}};
			Matrice M2 = new Matrice(tab2);
			chPanelChoix.getPanGauss().getPanelAffichageMatrices().ajoutMatrice(M1,M2);
			chPanelChoix.getPanGauss().getPanelAffichageMatrices().revalidate();
			chPanelChoix.getPanGauss().getPanelAffichageMatrices().repaint();
			chPanelChoix.getPanGauss().repaint();
			chPanelChoix.repaint();
			
			for(int i = 0; i<operation.length; i++) { //si on valide on reset l'operation
				operation[i]="";
			}
			affichageOperation();
		}
		
		if(pEvt.getActionCommand().equals(Data.CONSTANTE)) { //si la commande de la source est le bouton constante
			if(operation[1] == "<-") {
				//Création du popup de la demande de la constante uniquement avec la flèche <-
				Fraction constante; //constante de l'utilisateur récupérée
				String txt = JOptionPane.showInputDialog(null,"Veuillez rentrer une constante"); //chaine de caractere qu'on va récupérer
				//Si on rentre une valeur pour la constante
				if (txt.equals("") || txt.equals("0")) {
					constante = new Fraction("1");//valeur par défaut à 1
				}
				else {
					constante = new Fraction(txt); //on convertit la chaine en fraction					
				}
				//on ajoute la constante au premier emplacement vide
				int labelVideConstante = chPanGauss.getPanelCommandes().getLabelVideConstante();//on récupère l'indice du premier label disponible pour une constante
				operation[labelVideConstante] = constante.toString();
				if (!constante.toString().equals("1"))
					chPanGauss.getPanelCommandes().getLabel(labelVideConstante).setText(constante.toString());
				affichageOperation();
			}
		}
		
		if(Arrays.asList(Data.OPERATIONS).contains(pEvt.getActionCommand())) { //si la source de la commande est un opérateur
			operation[4] = pEvt.getActionCommand();
		}
	}
	
	//quand on clique sur une ligne
	@Override
	public void mouseClicked(MouseEvent e) {
		PanelCommandes panCom = chPanGauss.getPanelCommandes();
		int labelVide = panCom.getLabelVideLigne();
		panCom.getLabel(labelVide).setText(e.getComponent().getName());
		operation[labelVide] = e.getComponent().getName();
		affichageOperation();
	}

	//pour le over sur une ligne
	@Override
	public void mouseEntered(MouseEvent e) {
		e.getComponent().setForeground(Color.RED);
	}

	//quand on quitte le over
	@Override
	public void mouseExited(MouseEvent e) {
		e.getComponent().setForeground(Color.BLACK);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
	}


	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
}

	private void affichageOperation() { //fonction test de pour la création de l'opération
		
		System.out.print("\nOpération : ");
		for(int i = 0; i<operation.length; i++) {
			System.out.print(operation[i]);
		}
		System.out.println("");
	}
}
