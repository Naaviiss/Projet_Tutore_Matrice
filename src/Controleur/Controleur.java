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

import javax.swing.JOptionPane;

import modele.Data;
import modele.ExceptCaseVide;
import modele.ExceptEntreFraction;
import modele.ExceptNegatifMalPlace;
import modele.ExceptZeroDivision;
import modele.Fraction;
import modele.Matrice;
import vue.PanelAffichageMatrices;
import vue.PanelChoix;
import vue.PanelCommandes;

import vue.PanelGauss;
import vue.PanelMatrice;

public class Controleur implements ActionListener,MouseListener{
	
	private PanelMatrice chPanMatrice;
	private PanelChoix chPanelChoix;
	private PanelGauss chPanGauss;
	private PanelAffichageMatrices chPanAffichageMatrices;
	private String[] operation = new String[6]; //tableau correspondant au calcul de l'utilisateur
	Fraction constante; //constante de l'utilisateur récupérée, par défaut, elle vaut 1
	private PanelCommandes panCom; //panel commande
	
	
	public Controleur(PanelChoix pPanChoix) {
		//on met une constante par défaut oau cas où l'utilisateur n'en renseigne pas
		constante = new Fraction(1);
		
		
		//on instancie le tableau de string correspondant au calcul de l'utilisateur
		for(int i=0;i<operation.length;i++) {
			operation[i]= ""; //au départ, le tableau est vide
		}
		
		chPanelChoix = pPanChoix;
		List<Matrice> chMatrices = new ArrayList<Matrice>();//list des matrices
		List<Matrice> chMatricesID = new ArrayList<Matrice>();//liste des matrices identités
		List<String> chLigneModif = new ArrayList<String>();//liste des opérations effectuées sous forme de chaine de caractères
		List<String> chCommentaires= new ArrayList<String>();//liste des commentaires sur les calculs
		chPanAffichageMatrices = new PanelAffichageMatrices(chMatrices, chMatricesID,chLigneModif,chCommentaires);//on créé le panel affichage
	}
	
	public void actionPerformed(ActionEvent pEvt){
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
			try {
				Matrice M1 = chPanMatrice.getMatriceSaisi();//création de la matrice
				Matrice M2 = Matrice.identite(M1.getTaille());//création de la matrice identité
				chPanGauss = new PanelGauss(M1);
				chPanAffichageMatrices.ajoutMatrice(M1, M2,"","");//au départ la chaine pour le calcul et celle pour le commentaire sont vides
				chPanGauss.setAffichageMatrices(chPanAffichageMatrices);
				chPanelChoix.add(chPanGauss, "panel_gauss");
				chPanGauss.enregistreEcouteur(this);
				chPanelChoix.getCardLayout().show(chPanelChoix, "panel_gauss");
				panCom = chPanGauss.getPanelCommandes();//on récupère le panel commande
			} 
			catch (ExceptEntreFraction e) {
				JOptionPane.showMessageDialog(null, "Vous ne pouvez pas rentrer de lettres et de caractères spéciaux dans une fraction !","Erreur",JOptionPane.ERROR_MESSAGE);
			}
			catch (ExceptNegatifMalPlace e) {
				JOptionPane.showMessageDialog(null, "Erreur dans le placement du signe \"-\" !","Erreur",JOptionPane.ERROR_MESSAGE);
			}
			catch (ExceptZeroDivision e) {
				JOptionPane.showMessageDialog(null, "Vous ne pouvez pas diviser un entier par 0 !","Erreur",JOptionPane.ERROR_MESSAGE);
			}
			catch (ExceptCaseVide e) {
				JOptionPane.showMessageDialog(null, "Vous devez remplir toutes les cases de la matrice !","Erreur",JOptionPane.ERROR_MESSAGE);
			}
			
		}
		
		
		if(Arrays.asList(Data.FLECHES).contains(pEvt.getActionCommand())) { //si la commande de la source est une flèche
			for (int i = 2;i<operation.length;i++) { //si on change de flèche la suite du calcul est réinitialisée
				panCom.getLabel(i).setText("");
				operation[i] = "";
			}
			panCom.getLabel(1).setText(pEvt.getActionCommand());
			operation[1]= pEvt.getActionCommand();
		}

		//si on clique sur le bouton effacer, on efface le calcul en cours
		if(pEvt.getActionCommand().equals(Data.EFFACER)) {
			JLabel calcul[] = panCom.getCalcul(); //on récupère l'expression affichée par l'utilisateur
			for (int i=0;i<calcul.length;i++) {
				calcul[i].setText("");
				operation[i] = "";
			}
			//on remet la zone de commentaire à vide
			panCom.getZoneCommentaire().setText("");
		}
		
