package Controleur;

import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.nio.channels.SelectableChannel;
import java.util.Arrays;

import modele.Data;
import modele.Fraction;
import modele.Matrice;
import vue.PanelAffichage;
import vue.PanelChoix;
import vue.PanelGauss;
import vue.PanelMatrice;

public class Controleur implements ActionListener,MouseListener{
	
	PanelMatrice chPanMatrice;
	PanelChoix chPanelChoix;
	PanelGauss chPanGauss;
	
	public Controleur(PanelChoix pPanChoix) {
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
			chPanGauss = new PanelGauss(/*matrice*/);//on créer le panel gauss à partir de la matrice récupérée
			chPanelChoix.getCardLayout().show(chPanelChoix, "panel_gauss");
		}
		
		if(pEvt.getActionCommand().equals(Data.VALIDER_PANEL_COMMANDES)) {//on valide son opération
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
		
		if(Arrays.asList(Data.OPERATIONS).contains(pEvt.getActionCommand())) { //si la commande de la source est un opérateur
			System.out.println("Je clique sur un operateur\n");
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
