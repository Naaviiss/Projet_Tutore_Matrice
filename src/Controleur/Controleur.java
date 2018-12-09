package Controleur;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Arrays;

import javax.swing.JLabel;

import modele.Data;
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
			chPanGauss = new PanelGauss(/*matrice*/);//on cr�er le panel gauss � partir de la matrice r�cup�r�e
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
		
		if(Arrays.asList(Data.OPERATIONS).contains(pEvt.getActionCommand())) { //si la commande de la source est un op�rateur
			chPanGauss.getPanelCommandes().getLabel(3).setText(pEvt.getActionCommand());
		}
	}
	
	//quand on clique sur une ligne
	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println("ici:"+chPanGauss.getPanelCommandes().getLabel(0).getText());
		chPanGauss.getPanelCommandes().getLabel(0).setText(e.getComponent().getName());
		System.out.println(chPanGauss.getPanelCommandes().getLabel(0).getText());
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

}
