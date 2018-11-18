package Controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import modele.Data;
import vue.PanelMatrice;

public class Controleur implements ActionListener{
	
	//faudra mettre tous les champs mais por l'instant je mets le mien
	PanelMatrice chPanMatrice;
	
	public Controleur(PanelMatrice pPanMatrice) {
		chPanMatrice = pPanMatrice;
	}
	
	public void actionPerformed(ActionEvent pEvt) {
		if(pEvt.getActionCommand().equals(Data.VALIDER_PANEL_MATRICE)) {
			
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
