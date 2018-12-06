package Controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.Arrays;

import javax.swing.JButton;

import modele.Data;
import modele.Fraction;
import modele.Matrice;
import vue.PanelAffichage;
import vue.PanelChoix;
import vue.PanelMatrice;

public class Controleur implements ActionListener{
	
	PanelMatrice chPanMatrice;
	PanelChoix chPanelChoix;
	
	private String[] operation = new String[7]; //tableau contenant l'operation entrer par l'utilisateur
	private int positionOperation; //position de actuel de l'opération
	
	public Controleur(/*PanelMatrice pPanMatrice, */PanelChoix pPanChoix) {
//		chPanMatrice = pPanMatrice;
		chPanelChoix = pPanChoix;
		for(int i = 0; i<7; i++) {
			operation[i]="";
			positionOperation = 0; //position de depart de l'opération
		}
	}

	public void actionPerformed(ActionEvent pEvt) {
		if(pEvt.getActionCommand().equals(Data.CHOIX[1])) {
			chPanelChoix.getCardLayout().show(chPanelChoix, "panel_taille");
		}
		
		if(pEvt.getActionCommand().equals(Data.VALIDER_PANEL_TAILLE)) {
			chPanMatrice = new PanelMatrice(chPanelChoix.getPanTaille().getTaille());
			chPanelChoix.add(chPanMatrice, "panel_matrice");
			chPanMatrice.enregistreEcouteur(this);
			chPanelChoix.getCardLayout().show(chPanelChoix, "panel_matrice");
			chPanMatrice.enregistreEcouteur(this);
		}
		
		if(pEvt.getActionCommand().equals(Data.VALIDER_PANEL_MATRICE)) {
			chPanelChoix.getCardLayout().show(chPanelChoix, "panel_gauss");
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
			
			for(int i = 0; i<7; i++) { //si on valide on reset l'operation
				operation[i]="";
			}
			positionOperation = 0;
			affichageOperation();
		}
		
		if(pEvt.getActionCommand().equals(Data.CONSTANTE)) { //si la commande de la source est le bouton constante
			System.out.print("Je clique sur le bouton constante\n");
			if(positionOperation!=7 && positionOperation!=0) { //si la chaine depasse les 7 actions on ne fait pas l'action ou si la constante est mal placer
				if(positionOperation!=2 || pEvt.getActionCommand()!= "0") { //remplacer pEvt.getActionCommand() par la valeur donnee par l'utilisateur et "0" par un int
					//si la premiere constante est differente de 0
					String valider = "ok";
					for(int i = 0; i<Data.LIGNES.length;i++) {
						if(Data.LIGNES[i] == operation[positionOperation-2]) {
							valider = "non ok";
						}
					}
					if(valider == "ok" || operation[positionOperation-2]!=Data.CONSTANTE) {//est ce que on a 2 lignes a la suite (si oui rien ne se passe)
							if(Data.CONSTANTE == operation[positionOperation-1]) {
								valider = "non ok";
							} //fin du test
						if(valider == "ok") {
							operation[positionOperation]= pEvt.getActionCommand();
							positionOperation+=1;
						}
					}
				}
			}
			//on fera la gestion de la constante une fois qu'elle aura ete ajoutee
			affichageOperation();
			}
		
		if(Arrays.asList(Data.LIGNES).contains(pEvt.getActionCommand())) { //si la commande de la source est une ligne
			System.out.print("Je clique sur le bouton "+pEvt.getActionCommand());
			if(positionOperation!=7) { //si la chaine depasse les 7 actions on ne fait pas l'action
				if(positionOperation == 0) { //est ce que c la premiere action de la chaine
					operation[positionOperation]= pEvt.getActionCommand();
					positionOperation+=1;
					operation[positionOperation]= "<-";
					positionOperation+=1;
				}
				else {
					String valider = "ok";
					for(int i = 0; i<Data.LIGNES.length;i++) {
						if(Data.LIGNES[i] == operation[positionOperation-2]) {
							valider = "non ok";
						}
					}
					if(valider == "ok" || operation[positionOperation-1]!=Data.CONSTANTE) {//est ce que on a 2 lignes a la suite (si oui rien ne se passe)
						valider = "ok";
						for(int i = 0; i<Data.LIGNES.length;i++) {
							if(Data.LIGNES[i] == operation[positionOperation-1]) {
								valider = "non ok";
							}
						} //fin du test
						if(valider == "ok") {
							operation[positionOperation]= pEvt.getActionCommand();
							positionOperation+=1;
						}
					}
				}
			}
			affichageOperation();
		}
		
		if(Arrays.asList(Data.OPERATIONS).contains(pEvt.getActionCommand())) { //si la commande de la source est un opérateur
			System.out.print("Je clique sur le bouton "+pEvt.getActionCommand());
			if(positionOperation!=7 && positionOperation!=0 && positionOperation!=2 && positionOperation!=6) { //si la chaine depasse les 7 actions ou si l'operateur ce trouve en premiere position on ne fait pas l'action 
				//est ce que on a 2 operations a la suite (si oui rien ne se passe)
				String valider = "ok"; 
				for(int i = 0; i<Data.OPERATIONS.length;i++) {
					if(Data.OPERATIONS[i] == operation[positionOperation-1]) {
						valider = "non ok";
					}
				}//fin du test
				if(valider == "ok") {
					operation[positionOperation]= pEvt.getActionCommand();
					positionOperation+=1;
				}
			}
			affichageOperation();
		}
	}

	private void affichageOperation() {
		
		System.out.print("\nOpération : ");
		for(int i = 0; i<7; i++) {
			System.out.print(operation[i]);
		}
		System.out.println("");
	}

}
