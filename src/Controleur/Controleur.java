package Controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
		if(pEvt.getActionCommand().equals(Data.CHOIX[1])) {
			chPanelChoix.getCardLayout().show(chPanelChoix, "panel_taille");
		}
		if(pEvt.getActionCommand().equals(Data.VALIDER_PANEL_TAILLE)) {
			//chPanMatrice = new PanelMatrice(chPanelChoix.getPanTaille().getTaille());
			//chPanelChoix.add(chPanMatrice, "panel_matrice");
			//chPanelChoix.getCardLayout().show(chPanelChoix, "panel_matrice");
			chPanelChoix.getCardLayout().show(chPanelChoix, "panel_gauss");
		}
		if(pEvt.getActionCommand().equals(Data.VALIDER_PANEL_MATRICE)) {
			chPanelChoix.getCardLayout().show(chPanelChoix, "panel_gauss");
		}
		if(pEvt.getActionCommand().equals(Data.VALIDER_PANEL_COMMANDES)) {
			System.out.println("coucou");
			Fraction[][] tab = {{new Fraction(1,2),new Fraction(2,2),new Fraction(3,2)},{new Fraction(4,7),new Fraction(5,5),new Fraction(6,2)},{new Fraction(7,3),new Fraction(8,4),new Fraction(9,16)}};
			Matrice M1 = new Matrice(tab);
			Fraction[][] tab2 = {{new Fraction(1),new Fraction(2),new Fraction(3)},{new Fraction(4),new Fraction(5),new Fraction(6)},{new Fraction(7),new Fraction(8),new Fraction(9)}};
			Matrice M2 = new Matrice(tab2);
			chPanelChoix.getPanGauss().getPanelAffichageMatrices().ajoutMatrice(M1,M2);
			chPanelChoix.getPanGauss().getPanelAffichageMatrices().revalidate();
			chPanelChoix.getPanGauss().getPanelAffichageMatrices().repaint();
		}
		for (int i = 0; i<Data.OPERATIONS.length;i++) {
			if(pEvt.getActionCommand().equals(Data.OPERATIONS[i])) {
				
			}
		}
		for (int i = 0; i<Data.LIGNES.length; i++) {
			if(pEvt.getActionCommand().equals(Data.LIGNES[i])) {
				chPanelChoix.getPanGauss().getPanelCommandes().setCalcul(Data.LIGNES[i]);
				chPanelChoix.getPanGauss().getPanelCommandes().getCalcul();
				System.out.println(chPanelChoix.getPanGauss().getPanelCommandes().getCalcul());
			}
		}
	}

}
