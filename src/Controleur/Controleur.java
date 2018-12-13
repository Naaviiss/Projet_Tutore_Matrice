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

import modele.Data;
import modele.Matrice;
import vue.PanelAffichageMatrices;
import vue.PanelChoix;
import vue.PanelCommandes;
import vue.PanelGauss;
import vue.PanelMatrice;

public class Controleur implements ActionListener,MouseListener{
	
	PanelMatrice chPanMatrice;
	PanelChoix chPanelChoix;
	PanelGauss chPanGauss;
	PanelAffichageMatrices chPanAffichageMatrices;
	
	public Controleur(PanelChoix pPanChoix) {
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
			chPanGauss.getPanelCommandes().getLabel(4).setText(pEvt.getActionCommand());
		}
		
		if(Arrays.asList(Data.FLECHES).contains(pEvt.getActionCommand())) { //si la commande de la source est une flèche
			PanelCommandes panCom = chPanGauss.getPanelCommandes();//on recupere le panel commande
			panCom.getLabel(1).setText(pEvt.getActionCommand());
			if(pEvt.getActionCommand().equals(Data.FLECHES[0]))//s'il sagit de la flèche <-
				panCom.getLabel(3).setText(panCom.getLabel(0).getText());//la ligne suivant devient la même ligne que sur laquelle le calcul va s'effectuer
		}
		
		//si on clique sur le bouton effacer, on efface le calcul en cours
		if(pEvt.getActionCommand().equals(Data.EFFACER)) {
			JLabel calcul[] = chPanGauss.getPanelCommandes().getCalcul(); //on récupère l'expression affichée par l'utilisateur
			for (int i=0;i<calcul.length;i++) {
				calcul[i].setText("");
			}
		}
		
	}
	
	//quand on clique sur une ligne
	@Override
	public void mouseClicked(MouseEvent e) {
		PanelCommandes panCom = chPanGauss.getPanelCommandes();
		panCom.getLabel(panCom.getLabelVide()).setText(e.getComponent().getName());
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
