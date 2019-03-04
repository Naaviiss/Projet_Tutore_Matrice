package Controleur;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

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
	private Boolean etat=false; //etat pour savoir où l'utilisateur se trouve dans l'application
								//utilisé pour revenir au tout début du calcul de la matrice inverse
	
	
	public Controleur(PanelChoix pPanChoix) {
		
		//on met une constante par dÃ©faut au cas oÃ¹ l'utilisateur n'en renseigne pas
		constante = new Fraction(1);
		
		//on instancie le tableau de string correspondant au calcul de l'utilisateur
		for(int i=0;i<operation.length;i++) {
			operation[i]= ""; //au dÃ©part, le tableau est vide
		}
		
		chPanelChoix = pPanChoix;
		//fenMere = (FenetreMere) SwingUtilities.windowForComponent(chPanelChoix);
		List<Matrice> chMatrices = new ArrayList<Matrice>();//list des matrices
		List<Matrice> chMatricesID = new ArrayList<Matrice>();//liste des matrices identitÃ©s
		List<String> chLigneModif = new ArrayList<String>();//liste des opÃ©rations effectuÃ©es sous forme de chaine de caractÃ¨res
		List<String> chCommentaires= new ArrayList<String>();//liste des commentaires sur les calculs
		chPanAffichageMatrices = new PanelAffichageMatrices(chMatrices, chMatricesID,chLigneModif,chCommentaires);//on crÃ©Ã© le panel affichage
	}
	
	@Override
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
				Matrice M2 = Matrice.identite(M1.getTaille());//crÃ©ation de la matrice identitÃ©
				chPanGauss = new PanelGauss(M1);
				chPanAffichageMatrices.ajoutMatrice(M1, M2,"","");//au dÃ©part la chaine pour le calcul et celle pour le commentaire sont vides
				chPanGauss.setAffichageMatrices(chPanAffichageMatrices);
				chPanelChoix.add(chPanGauss, "panel_gauss");
				chPanGauss.enregistreEcouteur(this);
				chPanelChoix.getCardLayout().show(chPanelChoix, "panel_gauss");
				panCom = chPanGauss.getPanelCommandes();//on rÃ©cupÃ¨re le panel commande
				etat = true;
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
		
		
		if(Arrays.asList(Data.FLECHES).contains(pEvt.getActionCommand())) { //si la commande de la source est une flÃ¨che
			for (int i = 2;i<operation.length;i++) { //si on change de flÃ¨che la suite du calcul est rÃ©initialisÃ©e
				panCom.getLabel(i).setText("");
				operation[i] = "";
			}
			panCom.getLabel(1).setText(pEvt.getActionCommand());
			operation[1]= pEvt.getActionCommand();
		}

		//si on clique sur le bouton effacer, on efface le calcul en cours
		if(pEvt.getActionCommand().equals(Data.EFFACER)) {
			JLabel calcul[] = panCom.getCalcul(); //on rÃ©cupÃ¨re l'expression affichÃ©e par l'utilisateur
			for (int i=0;i<calcul.length;i++) {
				calcul[i].setText("");
				operation[i] = "";
			}
			//on remet la zone de commentaire Ã  vide
			panCom.getZoneCommentaire().setText("");
		}
		
		//si on valide l'opÃ©ration
		if(pEvt.getActionCommand().equals(Data.VALIDER_PANEL_COMMANDES)) {

			int ligneB;//index de la deuxiÃ¨me ligne choisie
			int ligneModifiee = getNumLigne(operation[0]); //on rï¿½cupï¿½re la ligne Ã  modifier
			
			//on rÃ©cupÃ¨re le calcul sous forme de chaine
			String chaine = new String();
			for (int i =0;i<operation.length;i++) {
				chaine+=operation[i];
			}
			
			//on rÃ©cupÃ¨re le commentaire
			String commentaire = panCom.getZoneCommentaire().getText();
			
			//on recupere la derniere matrice de chaque liste
			Matrice actuelle = chPanAffichageMatrices.getChMatrices().get(chPanAffichageMatrices.getChMatrices().size()-1);//on r�cup�re la matrice sur laquelle on travaille
			Matrice actuelleID = chPanAffichageMatrices.getChMatricesID().get(chPanAffichageMatrices.getChMatricesID().size()-1);//idem pour son identit�
			
			Matrice matricePrincipale = new Matrice(actuelle.getTaille());//matrice sur laquelle on va effectuer les calculs
			Matrice matriceIdentite = new Matrice(actuelleID.getTaille());//matrice identité sur laquelle on va effectuer les calculs
			
			//on copie les matrices
			matricePrincipale.copie(actuelle);
			matriceIdentite.copie(actuelleID);
			
			//Si l'utilisateur veut intervertir 2 lignes
			if (operation[1].equals(Data.FLECHES[1])) {
				ligneB = getNumLigne(operation[2]);
				matricePrincipale.echange(ligneModifiee, ligneB);//on Ã©change les lignes sur la matrice principale
				matriceIdentite.echange(ligneModifiee, ligneB);//on Ã©change les lignes sur la matrice identitÃ©
			}
			//Si l'utilisateur veut effectuer un calcul sur une ligne
			else {
				//si c'est la deuxiÃ¨me ligne qui prend un calcul
				if (Arrays.asList(Data.LIGNES).contains(operation[5])) {
					ligneB = getNumLigne(operation[5]);
					if (operation[4].equals("")) {
						operation[4] = "1";
					}
					try {
						matricePrincipale.modifyLine2(ligneModifiee, operation[3], ligneB, new Fraction(operation[4]));//on fait l'opÃ©ration sur la ligne de la matrice principale
						matriceIdentite.modifyLine2(ligneModifiee, operation[3], ligneB, new Fraction(operation[4]));//on fait l'opÃ©ration sur la ligne de la matrice identitÃ©
					} catch (ExceptEntreFraction e) {}
					catch (ExceptNegatifMalPlace e) {}
					catch (ExceptZeroDivision e) {}
					catch (ExceptCaseVide e) {}
					
				}
				//si c'est la premiÃ¨re ligne qui prend un calcul
				else {
					try {
						matricePrincipale.modifyLine(ligneModifiee, new Fraction(operation[2]));//on fait l'opÃ©ration sur la ligne de la matrice principale
						matriceIdentite.modifyLine(ligneModifiee, new Fraction(operation[2]));//on fait l'opÃ©ration sur la ligne de la matrice identitÃ©
					} 
					catch (ExceptEntreFraction e) {}
					catch (ExceptNegatifMalPlace e) {}
					catch (ExceptZeroDivision e) {}
					catch (ExceptCaseVide e) {}
				}
			}
			chPanAffichageMatrices.ajoutMatrice(matricePrincipale,matriceIdentite,chaine,commentaire); //on ajoute les matrices,l'opÃ©ration Ã  la table
			
			//si on valide on reset l'operation en simulant un clic sur le bouton effacer
			panCom.getEffacer().doClick();
			
			//on change la matrice affichÃ©e dans le panelCommande
			panCom.refresh(matricePrincipale);
			panCom.getChChoixLigneMatrice().enregistreEcouteur(this); //on met le nouveau panel Ã  l'Ã©coute du controleur
			
			//si l'utilisateur rÃ©ussit son calcul
			if(chPanAffichageMatrices.getChMatrices().get(chPanAffichageMatrices.getChMatrices().size()-1).isIdentite()) {
				//on lance un popup pour le fÃ©liciter
				JOptionPane.showMessageDialog(null, "FÃ©licitations !\nVous avez rÃ©ussi Ã  retrouver la matricÃ© identitÃ© !\n Pensez Ã  exporter votre travail en PDF pour ne pas en perdre une miette ;)\n\nVoici votre matrice inversÃ©e:\n"+matriceIdentite.toString(),"Bravo !",JOptionPane.INFORMATION_MESSAGE);
			}
		}
		
		//si on clique sur le bouton constante
		if(pEvt.getActionCommand().equals(Data.CONSTANTE)) { //si la commande de la source est le bouton constante
			if( operation[1].equals(Data.FLECHES[0]) && (operation[2].equals("") || Arrays.asList(Data.LIGNES).contains(operation[2])) ) { //on  ne peut avoir une constante qu'avec la flÃ¨che <- et si une constante n'a pas dÃ©jÃ  Ã©tÃ© entrÃ©e auparavant
				//CrÃ©ation du popup de la demande de la constante uniquement avec la flÃ¨che <-
				String txt = JOptionPane.showInputDialog(null,"Veuillez rentrer une constante"); //chaine de caractere qu'on va rÃ©cupÃ©rer
				try {
					//Si on rentre une valeur pour la constante
					if (txt.equals("") || txt.equals("0")) {
						constante = new Fraction("1");//valeur par dÃ©faut Ã  1
					}
					else {
						constante = new Fraction(txt); //on convertit la chaine en fraction					
					}
					
					//on ajoute la constante au premier emplacement vide
					int labelVideConstante = panCom.getLabelVideConstante();//on rÃ©cupÃ¨re l'indice du premier label disponible pour une constante
					//si la constante est Ã  son premier emplacement vide
					if (labelVideConstante == 2) {
						//la ligne qui suit doit Ãªtre la mÃªme que celle Ã  modifier
						operation[3] = operation[0];
						panCom.getLabel(3).setText(operation[3]);
					}
					operation[labelVideConstante] = constante.toString();
					
					if (!constante.toString().equals("1"))
						panCom.getLabel(labelVideConstante).setText(constante.toString());
				}
				catch(ExceptEntreFraction e) { //si on lÃ¨ve une exception
					JOptionPane.showMessageDialog(null, "Vous ne pouvez pas rentrer de lettres et de caractÃ¨res spÃ©ciaux dans une fraction !","Erreur",JOptionPane.ERROR_MESSAGE);
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
		
		if(Arrays.asList(Data.OPERATIONS).contains(pEvt.getActionCommand())) { //si la source de la commande est un opÃ©rateur
			//uniquement si la premiÃ¨re ligne = ligne Ã  modifier et qu'aucun signe n'a Ã©tÃ© renseignÃ©
			if( operation[2].equals(operation[0]) && operation[3].equals("") ) {
				operation[3] = pEvt.getActionCommand();
				panCom.getLabel(3).setText(pEvt.getActionCommand());
			}
			else {
				JOptionPane.showMessageDialog(null, "Vous ne pouvez pas choisir un opÃ©rateur si vous n'avez pas Ã©crit un calcul de la bonne forme !\n","Erreur",JOptionPane.ERROR_MESSAGE);
			}
		}
		
		//PARTIE SUR LE MENU
				//Si l'utilisateur décide d'aller au calcul précédent
				if (pEvt.getActionCommand().equals(Data.TITRE_MATRICE_LISTE[0]))
				{
					//On vérifie si l'utilisateur est au niveau de la JTable ou avant
					if (etat != false) {
						//On récupère la liste des matrices
						List<Matrice> chMatrice = chPanAffichageMatrices.getChMatrices();
						//Si on n'est pas à la toute première ligne
						//on peut continuer, sinon, on ne fait rien.
						if (chMatrice.size() != 1){
							String texte = new String("Devra revenir en arrière");
							JOptionPane.showMessageDialog(null, texte, "Aide d'utilisation", JOptionPane.INFORMATION_MESSAGE);
							
							//On récupère chaque liste
							List<Matrice> chMatriceID = chPanAffichageMatrices.getchMatricesIdentites();
							List<String> chLignes = chPanAffichageMatrices.getChLigneModif();
							List<String> chCommentaires= chPanAffichageMatrices.getChCommentaire();
		
							//on enlève le dernier élèment pour chaque liste
						    chMatrice.remove(chMatrice.size()-1);
						    chMatriceID.remove(chMatrice.size());
						    chLignes.remove(chMatrice.size()-1);
						    chCommentaires.remove(chMatrice.size()-1);
		
						    //On actualise dans la JTable les changements 
							PanelAffichageMatrices.clearTableAt(chPanAffichageMatrices.getTableMatrices(),chMatrice.size()); 
							
							//On récupère la matrice sur laquelle on va dorénavant travailler
							Matrice actuelle = chPanAffichageMatrices.getChMatrices().get(chMatrice.size()-1);
							
							//On l'actualise sur l'affichage
							chPanGauss.setAffichageMatrices(chPanAffichageMatrices);
							chPanelChoix.add(chPanGauss, "panel_gauss");										
							chPanelChoix.getCardLayout().show(chPanelChoix, "panel_gauss");
							
							//si on valide on reset l'operation en simulant un clic sur le bouton effacer
							panCom.getEffacer().doClick();
							
							//on change la matrice affichage dans le panelCommande
							panCom.refresh(actuelle);
							panCom.getChChoixLigneMatrice().enregistreEcouteur(this); //on met le nouveau panel à l'écoute du controleur
						}
					}
				}
				if (pEvt.getActionCommand().equals(Data.TITRE_MATRICE_LISTE[1])){
					String texte = new String("Devra agrandir le texte");
					JOptionPane.showMessageDialog(null, texte, "Aide d'utilisation", JOptionPane.INFORMATION_MESSAGE);
				}
				if (pEvt.getActionCommand().equals(Data.TITRE_MATRICE_LISTE[2])){
					String texte = new String("Devra retrecir le texte");
					JOptionPane.showMessageDialog(null, texte, "Aide d'utilisation", JOptionPane.INFORMATION_MESSAGE);
				}
				
				//Permettre à l'utilisateur de recommencer des calculs depuis le début sur sa matrice
				if (pEvt.getActionCommand().equals(Data.TITRE_MATRICE_LISTE[3])){
					
					String texte = new String("Devra recommencer le calcul");
					JOptionPane.showMessageDialog(null, texte, "Aide d'utilisation", JOptionPane.INFORMATION_MESSAGE);
					//PanelAffichageMatrices.deleteAllRows(chPanAffichageMatrices.getModel());
					//On efface ce qui est présent dans la JTable
					PanelAffichageMatrices.clearTable(chPanAffichageMatrices.getTableMatrices());
					//On vide toutes les listes (qui contiennent la matrice, l'inverse, l'opération et le commentaire)
					chPanAffichageMatrices.viderListe();

					//On vérifie si l'utilisateur est au niveau de la JTable ou avant
					if (etat != false) {
						//s'il est au niveau de la JTable, on remet tout comme avant.
						try {
							Matrice M1 = chPanMatrice.getMatriceSaisi();//crÃ©ation de la matrice
							Matrice M2 = Matrice.identite(M1.getTaille());//crÃ©ation de la matrice identitÃ©
							chPanGauss = new PanelGauss(M1);
							chPanAffichageMatrices.ajoutMatrice(M1, M2,"","");//au dÃ©part la chaine pour le calcul et celle pour le commentaire sont vides
							chPanGauss.setAffichageMatrices(chPanAffichageMatrices);
							chPanelChoix.add(chPanGauss, "panel_gauss");
							chPanGauss.enregistreEcouteur(this);
							chPanelChoix.getCardLayout().show(chPanelChoix, "panel_gauss");
							panCom = chPanGauss.getPanelCommandes();//on rÃ©cupÃ¨re le panel commande
						} 
						catch (ExceptEntreFraction e) {
							JOptionPane.showMessageDialog(null, "Vous ne pouvez pas rentrer de lettres et de caractÃ¨res spÃ©ciaux dans une fraction !","Erreur",JOptionPane.ERROR_MESSAGE);
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
					//Sinon, il ne se passe rien.
				}
				
				if (pEvt.getActionCommand().equals(Data.TITRE_MATRICE[0])){
					String texte = new String("RETOUR AU MENU PRINCIPAL");
					JOptionPane.showMessageDialog(null, texte, "Aide d'utilisation", JOptionPane.INFORMATION_MESSAGE);
					}
				
				if (pEvt.getActionCommand().equals(Data.TITRE_MATRICE[2])){
					String texte = new String("Pour bien utiliser ce logiciel, il faut suivre les Ã©tapes suivantes. Toutes les Ã©tapes nÃ©cessitent d'appuyer sur un bouton 'valider' Ã  chaque fois.\n\n\nPremiÃ¨rement, choisir la taille de sa matrice. Celle-ci peut Ãªtre comprise entre 3 et 5 (Si on comprends le principe avec ces tailles-lÃ , on comprend le principe avec des tailles encore plus grandes.\n\n"
							+ "DeuxiÃ¨mement, remplir sa matrice. On peut remplir la matrice avec des entiers (positifs, nÃ©gatifs, nuls) et des fractions (positives,nÃ©gatives). Les fractions seront rÃ©duites automatiquement.\n\n"
							+ "TroisiÃ¨mement, effectuer des calculs sur sa matrice pour trouver la matrice inverse. Les calculs doivent s'Ã©crirent correctement. Les diffÃ©rents formes de calculs possibles sont les suivantes :\n\n"
							+ "Ligne_i â†” Ligne_j\n"
							+ "Ligne_i â†� lambda * ligne_i (Si lambda â‰  0)\n"
							+ "Ligne_i â†� ligne_i + lambda * ligne_j\n\n"
							+ "Une matrice identitÃ© correspond Ã  : \n" + Matrice.identite(3).toString()
							+ "Bonne chance !");

					JOptionPane.showMessageDialog(null, texte, "Aide d'utilisation", JOptionPane.INFORMATION_MESSAGE);
				}
				//Fermer seulement la fenêtre actuelle
				if (pEvt.getActionCommand().equals(Data.TITRE_MATRICE[3])){
		             SwingUtilities.getWindowAncestor(chPanelChoix).dispose();
				}
				
				//Fermer entièrement l'application
				if(pEvt.getActionCommand().equals(Data.TITRE_MATRICE[4])) {
					System.exit(0);
				}
		
	}
	
	//quand on clique sur une ligne
	@Override
	public void mouseClicked(MouseEvent e) {//si on clique sur une ligne
		int labelVide = panCom.getLabelVideLigne();

		if ( labelVide == 0 || operation[1].equals(Data.FLECHES[0]) ){ //si on choisit la ligne Ã  modifier ou si on a utilisÃ© la flÃ¨che <-
			if (labelVide == 0) {
				panCom.getLabel(labelVide).setText(e.getComponent().getName()); //on peut afficher la ligne cliquÃ©e
				operation[labelVide] = e.getComponent().getName();//on peut ajouter la ligne Ã  l'opÃ©ration
			}
			if( labelVide == 2 && operation[0].equals( e.getComponent().getName() ) ) {//si la premiÃ¨re ligne aprÃ¨s la flÃ¨che est la mÃªme que celle Ã  modifier
				panCom.getLabel(labelVide).setText(e.getComponent().getName()); //on peut afficher la ligne cliquÃ©e
				operation[labelVide] = e.getComponent().getName();//on peut ajouter la ligne Ã  l'opÃ©ration
			}
			if (labelVide == 5 && !( operation[0].equals( e.getComponent().getName() ) ) && !Arrays.asList(Data.LIGNES).contains(operation[3]) ) { //si la troisiÃ¨me ligne du calcul est diffÃ©rente de la ligne Ã  modifier et que la deuxime ligne choisie ne prend pas de calcul
				panCom.getLabel(labelVide).setText(e.getComponent().getName()); //on peut afficher la ligne cliquÃ©e
				operation[labelVide] = e.getComponent().getName();//on peut ajouter la ligne Ã  l'opÃ©ration
			}
			
			
		}
		else if (labelVide == 2 && operation[1].equals(Data.FLECHES[1])) {//s'il s'agit de la flÃ¨che <-> et qu'on choisit la ligne avec laquelle on va intervertir la premiÃ¨re
			if (!(e.getComponent().getName().equals(operation[0]))) { //si la ligne Ã  Ã©changÃ©e est diffÃ©rente de la premiÃ¨re ligne choisie
				panCom.getLabel(labelVide).setText(e.getComponent().getName()); //on peut afficher la ligne cliquÃ©e
				operation[labelVide] = e.getComponent().getName();//on peut ajouter la ligne Ã  l'opÃ©ration
			}
		}
		//ces if permettent d'Ã©viter le calculs comme L2<-> L2 L3 ou L1 <- L2 L3 L2, etc.
		
		
		
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

	private void affichageOperation() { //fonction test de pour la crÃ©ation de l'opÃ©ration
		
		System.out.print("\nOpÃ©ration : ");
		for(int i = 0; i<operation.length; i++) {
			System.out.print(""+i+" : "+operation[i]+"\n");
		}
		System.out.println("");
	}
	
	//retourne l'index correspondant Ã  la ligne
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
