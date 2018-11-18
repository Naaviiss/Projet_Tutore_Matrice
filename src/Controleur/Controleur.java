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
	}

}
