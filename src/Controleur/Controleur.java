package Controleur;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.TableColumn;

import com.itextpdf.text.DocumentException;

import modele.Data;
import modele.ExceptCaseVide;
import modele.ExceptEntreFraction;
import modele.ExceptNegatifMalPlace;
import modele.ExceptZeroDivision;
import modele.Fraction;
import modele.Matrice;
import modele.TablePDF;
import vue.MultiLigneRenderer;
import vue.PanelAffichageMatrices;
import vue.PanelChoix;
import vue.PanelCommandes;
import vue.PanelGauss;
import vue.PanelMatrice;
import vue.PanelTaille;

public class Controleur implements ActionListener,MouseListener{
	
	private PanelMatrice chPanMatrice;
	private PanelTaille chPanTaille;

	private PanelChoix chPanelChoix;
	private PanelGauss chPanGauss;
	private PanelAffichageMatrices chPanAffichageMatrices;
	private String[] operation = new String[6]; //tableau correspondant au calcul de l'utilisateur
	Fraction constante; //constante de l'utilisateur rÃ©cupÃ©rÃ©e, par dÃ©faut, elle vaut 1
	private PanelCommandes panCom; //panel commande
	private Boolean etat=false; //etat pour savoir oÃ¹ l'utilisateur se trouve dans l'application
								//utilisÃ© pour revenir au tout dÃ©but du calcul de la matrice inverse
	
	
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
		List<String> chLigneModif = new ArrayList<String>();//liste des opÃ©rations effectuÃ©es sous forme de chaine de caractÃ©res
		List<String> chCommentaires= new ArrayList<String>();//liste des commentaires sur les calculs
		chPanAffichageMatrices = new PanelAffichageMatrices(chMatrices, chMatricesID,chLigneModif,chCommentaires);//on crÃ©Ã© le panel affichage
	}
	
	@Override
	public void actionPerformed(ActionEvent pEvt){
		if(pEvt.getActionCommand().equals(Data.CHOIX[1])) {//choix du programme matrice
			chPanelChoix.getCardLayout().show(chPanelChoix, "panel_taille");
		}
		
		if(pEvt.getActionCommand().equals(Data.VALIDER_PANEL_TAILLE)) {//choix de la taille de la matrice
			chPanMatrice = new PanelMatrice(chPanTaille.getTaille());
			chPanelChoix.add(chPanMatrice, "panel_matrice");
			chPanMatrice.enregistreEcouteur(this);
			chPanelChoix.getCardLayout().show(chPanelChoix, "panel_matrice");	
		}
		
		//Si l'utilisateur choisit le mode didacticiel
		if(pEvt.getActionCommand().equals(Data.VALIDER_DIDACTICIEL)){
			//On crÃ©e une matrice de taille 3
			Matrice M1 = new Matrice(3);
			
			//On la remplit
			M1.setCase(0,0,new Fraction(2));
			M1.setCase(0,1,new Fraction(1));
			M1.setCase(0,2,new Fraction(2));
			M1.setCase(1,0,new Fraction(1));
			M1.setCase(1,1,new Fraction(1));
			M1.setCase(1,2,new Fraction(3));
			M1.setCase(2,0,new Fraction(2));
			M1.setCase(2,1,new Fraction(2));
			M1.setCase(2,2,new Fraction(1));
			
			//crÃ©ation de la matrice identitÃ©
			Matrice M2 = Matrice.identite(M1.getTaille());

			chPanGauss = new PanelGauss(M1);
			
			//on recupere la derniere matrice de chaque liste
			Matrice actuelle;//on rÃ©cupÃ©re la matrice sur laquelle on travaille
			Matrice actuelleID;//idem pour son identitÃ©
			
			Matrice matricePrincipale;//matrice sur laquelle on va effectuer les calculs
			Matrice matriceIdentite;//matrice identitÃ© sur laquelle on va effectuer les calculs
			
			chPanAffichageMatrices.ajoutMatrice(M1, M2,"","");

			//CrÃ©ation de toutes les opÃ©rations suivantes
			//que ce soit pour les lignes, le signe, la multiplication, l'opÃ©ration
			//ou le commentaire
			int []chLigneA = {0,1,2,2,2,1,0};
			int []chLigneB = {1,0,0,1,2,2,2};
			String []chSigne = {"-","-","-","-","","-","+"};
			Fraction [] chFraction = {new Fraction(1),new Fraction(1),new Fraction(2),new Fraction(2),new Fraction(-1,5),new Fraction(4),new Fraction(1)};
			String [] chOperations = {"L1<-L1-L2","L2<-L2-L1","L3<-L3-2L1","L3<-L3-2L2","L3<-1/5L3","L2<-L2-4L3","L1<-L1+L3"};
			String [] chCommentaires = {"On va cherche Ã  ce que le premier 2 devienne un 1 et que tout ce qui tourne autour de lui soit un 0",
					"On cherche maintenant Ã  ce que le 1 en dessous du 1 en haut Ã  gauche devienne un 0 aussi. Comme Ã§a, le coin en haut Ã  gauche sera dÃ©jÃ  parfait.",
					"On va chercher Ã  ce que le 2 en bas Ã  gauche devienne un 0",
					"On continue en voulant que le 2 devienne un 0 pour ressembler de plus en plus Ã  une matrice inverse",
					"Toute la partie de gauche correspond Ã  une matrice inverse. On va donc changer le -5 en un 1 pour avoir notre diagonale de 1",
					"On va enlever le 4 pour obtenir un 0",
					"On va chercher Ã  enlever ce -1 pour obtenir la matice inverse.\n\nBravo! Vous avez rÃ©ussi et tout compris!"};
			//et on fait une boucle pour remplir tout dans la JTable
			for (int i = 0;i<=6;i++) {
				if (i == 4) {
					actuelle = chPanAffichageMatrices.getChMatrices().get(chPanAffichageMatrices.getChMatrices().size()-1);//on rÃ©cupÃ©re la matrice sur laquelle on travaille
					actuelleID = chPanAffichageMatrices.getChMatricesID().get(chPanAffichageMatrices.getChMatricesID().size()-1);//idem pour son identitÃ©
					
					matricePrincipale = new Matrice(actuelle.getTaille());//matrice sur laquelle on va effectuer les calculs
					matriceIdentite = new Matrice(actuelleID.getTaille());//matrice identitÃ© sur laquelle on va effectuer les calculs
					
					//on copie les matrices
					matricePrincipale.copie(actuelle);
					matriceIdentite.copie(actuelleID);
					
					matricePrincipale.modifyLine(chLigneA[i], chFraction[i]);
					matriceIdentite.modifyLine(chLigneA[i], chFraction[i]);
					chPanAffichageMatrices.ajoutMatrice(matricePrincipale, matriceIdentite,chOperations[i],chCommentaires[i]);
				}
				else {
					actuelle = chPanAffichageMatrices.getChMatrices().get(chPanAffichageMatrices.getChMatrices().size()-1);//on rÃ©cupÃ©re la matrice sur laquelle on travaille
					actuelleID = chPanAffichageMatrices.getChMatricesID().get(chPanAffichageMatrices.getChMatricesID().size()-1);//idem pour son identitÃ©
					
					matricePrincipale = new Matrice(actuelle.getTaille());//matrice sur laquelle on va effectuer les calculs
					matriceIdentite = new Matrice(actuelleID.getTaille());//matrice identitÃ© sur laquelle on va effectuer les calculs
					
					//on copie les matrices
					matricePrincipale.copie(actuelle);
					matriceIdentite.copie(actuelleID);
					
					matricePrincipale.modifyLine2(chLigneA[i],chSigne[i], chLigneB[i],chFraction[i]);
					matriceIdentite.modifyLine2(chLigneA[i],chSigne[i], chLigneB[i],chFraction[i]);
					chPanAffichageMatrices.ajoutMatrice(matricePrincipale, matriceIdentite,chOperations[i],chCommentaires[i]);
				}
			}
			chPanGauss.setAffichageMatrices(chPanAffichageMatrices);
			chPanelChoix.add(chPanGauss, "panel_gauss");
			chPanGauss.enregistreEcouteur(this);
			chPanelChoix.getCardLayout().show(chPanelChoix, "panel_gauss");
			panCom = chPanGauss.getPanelCommandes();//on rÃ©cupÃ©re le panel commande		
		}
		//Si l'utilisateur choisit le mode autonome oÃ¹ il faut tout tout seul
		if (pEvt.getActionCommand().equals(Data.VALIDER_AUTONOME)) {
				chPanTaille = new PanelTaille();
				chPanelChoix.add(chPanTaille, "panel_taille");
				chPanTaille.enregistreEcouteur(this);
				chPanelChoix.getCardLayout().show(chPanelChoix,"panel_taille");
		}
		if(pEvt.getActionCommand().equals(Data.VALIDER_PANEL_MATRICE)) {
			try {
				Matrice M1 = chPanMatrice.getMatriceSaisi();//crÃ©ation de la matrice
				Matrice M2 = Matrice.identite(M1.getTaille());//crÃ©ation de la matrice identitÃ©
				chPanGauss = new PanelGauss(M1);
				chPanAffichageMatrices.ajoutMatrice(M1, M2,"","");//au dÃ©part la chaine pour le calcul et celle pour le commentaire sont vides
				chPanGauss.setAffichageMatrices(chPanAffichageMatrices);
				chPanelChoix.add(chPanGauss, "panel_gauss");
				chPanGauss.enregistreEcouteur(this);
				chPanelChoix.getCardLayout().show(chPanelChoix, "panel_gauss");
				panCom = chPanGauss.getPanelCommandes();//on rÃ©cupÃ©re le panel commande
				etat = true;
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
		
		
		if(Arrays.asList(Data.FLECHES).contains(pEvt.getActionCommand())) { //si la commande de la source est une flÃ©che
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
			if(!operation[0].equals("")) {
				int ligneB;//index de la deuxième ligne choisie
				int ligneModifiee = getNumLigne(operation[0]); //on rÃ©cupÃ©re la ligne Ã  modifier
				
				//on rÃ©cupÃ¨re le calcul sous forme de chaine
				String chaine = new String();
				for (int i =0;i<operation.length;i++) {
					chaine+=operation[i];
				}
				
				//on rÃ©cupÃ¨re le commentaire
				String commentaire = panCom.getZoneCommentaire().getText();
				
				//on recupere la derniere matrice de chaque liste
				Matrice actuelle = chPanAffichageMatrices.getChMatrices().get(chPanAffichageMatrices.getChMatrices().size()-1);//on rÃ©cupÃ©re la matrice sur laquelle on travaille
				Matrice actuelleID = chPanAffichageMatrices.getChMatricesID().get(chPanAffichageMatrices.getChMatricesID().size()-1);//idem pour son identitÃ©
	
				
				Matrice matricePrincipale = new Matrice(actuelle.getTaille());//matrice sur laquelle on va effectuer les calculs
				Matrice matriceIdentite = new Matrice(actuelleID.getTaille());//matrice identitÃ© sur laquelle on va effectuer les calculs
				
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
				chPanAffichageMatrices.ajoutMatrice(matricePrincipale,matriceIdentite,chaine,commentaire); //on ajoute les matrices,l'opÃ©ration ÃƒÂ  la table
				
				//si on valide on reset l'operation en simulant un clic sur le bouton effacer
				panCom.getEffacer().doClick();
				
				//on change la matrice affichÃ©e dans le panelCommande
				panCom.refresh(matricePrincipale);
				panCom.getChChoixLigneMatrice().enregistreEcouteur(this); //on met le nouveau panel ÃƒÂ  l'Ã©coute du controleur
				
				//si l'utilisateur rÃ©ussit son calcul
				if(chPanAffichageMatrices.getChMatrices().get(chPanAffichageMatrices.getChMatrices().size()-1).isIdentite()) {
					//on lance un popup pour le fÃ©liciter
					JOptionPane.showMessageDialog(null, "FÃ©licitations !\nVous avez rÃ©ussi Ã  retrouver la matrice identitÃ© !\n Pensez Ã Â  exporter votre travail en PDF (Ctrl + P) pour ne pas en perdre une miette ;)\n\nVoici votre matrice inversÃ©e:\n"+matriceIdentite.toString(),"Bravo !",JOptionPane.INFORMATION_MESSAGE);
				}
			}
		}
		
		//si on clique sur le bouton constante
		if(pEvt.getActionCommand().equals(Data.CONSTANTE)) { //si la commande de la source est le bouton constante
			if( operation[1].equals(Data.FLECHES[0]) && (operation[2].equals("") || Arrays.asList(Data.LIGNES).contains(operation[2])) ) { //on  ne peut avoir une constante qu'avec la flÃ¨che <- et si une constante n'a pas dÃ©jÃƒÂ  Ã©tÃ© entrÃ©e auparavant
				//CrÃ©ation du popup de la demande de la constante uniquement avec la flÃ¨che <-
				String txt = JOptionPane.showInputDialog(null,"Veuillez rentrer une constante"); //chaine de caractere qu'on va rÃ©cupÃ©rer
				try {
					//Si on rentre une valeur pour la constante
					if (txt.equals("") || txt.equals("0")) {
						constante = new Fraction("1");//valeur par dÃ©faut Ã Â  1
					}
					else {
						constante = new Fraction(txt); //on convertit la chaine en fraction					
					}
					
					//on ajoute la constante au premier emplacement vide
					int labelVideConstante = panCom.getLabelVideConstante();//on rÃ©cupÃ¨re l'indice du premier label disponible pour une constante
					//si la constante est ÃƒÂ  son premier emplacement vide
					if (labelVideConstante == 2) {
						//la ligne qui suit doit Ãªtre la mÃªme que celle ÃƒÂ  modifier
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
			//uniquement si la premiÃ¨re ligne = ligne Ã Â  modifier et qu'aucun signe n'a Ã©tÃ© renseignÃ©
			if( operation[2].equals(operation[0]) && operation[3].equals("") ) {
				operation[3] = pEvt.getActionCommand();
				panCom.getLabel(3).setText(pEvt.getActionCommand());
			}
			else {
				JOptionPane.showMessageDialog(null, "Vous ne pouvez pas choisir un opÃ©rateur si vous n'avez pas Ã©crit un calcul de la bonne forme !\n","Erreur",JOptionPane.ERROR_MESSAGE);
			}
		}
		
		//PARTIE SUR LE MENU
				//Si l'utilisateur dÃ©cide d'aller au calcul prÃ©cÃ©dent
				if (pEvt.getActionCommand().equals(Data.TITRE_MATRICE_LISTE[0]))
				{
					//On vÃ©rifie si l'utilisateur est au niveau de la JTable ou avant
					if (etat != false) {
						//On rÃ©cupÃ¨re la liste des matrices
						List<Matrice> chMatrice = chPanAffichageMatrices.getChMatrices();
						//Si on n'est pas Ã  la toute premiÃ¨re ligne
						//on peut continuer, sinon, on ne fait rien.
						if (chMatrice.size() != 1) {							
							//On rÃ©cupÃ¨re chaque liste
							List<Matrice> chMatriceID = chPanAffichageMatrices.getChMatricesID();
							List<String> chLignes = chPanAffichageMatrices.getChLigneModif();
							List<String> chCommentaires= chPanAffichageMatrices.getChCommentaire();
		
							//on enlÃ¨ve le dernier Ã©lÃ¨ment pour chaque liste
						    chMatrice.remove(chMatrice.size()-1);
						    chMatriceID.remove(chMatrice.size());
						    chLignes.remove(chMatrice.size()-1);
						    chCommentaires.remove(chMatrice.size()-1);
		
						    //On actualise dans la JTable les changements 
							PanelAffichageMatrices.clearTableAt(chPanAffichageMatrices.getTableMatrices(),chMatrice.size()); 
							
							//On rÃ©cupÃ¨re la matrice sur laquelle on va dorÃ©navant travailler
							Matrice actuelle = chPanAffichageMatrices.getChMatrices().get(chMatrice.size()-1);
							
							//On l'actualise sur l'affichage
							chPanGauss.setAffichageMatrices(chPanAffichageMatrices);
							chPanelChoix.add(chPanGauss, "panel_gauss");										
							chPanelChoix.getCardLayout().show(chPanelChoix, "panel_gauss");
							
							//si on valide on reset l'operation en simulant un clic sur le bouton effacer
							panCom.getEffacer().doClick();
							
							//on change la matrice affichage dans le panelCommande
							panCom.refresh(actuelle);
							panCom.getChChoixLigneMatrice().enregistreEcouteur(this); //on met le nouveau panel Ã  l'Ã©coute du controleur
						}
					}
				}
				//si l'utilisateur veut zoomer
				if (pEvt.getActionCommand().equals(Data.TITRE_MATRICE_LISTE[1])){
					JTable table = chPanAffichageMatrices.getTableMatrices();
					int taillePolice = table.getFont().getSize();
					taillePolice+=2;
					
					for(int ligne = 0; ligne < table.getRowCount()-2;ligne++) {
						//on rÃ©cupÃ¨re la hauteur de la ligne pour l'agrandir par la suite
						int hauteur = table.getRowHeight();
						
						for (int colonne = 0; colonne < table.getColumnCount(); colonne++) {
							
							//on rÃ©cupÃ¨re la colonne qu'on va Ã©largir
							TableColumn laColonne = table.getColumnModel().getColumn(colonne);
							int largeur = laColonne.getWidth();
							
							largeur = largeur + 50;
							
							//on change la largeur de la colonne
							laColonne.setPreferredWidth(largeur);
						}
						
						table.setRowHeight(hauteur);
					}
					
					MultiLigneRenderer rend = new MultiLigneRenderer(taillePolice-8,taillePolice);
					
					for (int i=0; i< table.getColumnCount(); i++) {
						table.getColumnModel().getColumn(i).setCellRenderer(rend);
					}
				}
				if (pEvt.getActionCommand().equals(Data.TITRE_MATRICE_LISTE[2])){
					String texte = new String("Devra retrecir le texte");
					JOptionPane.showMessageDialog(null, texte, "Aide d'utilisation", JOptionPane.INFORMATION_MESSAGE);
				}
				
				//Permettre Ã  l'utilisateur de recommencer des calculs depuis le dÃ©but sur sa matrice
				if (pEvt.getActionCommand().equals(Data.TITRE_MATRICE_LISTE[3])){
					//On vÃ©rifie si l'utilisateur est au niveau de la JTable ou avant
					if (etat != false) {
						String texte = new String("Devra recommencer le calcul");
						JOptionPane.showMessageDialog(null, texte, "Aide d'utilisation", JOptionPane.INFORMATION_MESSAGE);
						//On efface ce qui est prÃ©sent dans la JTable
						PanelAffichageMatrices.clearTable(chPanAffichageMatrices.getTableMatrices());
						//On vide toutes les listes (qui contiennent la matrice, l'inverse, l'opÃ©ration et le commentaire)
						chPanAffichageMatrices.viderListe();
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
				
				//si l'utilisateur veut exporter son travail en PDF
				if(pEvt.getActionCommand().equals(Data.TITRE_MATRICE_LISTE[4])) {
					JFileChooser fichier = new JFileChooser(); //pour que l'utilisateur choisisse lÃ  oÃ¹ il veut crÃ©e son fichier
					fichier.setCurrentDirectory(new File(System.getProperty("user.home"))); //par dÃ©faut on se place dans le rÃ©pertoire utilisateur
					FileNameExtensionFilter filtre = new FileNameExtensionFilter(null, "*pdf");//on veut que le fichier soit uniquement au format pdf
					fichier.addChoosableFileFilter(filtre);
					
					//on regarde si l'utilisateur a bien choisi un fichier
					int resultat = fichier.showSaveDialog(null);
					
					if(resultat == JFileChooser.APPROVE_OPTION) {//si c'est bon
						try {
							new TablePDF().createPDF(fichier.getSelectedFile(), chPanAffichageMatrices);
						} catch (FileNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (DocumentException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					else if(resultat == JFileChooser.CANCEL_OPTION) {
						JOptionPane.showMessageDialog(null, "Erreur, mauvais fichier sÃ©lectionnÃ©","Erreur",JOptionPane.ERROR_MESSAGE);
					}
				}
				
				//si l'utilisateur souhaite revenir au menu principal
				if (pEvt.getActionCommand().equals(Data.TITRE_MATRICE[0])){
					String texte = new String("RETOUR AU MENU PRINCIPAL");
					JOptionPane.showMessageDialog(null, texte, "Aide d'utilisation", JOptionPane.INFORMATION_MESSAGE);
					}
				
				if (pEvt.getActionCommand().equals(Data.TITRE_MATRICE[2])){
					String texte = new String("Pour bien utiliser ce logiciel, il faut suivre les Ã©tapes suivantes. Toutes les Ã©tapes nÃ©cessitent d'appuyer sur un bouton 'valider' Ã  chaque fois.\n\n\nPremiÃ¨rement, choisir la taille de sa matrice. Celle-ci peut Ãªtre comprise entre 3 et 5 (Si on comprends le principe avec ces tailles-lÃ Â , on comprend le principe avec des tailles encore plus grandes.\n\n"
							+ "DeuxiÃ¨mement, remplir sa matrice. On peut remplir la matrice avec des entiers (positifs, nÃ©gatifs, nuls) et des fractions (positives,nÃ©gatives). Les fractions seront rÃ©duites automatiquement.\n\n"
							+ "TroisiÃ¨mement, effectuer des calculs sur sa matrice pour trouver la matrice inverse. Les calculs doivent s'Ã©crirent correctement. Les diffÃ©rents formes de calculs possibles sont les suivantes :\n\n"
							+ "Ligne_i â†” Ligne_j\n"
							+ "Ligne_i â†� lambda * ligne_i (Si lambda â‰ Â  0)\n"
							+ "Ligne_i â†� ligne_i + lambda * ligne_j\n\n"
							+ "Une matrice identitÃ© correspond Ã Â  : \n" + Matrice.identite(3).toString()
							+ "Bonne chance !");

					JOptionPane.showMessageDialog(null, texte, "Aide d'utilisation", JOptionPane.INFORMATION_MESSAGE);
				}
				//Fermer seulement la fenÃªtre actuelle
				if (pEvt.getActionCommand().equals(Data.TITRE_MATRICE[3])){
		             SwingUtilities.getWindowAncestor(chPanelChoix).dispose();
				}
				
				//Fermer entiÃ¨rement l'application
				if(pEvt.getActionCommand().equals(Data.TITRE_MATRICE[4])) {
					System.exit(0);
				}
		
	}
	
	//quand on clique sur une ligne
	@Override
	public void mouseClicked(MouseEvent e) {//si on clique sur une ligne
		int labelVide = panCom.getLabelVideLigne();

		if ( labelVide == 0 || operation[1].equals(Data.FLECHES[0]) ){ //si on choisit la ligne ÃƒÂ  modifier ou si on a utilisÃ© la flÃ¨che <-
			if (labelVide == 0) {
				panCom.getLabel(labelVide).setText(e.getComponent().getName()); //on peut afficher la ligne cliquÃ©e
				operation[labelVide] = e.getComponent().getName();//on peut ajouter la ligne ÃƒÂ  l'opÃ©ration
			}
			if( labelVide == 2 && operation[0].equals( e.getComponent().getName() ) ) {//si la premiÃ¨re ligne aprÃ¨s la flÃ¨che est la mÃƒÂªme que celle ÃƒÂ  modifier
				panCom.getLabel(labelVide).setText(e.getComponent().getName()); //on peut afficher la ligne cliquÃ©e
				operation[labelVide] = e.getComponent().getName();//on peut ajouter la ligne ÃƒÂ  l'opÃ©ration
			}
			if (labelVide == 5 && !( operation[0].equals( e.getComponent().getName() ) ) && !Arrays.asList(Data.LIGNES).contains(operation[3]) ) { //si la troisiÃ¨me ligne du calcul est diffÃ©rente de la ligne ÃƒÂ  modifier et que la deuxime ligne choisie ne prend pas de calcul
				panCom.getLabel(labelVide).setText(e.getComponent().getName()); //on peut afficher la ligne cliquÃ©e
				operation[labelVide] = e.getComponent().getName();//on peut ajouter la ligne ÃƒÂ  l'opÃ©ration
			}
			
			
		}
		else if (labelVide == 2 && operation[1].equals(Data.FLECHES[1])) {//s'il s'agit de la flÃ¨che <-> et qu'on choisit la ligne avec laquelle on va intervertir la premiÃ¨re
			if (!(e.getComponent().getName().equals(operation[0]))) { //si la ligne ÃƒÂ  Ã©changÃ©e est diffÃ©rente de la premiÃ¨re ligne choisie
				panCom.getLabel(labelVide).setText(e.getComponent().getName()); //on peut afficher la ligne cliquÃ©e
				operation[labelVide] = e.getComponent().getName();//on peut ajouter la ligne ÃƒÂ  l'opÃ©ration
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
	
	//retourne l'index correspondant ÃƒÂ  la ligne
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