		//si on valide l'opération
		if(pEvt.getActionCommand().equals(Data.VALIDER_PANEL_COMMANDES)) {

			int ligneB;//index de la deuxième ligne choisie
			int ligneModifiee = getNumLigne(operation[0]); //on r�cup�re la ligne à modifier
			
			//on récupère le calcul sous forme de chaine
			String chaine = new String();
			for (int i =0;i<operation.length;i++) {
				chaine+=operation[i];
			}
			
			//on récupère le commentaire
			String commentaire = panCom.getZoneCommentaire().getText();
			
			//on recupere la derniere matrice de chaque liste
			Matrice actuelle = chPanAffichageMatrices.getChMatrices().get(chPanAffichageMatrices.getChMatrices().size()-1);//on récupère la matrice sur laquelle on travaille
			Matrice actuelleID = chPanAffichageMatrices.getChMatricesIdentites().get(chPanAffichageMatrices.getChMatricesIdentites().size()-1);//idem pour son identit�
			
			Matrice matricePrincipale = new Matrice(actuelle.getTaille());//matrice sur laquelle on va effectuer les calculs
			Matrice matriceIdentite = new Matrice(actuelleID.getTaille());//matrice identité sur laquelle on va effectuer les calculs
			
			//on copie les matrices
			matricePrincipale.copie(actuelle);
			matriceIdentite.copie(actuelleID);
			
			//Si l'utilisateur veut intervertir 2 lignes
			if (operation[1].equals(Data.FLECHES[1])) {
				ligneB = getNumLigne(operation[2]);
				matricePrincipale.echange(ligneModifiee, ligneB);//on échange les lignes sur la matrice principale
				matriceIdentite.echange(ligneModifiee, ligneB);//on échange les lignes sur la matrice identité
			}
			//Si l'utilisateur veut effectuer un calcul sur une ligne
			else {
				//si c'est la deuxième ligne qui prend un calcul
				if (Arrays.asList(Data.LIGNES).contains(operation[5])) {
					ligneB = getNumLigne(operation[5]);
					if (operation[4].equals("")) {
						operation[4] = "1";
					}
					try {
						matricePrincipale.modifyLine2(ligneModifiee, operation[3], ligneB, new Fraction(operation[4]));//on fait l'opération sur la ligne de la matrice principale
						matriceIdentite.modifyLine2(ligneModifiee, operation[3], ligneB, new Fraction(operation[4]));//on fait l'opération sur la ligne de la matrice identité
					} catch (ExceptEntreFraction e) {}
					catch (ExceptNegatifMalPlace e) {}
					catch (ExceptZeroDivision e) {}
					catch (ExceptCaseVide e) {}
					
				}
				//si c'est la première ligne qui prend un calcul
				else {
					try {
						matricePrincipale.modifyLine(ligneModifiee, new Fraction(operation[2]));//on fait l'opération sur la ligne de la matrice principale
						matriceIdentite.modifyLine(ligneModifiee, new Fraction(operation[2]));//on fait l'opération sur la ligne de la matrice identité
					} 
					catch (ExceptEntreFraction e) {}
					catch (ExceptNegatifMalPlace e) {}
					catch (ExceptZeroDivision e) {}
					catch (ExceptCaseVide e) {}
				}
			}
			chPanAffichageMatrices.ajoutMatrice(matricePrincipale,matriceIdentite,chaine,commentaire); //on ajoute les matrices,l'opération à la table
			
			//si on valide on reset l'operation en simulant un clic sur le bouton effacer
			panCom.getEffacer().doClick();
			
			//on change la matrice affichée dans le panelCommande
			panCom.refresh(matricePrincipale);
			panCom.getChChoixLigneMatrice().enregistreEcouteur(this); //on met le nouveau panel à l'écoute du controleur
			
			//si l'utilisateur réussit son calcul
			if(chPanAffichageMatrices.getChMatrices().get(chPanAffichageMatrices.getChMatrices().size()-1).isIdentite()) {
				//on lance un popup pour le féliciter
				JOptionPane.showMessageDialog(null, "Félicitations !\nVous avez réussi à retrouver la matricé identité !\n Pensez à exporter votre travail en PDF pour ne pas en perdre une miette ;)\n\nVoici votre matrice inversée:\n"+matriceIdentite.toString(),"Bravo !",JOptionPane.INFORMATION_MESSAGE);
			}
		}
		
