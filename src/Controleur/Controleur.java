package Controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.Arrays;

import modele.Data;
import modele.Fraction;
import modele.Matrice;
import vue.PanelAffichage;
import vue.PanelChoix;
import vue.PanelMatrice;

public class Controleur implements ActionListener{
	
	PanelMatrice chPanMatrice;
	PanelChoix chPanelChoix;
	
	public Controleur(/*PanelMatrice pPanMatrice, */PanelChoix pPanChoix) {
//		chPanMatrice = pPanMatrice;
		chPanelChoix = pPanChoix;
	
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
			chPanMatrice.enregistreEcouteur(this);
		}
		
		if(pEvt.getActionCommand().equals(Data.VALIDER_PANEL_MATRICE)) {//on entre sa matrice
			chPanelChoix.getCardLayout().show(chPanelChoix, "panel_gauss");
		}
		
		if(pEvt.getActionCommand().equals(Data.VALIDER_PANEL_COMMANDES)) {//on valide son op�ration
			System.out.println("coucou");
//			Fraction[][] tab = {{new Fraction(1,2),new Fraction(2,2),new Fraction(3,2)},{new Fraction(4,7),new Fraction(5,5),new Fraction(6,2)},{new Fraction(7,3),new Fraction(8,4),new Fraction(9,16)}};
//			Matrice M1 = new Matrice(tab);
//			Fraction[][] tab2 = {{new Fraction(1),new Fraction(2),new Fraction(3)},{new Fraction(4),new Fraction(5),new Fraction(6)},{new Fraction(7),new Fraction(8),new Fraction(9)}};
//			Matrice M2 = new Matrice(tab2);
//			chPanelChoix.getPanGauss().getPanelAffichageMatrices().ajoutMatrice(M1,M2);
//			chPanelChoix.getPanGauss().getPanelAffichageMatrices().revalidate();
//			chPanelChoix.getPanGauss().getPanelAffichageMatrices().repaint();
			
		}
		
		if(Arrays.asList(Data.LIGNES).contains(pEvt.getActionCommand())) { //si la commande de la source est une ligne
			System.out.println("Je clique sur une ligne\n");
		}
		
		if(Arrays.asList(Data.OPERATIONS).contains(pEvt.getActionCommand())) { //si la commande de la source est un op�rateur
			System.out.println("Je clique sur un operateur\n");
		}
	}
	
//	public void effectueCalcul(String []tabCalcul) {
//		int ligneA,ligneB;
//		int ligneModifiee;
//		
//		Matrice matricePrincipale = ;//matrice principale
//		Matrice matriceIdentite;//matrice identite
//		
//		ligneModifiee= getNumLigne(tabCalcul[0]);
//		ligneB = getNumLigne(tabCalcul[6]);	//index de la deuxi�me ligne du calcul
//		
//		//Si l'�tudiant veut intervertir 2 lignes
//		if (tabCalcul[1].equals("<->")) {
//			//on effectue le potentiel calcul sur la ligne avec laquelle il souhaite �changer la ligne choisie pr�c�demment
//			//on �change les lignes sur la matrice principale
//			//on �change les lignes sur la matrice identit�
//		}
//		
//		//Si l'�tudiant veut effectuer un calcul sur une ligne
//		else {
//			if (Arrays.asList(Data.LIGNES).contains(tabCalcul[3])) {//si c'est la deuxi�me ligne qui prend un calcul
//				ligneA = getNumLigne(tabCalcul[3]); //index de la premi�re ligne du calcul
//				//on effectue le calcul sur la deuxi�me ligne du calcul
//			}
//			else {
//				ligneA = getNumLigne(tabCalcul[4]); //index de la premi�re ligne du calcul
//				//on effectue le calcul sur la premi�re ligne du calcul
//			}
//			//on fait l'op�ration sur la ligne de la matrice principale
//			//on fait l'op�ration sur la ligne de la matrice indentit�
//		}
//		
//		//on ajoute la matrice principale et la matrice identit� au hashmap
//		
//	}
			
}
