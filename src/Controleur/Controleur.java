package Controleur;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JOptionPane;

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
		
		if(Arrays.asList(Data.FLECHES).contains(pEvt.getActionCommand())) {
			System.out.print("Je clique sur le bouton "+pEvt.getActionCommand());
			operation[1]= pEvt.getActionCommand();
			positionOperation=2;
			for(int i = 2; i<7;i++) {
				operation[i]= "";
			}
			affichageOperation();
		}
		
		if(pEvt.getActionCommand().equals(Data.CONSTANTE)) { //si la commande de la source est le bouton constante
			System.out.print("Je clique sur le bouton constante\n");
			if(operation[1] == "<-") {
				//Création du popup de la demande de la constante
				String constante = null;
				String txt = null;
				Boolean test = true;
				while (test){
					txt = JOptionPane.showInputDialog(null,"Veuillez rentrer une constante");
					System.out.println(txt);
					//Si on rentre une valeur pour la constante
					if (txt != null){
						if (txt.length() == 1 && txt.equalsIgnoreCase("0")){
							constante = "1";
						}
						else{
							constante = txt;
						}
					}
					test = false;
				}
				if(estUnEntier(constante)){ //vérifie que la constante donnee est bien un entier
					if(positionOperation!=7 && positionOperation!=0) { //si la chaine depasse les 7 actions on ne fait pas l'action ou si la constante est mal placer
						if(positionOperation!=2 || pEvt.getActionCommand()!= "0") { //remplacer pEvt.getActionCommand() par la valeur donnee par l'utilisateur et "0" par un int
							//si la premiere constante est differente de 0
							String valider = "ok";
							for(int i = 0; i<Data.LIGNES.length;i++) {
								if(Data.LIGNES[i] == operation[positionOperation-1]) {
									valider = "non ok";
								}
							}
							if(estUnEntier(operation[positionOperation-2])) {
								valider = "non ok";
							}
							if(valider == "ok") {//est ce que on a 2 lignes a la suite (si oui rien ne se passe)
									valider = "ok";
									if(estUnEntier(operation[positionOperation-1])) {
										valider = "non ok";
									}//fin du test
								if(valider == "ok") {
									operation[positionOperation]= constante;
									positionOperation+=1;
								}
							}
						}
					}
				}
				//on fera la gestion de la constante une fois qu'elle aura ete ajoutee
				affichageOperation();
			}
		}
		
		if(Arrays.asList(Data.LIGNES).contains(pEvt.getActionCommand())) { //si la commande de la source est une ligne
			System.out.print("Je clique sur le bouton "+pEvt.getActionCommand());
			if(operation[1] == "<-") {
				//PARTIT AJOUT LIGNE
				if(positionOperation!=7) { //si la chaine depasse les 7 actions on ne fait pas l'action
					if(positionOperation == 0) { //est ce que c la premiere action de la chaine
						operation[positionOperation]= pEvt.getActionCommand();
						positionOperation+=1;
					}
					else {
						if(positionOperation == 2 || positionOperation == 3) { //est ce que c la premiere action de la chaine
							if(positionOperation == 3) {
								String valider = "ok";
								for(int i = 0; i<Data.LIGNES.length;i++) {
									if(Data.LIGNES[i] == operation[positionOperation-1]) {
										valider = "non ok";
									}
								}
								if(valider == "ok") {
									if(pEvt.getActionCommand()==operation[0]){
										operation[positionOperation]= pEvt.getActionCommand();
										positionOperation+=1;
									}
								}
							}
							else {
								if(pEvt.getActionCommand()==operation[0]){
									operation[positionOperation]= pEvt.getActionCommand();
									positionOperation+=1;
								}
							}
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
				}
				affichageOperation();
			}
			else {
				if(operation[0]=="") {
					operation[0]=pEvt.getActionCommand();
				}
				else {
					String valider = "ok";
					for(int i = 0; i<Data.LIGNES.length;i++) {
						if(Data.LIGNES[i] == operation[0]) {
							valider = "non ok";
						}
					}
					if(valider == "ok") {
						operation[2]=pEvt.getActionCommand();
					}
				}
			}
		}
		if(Arrays.asList(Data.OPERATIONS).contains(pEvt.getActionCommand())) { //si la commande de la source est un opérateur
			System.out.print("Je clique sur le bouton "+pEvt.getActionCommand());
			if(operation[1] == "<-") {
				if(positionOperation!=7 && positionOperation!=0 && positionOperation!=2 && positionOperation!=6 && positionOperation!=5)	 { //si la chaine depasse les 7 actions ou si l'operateur ce trouve en premiere position on ne fait pas l'action 
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
		for(int i = 0; i<7; i++) {
			System.out.print(operation[i]);
		}
		System.out.println("");
	}
	
	public boolean estUnEntier(String chaine) { //fonction permettant de voir si la constante est un entier
		try {
			Integer.parseInt(chaine);
		} catch (NumberFormatException e){
			return false;
		}
		return true;
	}
	
	//quand on clique sur une ligne
	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println("ici:"+chPanGauss.getPanelCommandes().getLabel(0).getText());
		chPanGauss.getPanelCommandes().getLabel(0).setText(e.getComponent().getName());
		System.out.println(chPanGauss.getPanelCommandes().getLabel(0).getText());
	}
	
		
		
		//ISFRACTION
		// String.isFraction() dit si la string est une fraction
		public boolean isFraction(String parFrac) {
			int slash = 0;  //si il y a un slash dans le String
			int rencontre = 0;	//savoir quand on a passé le slash
			String numerateurString = "";
			String denominateurString = "";
			for(char ch : parFrac.toCharArray()) { //Test si il y a un slash dans le String
				if(ch == '/') {
					slash = 1;
				}
			}
			
			if(slash == 1) {
				for(char ch : parFrac.toCharArray()) {	
					if(ch == '/') {
						rencontre = 1;
					}
					else if(ch != '/' && rencontre == 0) {
						if(ch != '0' && ch != '1' && ch != '2' && ch != '3' && ch != '4' && ch != '5' && ch != '6' && ch != '7' && ch != '8' && ch != '9') {
							return false;
						}
						else {
							numerateurString += ch;
						}
						
					}
					else {
						if(ch != '0' && ch != '1' && ch != '2' && ch != '3' && ch != '4' && ch != '5' && ch != '6' && ch != '7' && ch != '8' && ch != '9') {
							return false;
						}
						else {
							denominateurString += ch;
						}
					}
				}
				if(numerateurString == "" || denominateurString == "") {
					return false;
				}
				if(Integer.parseInt(denominateurString) == 0) {
					return false;
				}
			}
			else {
				for(char ch : parFrac.toCharArray()) {
					if(ch != '0' && ch != '1' && ch != '2' && ch != '3' && ch != '4' && ch != '5' && ch != '6' && ch != '7' && ch != '8' && ch != '9') {
						return false;
					}
					else {
						numerateurString += ch;
					}
				}
				if(numerateurString == "") {
					return false;
				}
			}
			return true;
		}
}