		//si on clique sur le bouton constante
		if(pEvt.getActionCommand().equals(Data.CONSTANTE)) { //si la commande de la source est le bouton constante
			if( operation[1].equals(Data.FLECHES[0]) && (operation[2].equals("") || Arrays.asList(Data.LIGNES).contains(operation[2])) ) { //on  ne peut avoir une constante qu'avec la flèche <- et si une constante n'a pas déjà été entrée auparavant
				//Création du popup de la demande de la constante uniquement avec la flèche <-
				String txt = JOptionPane.showInputDialog(null,"Veuillez rentrer une constante"); //chaine de caractere qu'on va récupérer
				try {
					//Si on rentre une valeur pour la constante
					if (txt.equals("") || txt.equals("0")) {
						constante = new Fraction("1");//valeur par défaut à 1
					}
					else {
						constante = new Fraction(txt); //on convertit la chaine en fraction					
					}
					
					//on ajoute la constante au premier emplacement vide
					int labelVideConstante = panCom.getLabelVideConstante();//on récupère l'indice du premier label disponible pour une constante
					//si la constante est à son premier emplacement vide
					if (labelVideConstante == 2) {
						//la ligne qui suit doit être la même que celle à modifier
						operation[3] = operation[0];
						panCom.getLabel(3).setText(operation[3]);
					}
					operation[labelVideConstante] = constante.toString();
					
					if (!constante.toString().equals("1"))
						panCom.getLabel(labelVideConstante).setText(constante.toString());
				}
				catch(ExceptEntreFraction e) { //si on lève une exception
					JOptionPane.showMessageDialog(null, "Vous ne pouvez pas rentrer de lettres et de caractères spéciaux dans une fraction !","Erreur",JOptionPane.ERROR_MESSAGE);
					panCom.getEffacer().doClick();//pour l'instant, le calcul est reset
				}
				catch (ExceptNegatifMalPlace e) {
					JOptionPane.showMessageDialog(null, "Erreur dans le placement du signe \"-\" !","Erreur",JOptionPane.ERROR_MESSAGE);
					panCom.getEffacer().doClick();//pour l'instant, le calcul est reset
				}
				catch (ExceptZeroDivision e) {
					JOptionPane.showMessageDialog(null, "Vous ne pouvez pas diviser un entier par 0 !","Erreur",JOptionPane.ERROR_MESSAGE);
					panCom.getEffacer().doClick();//pour l'instant, le calcul est reset
				}
				catch (ExceptCaseVide e) {
					JOptionPane.showMessageDialog(null, "Vous devez remplir toutes les cases de la matrice !","Erreur",JOptionPane.ERROR_MESSAGE);
					panCom.getEffacer().doClick();//pour l'instant, le calcul est reset
				}
			}
		}
		
		if(Arrays.asList(Data.OPERATIONS).contains(pEvt.getActionCommand())) { //si la source de la commande est un opérateur
			//uniquement si la première ligne = ligne à modifier et qu'aucun signe n'a été renseigné
			if( operation[2].equals(operation[0]) && operation[3].equals("") ) {
				operation[3] = pEvt.getActionCommand();
				panCom.getLabel(3).setText(pEvt.getActionCommand());
			}
			else {
				JOptionPane.showMessageDialog(null, "Vous ne pouvez pas choisir un opérateur si vous n'avez pas écrit un calcul de la bonne forme !\n","Erreur",JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	//quand on clique sur une ligne
	@Override
	public void mouseClicked(MouseEvent e) {//si on clique sur une ligne
		int labelVide = panCom.getLabelVideLigne();

		if ( labelVide == 0 || operation[1].equals(Data.FLECHES[0]) ){ //si on choisit la ligne à modifier ou si on a utilisé la flèche <-
			if (labelVide == 0) {
				panCom.getLabel(labelVide).setText(e.getComponent().getName()); //on peut afficher la ligne cliquée
				operation[labelVide] = e.getComponent().getName();//on peut ajouter la ligne à l'opération
			}
			if( labelVide == 2 && operation[0].equals( e.getComponent().getName() ) ) {//si la première ligne après la flèche est la même que celle à modifier
				panCom.getLabel(labelVide).setText(e.getComponent().getName()); //on peut afficher la ligne cliquée
				operation[labelVide] = e.getComponent().getName();//on peut ajouter la ligne à l'opération
			}
			if (labelVide == 5 && !( operation[0].equals( e.getComponent().getName() ) ) && !Arrays.asList(Data.LIGNES).contains(operation[3]) ) { //si la troisième ligne du calcul est différente de la ligne à modifier et que la deuxime ligne choisie ne prend pas de calcul
				panCom.getLabel(labelVide).setText(e.getComponent().getName()); //on peut afficher la ligne cliquée
				operation[labelVide] = e.getComponent().getName();//on peut ajouter la ligne à l'opération
			}
			
			
		}
		else if (labelVide == 2 && operation[1].equals(Data.FLECHES[1])) {//s'il s'agit de la flèche <-> et qu'on choisit la ligne avec laquelle on va intervertir la première
			if (!(e.getComponent().getName().equals(operation[0]))) { //si la ligne à échangée est différente de la première ligne choisie
				panCom.getLabel(labelVide).setText(e.getComponent().getName()); //on peut afficher la ligne cliquée
				operation[labelVide] = e.getComponent().getName();//on peut ajouter la ligne à l'opération
			}
		}
		//ces if permettent d'éviter le calculs comme L2<-> L2 L3 ou L1 <- L2 L3 L2, etc.
		
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
		for(int i = 0; i<operation.length; i++) {
			System.out.print(""+i+" : "+operation[i]+"\n");
		}
		System.out.println("");
	}
	
	//retourne l'index correspondant à la ligne
	public int getNumLigne(String ligne) {
		if (ligne.equals(Data.LIGNES[0]))
			return 0;
		else if (ligne.equals(Data.LIGNES[1]))
				return 1;
		else if (ligne.equals(Data.LIGNES[2]))
			return 2;
		else if (ligne.equals(Data.LIGNES[3]))
				return 3;
		else
			return 4;
	}	
	
}
