package Controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import modele.Data;
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
			chPanMatrice = new PanelMatrice(chPanelChoix.getPanTaille().getTaille());
			chPanelChoix.add(chPanMatrice, "panel_matrice");
			chPanelChoix.getCardLayout().show(chPanelChoix, "panel_matrice");
		}
		if(pEvt.getActionCommand().equals(Data.VALIDER_PANEL_MATRICE)) {
			chPanelChoix.getCardLayout().show(chPanelChoix, "panel_gauss");
		}
		if(pEvt.getActionCommand().equals(Data.VALIDER_PANEL_COMMANDES)) {
			
		}
		for (int i = 0; i<Data.OPERATIONS.length;i++) {
			if(pEvt.getActionCommand().equals(Data.OPERATIONS[i])) {
				
			}
		}
		for (int i = 0; i<Data.INTITULES.length;i++) {
			if(pEvt.getActionCommand().equals(Data.INTITULES[i])) {
				
			}
		}
	}

}
