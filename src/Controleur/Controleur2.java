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

import Modele.Data;
import Modele.ExceptCaseVide;
import Modele.ExceptEntreFraction;
import Modele.ExceptNegatifMalPlace;
import Modele.ExceptZeroDivision;
import Modele.Fraction;
import Modele.Matrice;
import Modele.TablePDF;

import java.awt.Font;

import vue.MultiLigneRenderer;
import vue.PanelAffichageMatrices;
import vue.PanelChoix;
import vue.PanelCommandes;
import vue.PanelGauss;
import vue.PanelMatrice;
import vue.PanelTaille;

public class Controleur2 implements ActionListener, MouseListener {
	/**
	 * Création d'un panel pour afficher la matrice
	 */
	private PanelMatrice chPanMatrice;
	/**
	 * Création d'un panel permettant de choisir la taille de la matrice
	 */
	private PanelTaille chPanTaille;
	/**
	 * Création d'un panel permettant de choisir le mode didacticiel ou le mode
	 * autonome
	 */
	private PanelChoix chPanelChoix;
	/**
	 * Création d'un panel affichant les étapes de la méthode de Gauss et les
	 * commandes pouvant Ãªtre réalisées
	 */
	private PanelGauss chPanGauss;
	/**
	 * Création d'un panel affichant seulement les étapes
	 */
	private PanelAffichageMatrices chPanAffichageMatrices;
	/**
	 * Création d'un tableau de String contenant le calcul de l'utilisateur
	 */
	private String[] operation = new String[6]; // tableau correspondant au calcul de l'utilisateur
	/**
	 * Création d'une fraction qui permettra de multiplier une ligne
	 */
	Fraction constante; // constante de l'utilisateur récupérée, par défaut, elle vaut 1
	/**
	 * Création d'un panel regroupant les commandes pouvant Ãªtre réalisées
	 */
	private PanelCommandes panCom; // panel commande
	/**
	 * Création d'un booléen pour savoir oÃ¹ l'utilisateur se trouve dans
	 * l'application Il est aussi utilisé pour revenir au tout début du calcul de la
	 * matrice inverse
	 */
	private Boolean etat = false; // etat pour savoir oÃ¹ l'utilisateur se trouve dans l'application
	// utilisé pour revenir au tout début du calcul de la matrice inverse
	/**
	 * Création d'un entier correspondant Ã  la taille de la police
	 */
	private int taillePolice; // correspond Ã  la taille de la police dans la JTable

	/**
	 * Créer un controleur
	 * 
	 * @param pPanChoix
	 */
	public Controleur2(PanelChoix pPanChoix) {

		// on met une constante par défaut au cas oÃ¹ l'utilisateur n'en renseigne pas
		constante = new Fraction(1);

		// on instancie le tableau de string correspondant au calcul de l'utilisateur
		for (int i = 0; i < operation.length; i++) {
			operation[i] = ""; // au départ, le tableau est vide
		}

		chPanelChoix = pPanChoix;
		// fenMere = (FenetreMere) SwingUtilities.windowForComponent(chPanelChoix);
		List<Matrice> chMatrices = new ArrayList<Matrice>();// list des matrices
		List<Matrice> chMatricesID = new ArrayList<Matrice>();// liste des matrices identités
		List<String> chLigneModif = new ArrayList<String>();// liste des opérations effectuées sous forme de chaine de
															// caractÃ¨res
		List<String> chCommentaires = new ArrayList<String>();// liste des commentaires sur les calculs
		chPanAffichageMatrices = new PanelAffichageMatrices(chMatrices, chMatricesID, chLigneModif, chCommentaires);// on
																													// créé
																													// le
																													// panel
																													// affichage
	}

	/**
	 * @param ActionEvent
	 *            pEvt : un évenement correspondant au choix de l'utilisateur Permet
	 *            de gérer les choix de l'utilisateur lors de l'utilisation du
	 *            programme au démarrage.
	 */
	@Override
	public void actionPerformed(ActionEvent pEvt) {
		if (pEvt.getActionCommand().equals(Data.CHOIX[1])) {// choix du programme matrice
			chPanelChoix.add(chPanelChoix, "panel_choix");
			chPanelChoix.getCardLayout().show(chPanelChoix, "panel_choix");
		}

		if (pEvt.getActionCommand().equals(Data.VALIDER_PANEL_TAILLE)) {// choix de la taille de la matrice
			chPanMatrice = new PanelMatrice(chPanTaille.getTaille());
			chPanelChoix.add(chPanMatrice, "panel_matrice");
			chPanMatrice.enregistreEcouteur(this);
			chPanelChoix.getCardLayout().show(chPanelChoix, "panel_matrice");
		}

		// Si l'utilisateur choisit le mode didacticiel
		if (pEvt.getActionCommand().equals(Data.VALIDER_DIDACTICIEL)) {
			// On crée une matrice de taille 3
			Matrice M1 = new Matrice(3);

			// On la remplit
			M1.setCase(0, 0, new Fraction(2));
			M1.setCase(0, 1, new Fraction(1));
			M1.setCase(0, 2, new Fraction(2));
			M1.setCase(1, 0, new Fraction(1));
			M1.setCase(1, 1, new Fraction(1));
			M1.setCase(1, 2, new Fraction(3));
			M1.setCase(2, 0, new Fraction(2));
			M1.setCase(2, 1, new Fraction(2));
			M1.setCase(2, 2, new Fraction(1));

			// création de la matrice identité
			Matrice M2 = Matrice.identite(M1.getTaille());

			chPanGauss = new PanelGauss(M1);

			// on recupere la derniere matrice de chaque liste
			Matrice actuelle;// on récupÃ¨re la matrice sur laquelle on travaille
			Matrice actuelleID;// idem pour son identité

			Matrice matricePrincipale;// matrice sur laquelle on va effectuer les calculs
			Matrice matriceIdentite;// matrice identité sur laquelle on va effectuer les calculs

			chPanAffichageMatrices.ajoutMatrice(M1, M2, "", "");

			// Création de toutes les opérations suivantes
			// que ce soit pour les lignes, le signe, la multiplication, l'opération
			// ou le commentaire
			int[] chLigneA = { 0, 1, 2, 2, 2, 1, 0 };
			int[] chLigneB = { 1, 0, 0, 1, 2, 2, 2 };
			String[] chSigne = { "-", "-", "-", "-", "", "-", "+" };
			Fraction[] chFraction = { new Fraction(1), new Fraction(1), new Fraction(2), new Fraction(2),
					new Fraction(-1, 5), new Fraction(4), new Fraction(1) };
			String[] chOperations = { "L1<-L1-L2", "L2<-L2-L1", "L3<-L3-2L1", "L3<-L3-2L2", "L3<-1/5L3", "L2<-L2-4L3",
					"L1<-L1+L3" };
			String[] chCommentaires = {
					"On va chercher Ã Â  ce que le premier 2 devienne un 1 et que tout ce qui tourne autour de lui soit un 0",
					"On cherche maintenant Ã Â  ce que le 1 en dessous du 1 en haut Ã  gauche devienne un 0 aussi. Comme Ã§a, le coin en haut Ã  gauche sera déjÃ  parfait.",
					"On va chercher Ã Â  ce que le 2 en bas Ã  gauche devienne un 0",
					"On continue en voulant que le 2 devienne un 0 pour ressembler de plus en plus Ã  une matrice inverse",
					"Toute la partie de gauche correspond Ã  une matrice inverse. On va donc changer le -5 en un 1 pour avoir notre diagonale de 1",
					"On va enlever le 4 pour obtenir un 0",
					"On va chercher Ã  enlever ce -1 pour obtenir la matice inverse.\n\nBravo! Vous avez réussi et tout compris!" };
			// et on fait une boucle pour remplir tout dans la JTable
			for (int i = 0; i <= 6; i++) {
				if (i == 4) {
					actuelle = chPanAffichageMatrices.getChMatrices()
							.get(chPanAffichageMatrices.getChMatrices().size() - 1);// on rÃƒÂ©cupÃƒÂ©re la matrice sur
																					// laquelle on travaille
					actuelleID = chPanAffichageMatrices.getChMatricesID()
							.get(chPanAffichageMatrices.getChMatricesID().size() - 1);// idem pour son identitÃƒÂ©

					matricePrincipale = new Matrice(actuelle.getTaille());// matrice sur laquelle on va effectuer les
																			// calculs
					matriceIdentite = new Matrice(actuelleID.getTaille());// matrice identitÃƒÂ© sur laquelle on va
																			// effectuer les calculs

					// on copie les matrices
					matricePrincipale.copie(actuelle);
					matriceIdentite.copie(actuelleID);

					matricePrincipale.modifyLine(chLigneA[i], chFraction[i]);
					matriceIdentite.modifyLine(chLigneA[i], chFraction[i]);
					chPanAffichageMatrices.ajoutMatrice(matricePrincipale, matriceIdentite, chOperations[i],
							chCommentaires[i]);
				} else {
					actuelle = chPanAffichageMatrices.getChMatrices()
							.get(chPanAffichageMatrices.getChMatrices().size() - 1);// on rÃƒÂ©cupÃƒÂ©re la matrice sur
																					// laquelle on travaille
					actuelleID = chPanAffichageMatrices.getChMatricesID()
							.get(chPanAffichageMatrices.getChMatricesID().size() - 1);// idem pour son identitÃƒÂ©

					matricePrincipale = new Matrice(actuelle.getTaille());// matrice sur laquelle on va effectuer les
																			// calculs
					matriceIdentite = new Matrice(actuelleID.getTaille());// matrice identité sur laquelle on va
																			// effectuer les calculs

					// on copie les matrices
					matricePrincipale.copie(actuelle);
					matriceIdentite.copie(actuelleID);

					matricePrincipale.modifyLine2(chLigneA[i], chSigne[i], chLigneB[i], chFraction[i]);
					matriceIdentite.modifyLine2(chLigneA[i], chSigne[i], chLigneB[i], chFraction[i]);
					chPanAffichageMatrices.ajoutMatrice(matricePrincipale, matriceIdentite, chOperations[i],
							chCommentaires[i]);
				}
			}
			chPanGauss.setAffichageMatrices(chPanAffichageMatrices);
			chPanelChoix.add(chPanGauss, "panel_gauss");
			chPanGauss.enregistreEcouteur(this);
			chPanelChoix.getCardLayout().show(chPanelChoix, "panel_gauss");
			panCom = chPanGauss.getPanelCommandes();// on récupÃ¨re le panel commande
		}
		// Si l'utilisateur choisit le mode autonome oÃ¹ il faut tout tout seul
		if (pEvt.getActionCommand().equals(Data.VALIDER_AUTONOME)) {
			chPanTaille = new PanelTaille();
			chPanelChoix.add(chPanTaille, "panel_taille");
			chPanTaille.enregistreEcouteur(this);
			chPanelChoix.getCardLayout().show(chPanelChoix, "panel_taille");
		}
		if (pEvt.getActionCommand().equals(Data.VALIDER_PANEL_MATRICE)) {
			try {
				Matrice M1 = chPanMatrice.getMatriceSaisi();// création de la matrice
				Matrice M2 = Matrice.identite(M1.getTaille());// création de la matrice identité
				chPanGauss = new PanelGauss(M1);
				chPanAffichageMatrices.ajoutMatrice(M1, M2, "", "");// au départ la chaine pour le calcul et celle pour
																	// le commentaire sont vides
				chPanGauss.setAffichageMatrices(chPanAffichageMatrices);
				chPanelChoix.add(chPanGauss, "panel_gauss");
				chPanGauss.enregistreEcouteur(this);
				chPanelChoix.getCardLayout().show(chPanelChoix, "panel_gauss");
				panCom = chPanGauss.getPanelCommandes();// on récupÃ¨re le panel commande
				etat = true;
			} catch (ExceptEntreFraction e) {
				JOptionPane.showMessageDialog(null,
						"Vous ne pouvez pas rentrer de lettres et de caractÃ¨res spéciaux dans une fraction !",
						"Erreur", JOptionPane.ERROR_MESSAGE);
			} catch (ExceptNegatifMalPlace e) {
				JOptionPane.showMessageDialog(null, "Erreur dans le placement du signe \"-\" !", "Erreur",
						JOptionPane.ERROR_MESSAGE);
			} catch (ExceptZeroDivision e) {
				JOptionPane.showMessageDialog(null, "Vous ne pouvez pas diviser un entier par 0 !", "Erreur",
						JOptionPane.ERROR_MESSAGE);
			} catch (ExceptCaseVide e) {
				JOptionPane.showMessageDialog(null, "Vous devez remplir toutes les cases de la matrice !", "Erreur",
						JOptionPane.ERROR_MESSAGE);
			}
		}

		if (Arrays.asList(Data.FLECHES).contains(pEvt.getActionCommand())) { // si la commande de la source est une
																				// flÃ¨che
			for (int i = 2; i < operation.length; i++) { // si on change de flÃ¨che la suite du calcul est réinitialisée
				panCom.getLabel(i).setText("");
				operation[i] = "";
			}
			panCom.getLabel(1).setText(pEvt.getActionCommand());
			operation[1] = pEvt.getActionCommand();
		}

		// si on clique sur le bouton effacer, on efface le calcul en cours
		if (pEvt.getActionCommand().equals(Data.EFFACER)) {
			JLabel calcul[] = panCom.getCalcul(); // on récupÃ¨re l'expression affichée par l'utilisateur
			for (int i = 0; i < calcul.length; i++) {
				calcul[i].setText("");
				operation[i] = "";
			}
			// on remet la zone de commentaire Ã  vide
			panCom.getZoneCommentaire().setText("");
		}

		// si on valide l'opération
		if (pEvt.getActionCommand().equals(Data.VALIDER_PANEL_COMMANDES)) {
			if (!operation[0].equals("")) {
				int ligneB;// index de la deuxiÃ¨me ligne choisie
				int ligneModifiee = getNumLigne(operation[0]); // on récupÃ¨re la ligne Ã  modifier

				// on récupÃ¨re le calcul sous forme de chaine
				String chaine = new String();
				for (int i = 0; i < operation.length; i++) {
					chaine += operation[i];
				}

				// on récupÃ¨re le commentaire
				String commentaire = panCom.getZoneCommentaire().getText();

				// on recupere la derniere matrice de chaque liste
				Matrice actuelle = chPanAffichageMatrices.getChMatrices()
						.get(chPanAffichageMatrices.getChMatrices().size() - 1);// on récupÃ¨re la matrice sur laquelle
																				// on travaille
				Matrice actuelleID = chPanAffichageMatrices.getChMatricesID()
						.get(chPanAffichageMatrices.getChMatricesID().size() - 1);// idem pour son identité

				Matrice matricePrincipale = new Matrice(actuelle.getTaille());// matrice sur laquelle on va effectuer
																				// les calculs
				Matrice matriceIdentite = new Matrice(actuelleID.getTaille());// matrice identité sur laquelle on va
																				// effectuer les calculs

				// on copie les matrices
				matricePrincipale.copie(actuelle);
				matriceIdentite.copie(actuelleID);

				// Si l'utilisateur veut intervertir 2 lignes
				if (operation[1].equals(Data.FLECHES[1])) {
					ligneB = getNumLigne(operation[2]);
					matricePrincipale.echange(ligneModifiee, ligneB);// on échange les lignes sur la matrice principale
					matriceIdentite.echange(ligneModifiee, ligneB);// on échange les lignes sur la matrice identité
				}
				// Si l'utilisateur veut effectuer un calcul sur une ligne
				else {
					// si c'est la deuxiÃ¨me ligne qui prend un calcul
					if (Arrays.asList(Data.LIGNES).contains(operation[5])) {
						ligneB = getNumLigne(operation[5]);
						if (operation[4].equals("")) {
							operation[4] = "1";
						}
						try {
							matricePrincipale.modifyLine2(ligneModifiee, operation[3], ligneB,
									new Fraction(operation[4]));// on fait l'opération sur la ligne de la matrice
																// principale
							matriceIdentite.modifyLine2(ligneModifiee, operation[3], ligneB,
									new Fraction(operation[4]));// on fait l'opération sur la ligne de la matrice
																// identité
						} catch (ExceptEntreFraction e) {
						} catch (ExceptNegatifMalPlace e) {
						} catch (ExceptZeroDivision e) {
						} catch (ExceptCaseVide e) {
						}

					}
					// si c'est la premiÃ¨re ligne qui prend un calcul
					else {
						try {
							matricePrincipale.modifyLine(ligneModifiee, new Fraction(operation[2]));// on fait
																									// l'opération sur
																									// la ligne de la
																									// matrice
																									// principale
							matriceIdentite.modifyLine(ligneModifiee, new Fraction(operation[2]));// on fait l'opération
																									// sur la ligne de
																									// la matrice
																									// identité
						} catch (ExceptEntreFraction e) {
						} catch (ExceptNegatifMalPlace e) {
						} catch (ExceptZeroDivision e) {
						} catch (ExceptCaseVide e) {
						}
					}
				}

				chPanAffichageMatrices.ajoutMatrice(matricePrincipale, matriceIdentite, chaine, commentaire); // on
																												// ajoute
																												// les
																												// matrices,l'opération
																												// Ã Â 
																												// la
																												// table

				// si on valide on reset l'operation en simulant un clic sur le bouton effacer
				panCom.getEffacer().doClick();

				// on change la matrice affichée dans le panelCommande
				panCom.refresh(matricePrincipale);
				panCom.getChChoixLigneMatrice().enregistreEcouteur(this); // on met le nouveau panel Ã Â  l'écoute du
																			// controleur

				// si l'utilisateur réussit son calcul
				if (chPanAffichageMatrices.getChMatrices().get(chPanAffichageMatrices.getChMatrices().size() - 1)
						.isIdentite()) {
					// on lance un popup pour le féliciter
					JOptionPane.showMessageDialog(null,
							"Félicitations !\nVous avez réussi Ã  retrouver la matrice identité !\n Pensez Ã Â  exporter votre travail en PDF (Ctrl + P) pour ne pas en perdre une miette ;)\n\nVoici votre matrice inversée:\n"
									+ matriceIdentite.toString(),
							"Bravo !", JOptionPane.INFORMATION_MESSAGE);
				}

				// si le calcul prend du temps, il doit y avoir un problÃ¨me
				if (chPanAffichageMatrices.getChMatrices().size() == 20) {
					// on lance un popup pour le féliciter
					JOptionPane.showMessageDialog(null,
							"Tu as fait beaucoup de calculs dis moi... Tu ne voudrais pas remettre en question ton travail ? Parce que Ã§a devient long lÃ  :o \n Peut-Ãªtre que l'équipe de développement n'a pas eu le temps de vérifier que ta matrice était inversible :p",
							"Attention !", JOptionPane.WARNING_MESSAGE);
				}
			}
		}

		// si on clique sur le bouton constante
		if (pEvt.getActionCommand().equals(Data.CONSTANTE)) { // si la commande de la source est le bouton constante
			if (operation[1].equals(Data.FLECHES[0])
					&& (operation[2].equals("") || Arrays.asList(Data.LIGNES).contains(operation[2]))) { // on ne peut
																											// avoir une
																											// constante
																											// qu'avec
																											// la
																											// flÃ¨che
																											// <- et si
																											// une
																											// constante
																											// n'a pas
																											// déjÃ Â 
																											// été
																											// entrée
																											// auparavant
				// CrÃƒÂ©ation du popup de la demande de la constante uniquement avec la flÃ¨che
				// <-
				String txt = JOptionPane.showInputDialog(null, "Veuillez rentrer une constante"); // chaine de caractere
																									// qu'on va
																									// récupérer
				try {
					// Si on rentre une valeur pour la constante
					if (txt.equals("") || txt.equals("0")) {
						constante = new Fraction("1");// valeur par défaut Ã Â  1
					} else {
						constante = new Fraction(txt); // on convertit la chaine en fraction
					}

					// on ajoute la constante au premier emplacement vide
					int labelVideConstante = panCom.getLabelVideConstante();// on récupÃ¨re l'indice du premier label
																			// disponible pour une constante
					// si la constante est Ã  son premier emplacement vide
					if (labelVideConstante == 2) {
						// la ligne qui suit doit Ãªtre la mÃªme que celle Ã Â  modifier
						operation[3] = operation[0];
						panCom.getLabel(3).setText(operation[3]);
					}
					operation[labelVideConstante] = constante.toString();

					if (!constante.toString().equals("1"))
						panCom.getLabel(labelVideConstante).setText(constante.toString());
				} catch (ExceptEntreFraction e) { // si on lÃƒÂ¨ve une exception
					JOptionPane.showMessageDialog(null,
							"Vous ne pouvez pas rentrer de lettres et de caractÃ¨res spéciaux dans une fraction !",
							"Erreur", JOptionPane.ERROR_MESSAGE);
					panCom.getEffacer().doClick();// pour l'instant, le calcul est reset
				} catch (ExceptNegatifMalPlace e) {
					JOptionPane.showMessageDialog(null, "Erreur dans le placement du signe \"-\" !", "Erreur",
							JOptionPane.ERROR_MESSAGE);
					panCom.getEffacer().doClick();// pour l'instant, le calcul est reset
				} catch (ExceptZeroDivision e) {
					JOptionPane.showMessageDialog(null, "Vous ne pouvez pas diviser un entier par 0 !", "Erreur",
							JOptionPane.ERROR_MESSAGE);
					panCom.getEffacer().doClick();// pour l'instant, le calcul est reset
				} catch (ExceptCaseVide e) {
					JOptionPane.showMessageDialog(null, "Vous devez remplir toutes les cases de la matrice !", "Erreur",
							JOptionPane.ERROR_MESSAGE);
					panCom.getEffacer().doClick();// pour l'instant, le calcul est reset
				}
			}
		}

		if (Arrays.asList(Data.OPERATIONS).contains(pEvt.getActionCommand())) { // si la source de la commande est un
																				// opérateur
			// uniquement si la premiÃ¨re ligne = ligne Ã  modifier et qu'aucun signe n'a
			// été renseigné
			if (operation[2].equals(operation[0]) && operation[3].equals("")) {
				operation[3] = pEvt.getActionCommand();
				panCom.getLabel(3).setText(pEvt.getActionCommand());
			} else {
				JOptionPane.showMessageDialog(null,
						"Vous ne pouvez pas choisir un opérateur si vous n'avez pas écrit un calcul sous la bonne forme !\n",
						"Erreur", JOptionPane.ERROR_MESSAGE);
			}
		}

		// PARTIE SUR LE MENU
		// Si l'utilisateur décide d'aller au calcul précédent
		if (pEvt.getActionCommand().equals(Data.TITRE_MATRICE_LISTE[0])) {
			// On vérifie si l'utilisateur est au niveau de la JTable ou avant
			if (etat != false) {
				// On récupÃ¨re la liste des matrices
				List<Matrice> chMatrice = chPanAffichageMatrices.getChMatrices();
				// Si on n'est pas Ã  la toute premiÃ¨re ligne
				// on peut continuer, sinon, on ne fait rien.
				if (chMatrice.size() != 1) {
					// On récupÃ¨re chaque liste
					List<Matrice> chMatriceID = chPanAffichageMatrices.getChMatricesID();
					List<String> chLignes = chPanAffichageMatrices.getChLigneModif();
					List<String> chCommentaires = chPanAffichageMatrices.getChCommentaire();

					// on enlÃ¨ve le dernier élément pour chaque liste
					chMatrice.remove(chMatrice.size() - 1);
					chMatriceID.remove(chMatrice.size());
					chLignes.remove(chMatrice.size() - 1);
					chCommentaires.remove(chMatrice.size() - 1);

					// On actualise dans la JTable les changements
					PanelAffichageMatrices.clearTableAt(chPanAffichageMatrices.getTableMatrices(), chMatrice.size());

					// On récupÃ¨re la matrice sur laquelle on va dorénavant travailler
					Matrice actuelle = chPanAffichageMatrices.getChMatrices().get(chMatrice.size() - 1);

					// On l'actualise sur l'affichage
					chPanGauss.setAffichageMatrices(chPanAffichageMatrices);
					chPanelChoix.add(chPanGauss, "panel_gauss");
					chPanelChoix.getCardLayout().show(chPanelChoix, "panel_gauss");

					// si on valide on reset l'operation en simulant un clic sur le bouton effacer
					panCom.getEffacer().doClick();

					// on change la matrice affichage dans le panelCommande
					panCom.refresh(actuelle);
					panCom.getChChoixLigneMatrice().enregistreEcouteur(this); // on met le nouveau panel ÃƒÂ  l'écoute
																				// du controleur
				}
			}
		}

		// si l'utilisateur veut zoomer
		if (pEvt.getActionCommand().equals(Data.TITRE_MATRICE_LISTE[1])) {
			// On vérifie si l'utilisateur est au niveau de la JTable ou avant
			if (etat != false) {
				JTable table = chPanAffichageMatrices.getTableMatrices();
				taillePolice = table.getFont().getSize();
				// On bloque Ã  un certain moment sinon c'est beaucoup trop grand
				if (taillePolice < 20) {
					taillePolice += 2;
					for (int ligne = 0; ligne < table.getRowCount() - 2; ligne++) {
						// on récupÃ¨re la hauteur de la ligne pour l'agrandir par la suite
						int hauteur = table.getRowHeight();

						for (int colonne = 0; colonne < table.getColumnCount(); colonne++) {

							// on récupÃ¨re la colonne qu'on va élargir
							TableColumn laColonne = table.getColumnModel().getColumn(colonne);
							int largeur = laColonne.getWidth();

							largeur = largeur + 50;

							// on change la largeur de la colonne
							laColonne.setPreferredWidth(largeur);
						}

						table.setRowHeight(hauteur);
					}
					MultiLigneRenderer rend = chPanAffichageMatrices.getRenderer();
					rend = new MultiLigneRenderer(taillePolice - 8, taillePolice);

					table.setFont(new Font("Serif", Font.BOLD, taillePolice));

					for (int i = 0; i < table.getColumnCount(); i++) {
						table.getColumnModel().getColumn(i).setCellRenderer(rend);
					}
				}
			}
		}

		// si l'utilisateur veut dézoomer
		if (pEvt.getActionCommand().equals(Data.TITRE_MATRICE_LISTE[2])) {
			// On vérifie si l'utilisateur est au niveau de la JTable ou avant
			if (etat != false) {
				JTable table = chPanAffichageMatrices.getTableMatrices();
				taillePolice = table.getFont().getSize();
				// On bloque Ã  un certain moment sinon ce n'est plus lisible, trop petit
				if (taillePolice > 12) {
					taillePolice -= 2;
					for (int ligne = 0; ligne < table.getRowCount() - 2; ligne++) {
						// on récupÃ¨re la hauteur de la ligne pour la rétrécir par la suite
						int hauteur = table.getRowHeight();

						for (int colonne = 0; colonne < table.getColumnCount(); colonne++) {

							// on récupÃ¨re la colonne qu'on va élargir
							TableColumn laColonne = table.getColumnModel().getColumn(colonne);
							int largeur = laColonne.getWidth();

							largeur = largeur - 50;

							// on change la largeur de la colonne
							laColonne.setPreferredWidth(largeur);
						}
						table.setRowHeight(hauteur);
					}
					MultiLigneRenderer rend = chPanAffichageMatrices.getRenderer();
					rend = new MultiLigneRenderer(taillePolice - 8, taillePolice);

					table.setFont(new Font("Serif", Font.BOLD, taillePolice));

					for (int i = 0; i < table.getColumnCount(); i++) {
						table.getColumnModel().getColumn(i).setCellRenderer(rend);
					}
				}
			}
		}

		// Permettre Ã Â  l'utilisateur de recommencer des calculs depuis le début sur
		// sa matrice
		if (pEvt.getActionCommand().equals(Data.TITRE_MATRICE_LISTE[3])) {
			// On vérifie si l'utilisateur est au niveau de la JTable ou avant
			if (etat != false) {
				// On efface ce qui est présent dans la JTable
				PanelAffichageMatrices.clearTable(chPanAffichageMatrices.getTableMatrices());
				// On vide toutes les listes (qui contiennent la matrice, l'inverse, l'opération
				// et le commentaire)
				chPanAffichageMatrices.viderListe();
				// s'il est au niveau de la JTable, on remet tout comme avant.
				try {
					Matrice M1 = chPanMatrice.getMatriceSaisi();// création de la matrice
					Matrice M2 = Matrice.identite(M1.getTaille());// création de la matrice identité
					chPanGauss = new PanelGauss(M1);
					chPanAffichageMatrices.ajoutMatrice(M1, M2, "", "");// au départ la chaine pour le calcul et celle
																		// pour le commentaire sont vides
					chPanGauss.setAffichageMatrices(chPanAffichageMatrices);
					chPanelChoix.add(chPanGauss, "panel_gauss");
					chPanGauss.enregistreEcouteur(this);
					chPanelChoix.getCardLayout().show(chPanelChoix, "panel_gauss");
					panCom = chPanGauss.getPanelCommandes();// on récupÃ¨re le panel commande
				} catch (ExceptEntreFraction e) {
					JOptionPane.showMessageDialog(null,
							"Vous ne pouvez pas rentrer de lettres et de caractÃ¨res spéciaux dans une fraction !",
							"Erreur", JOptionPane.ERROR_MESSAGE);
				} catch (ExceptNegatifMalPlace e) {
					JOptionPane.showMessageDialog(null, "Erreur dans le placement du signe \"-\" !", "Erreur",
							JOptionPane.ERROR_MESSAGE);
				} catch (ExceptZeroDivision e) {
					JOptionPane.showMessageDialog(null, "Vous ne pouvez pas diviser un entier par 0 !", "Erreur",
							JOptionPane.ERROR_MESSAGE);
				} catch (ExceptCaseVide e) {
					JOptionPane.showMessageDialog(null, "Vous devez remplir toutes les cases de la matrice !", "Erreur",
							JOptionPane.ERROR_MESSAGE);
				}
			}
			// Sinon, il ne se passe rien.
		}

		// si l'utilisateur veut exporter son travail en PDF
		if (pEvt.getActionCommand().equals(Data.TITRE_MATRICE_LISTE[4])) {
			JFileChooser fichier = new JFileChooser(); // pour que l'utilisateur choisisse lÃ Â  oÃ¹ il veut créer son
														// fichier
			fichier.setCurrentDirectory(new File(System.getProperty("user.home"))); // par défaut on se place dans le
																					// répertoire utilisateur
			FileNameExtensionFilter filtre = new FileNameExtensionFilter(null, "*pdf");// on veut que le fichier soit
																						// uniquement au format pdf
			fichier.addChoosableFileFilter(filtre);

			// on regarde si l'utilisateur a bien choisi un fichier
			int resultat = fichier.showSaveDialog(null);

			if (resultat == JFileChooser.APPROVE_OPTION) {// si c'est bon
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
			} else if (resultat == JFileChooser.CANCEL_OPTION) {
				JOptionPane.showMessageDialog(null, "Erreur, mauvais fichier sélectionné", "Erreur",
						JOptionPane.ERROR_MESSAGE);
			}
		}

		// si l'utilisateur souhaite revenir au menu principal
		if (pEvt.getActionCommand().equals(Data.TITRE_MATRICE[0])) {
			// On efface ce qui est présent dans la JTable
			PanelAffichageMatrices.clearTable(chPanAffichageMatrices.getTableMatrices());
			// On vide toutes les listes (qui contiennent la matrice, l'inverse, l'opération
			// et le commentaire)
			chPanAffichageMatrices.viderListe();
			chPanelChoix.getCardLayout().first(chPanelChoix);
		}

		if (pEvt.getActionCommand().equals(Data.TITRE_MATRICE[2])) {
			String texte = new String(
					"Pour bien utiliser ce logiciel, il faut suivre les étapes suivantes. Toutes les étapes nécessitent d'appuyer sur un bouton 'valider' Ã Â  chaque fois.\n\n\nPremiÃ¨rement, choisir la taille de sa matrice. Celle-ci peut Ãªtre comprise entre 3 et 5 (Si on comprends le principe avec ces tailles-lÃ Â Ã Â , on comprend le principe avec des tailles encore plus grandes.\n\n"
							+ "DeuxiÃ¨mement, remplir sa matrice. On peut remplir la matrice avec des entiers (positifs, négatifs, nuls) et des fractions (positives,négatives). Les fractions seront réduites automatiquement.\n\n"
							+ "TroisiÃ¨mement, effectuer des calculs sur sa matrice pour trouver la matrice inverse. Les calculs doivent s'écrirent correctement. Les différents formes de calculs possibles sont les suivantes :\n\n"
							+ "Ligne_i â†” Ligne_j\n" + "Ligne_i â†� lambda * ligne_i (Si lambda â‰  0)\n"
							+ "Ligne_i â†� ligne_i + lambda * ligne_j\n\n" + "Une matrice identité correspond Ã Â  : \n"
							+ Matrice.identite(3).toString() + "Bonne chance !");

			JOptionPane.showMessageDialog(null, texte, "Aide d'utilisation", JOptionPane.INFORMATION_MESSAGE);
		}
		// Fermer seulement la fenÃªtre actuelle
		if (pEvt.getActionCommand().equals(Data.TITRE_MATRICE[3])) {
			SwingUtilities.getWindowAncestor(chPanelChoix).dispose();
		}

		// Fermer entiÃ¨rement l'application
		if (pEvt.getActionCommand().equals(Data.TITRE_MATRICE[4])) {
			System.exit(0);
		}
	}

	// quand on clique sur une ligne
	@Override
	public void mouseClicked(MouseEvent e) {// si on clique sur une ligne
		int labelVide = panCom.getLabelVideLigne();

		if (labelVide == 0 || operation[1].equals(Data.FLECHES[0])) { // si on choisit la ligne Ã Â  modifier ou si on a
																		// utilisé la flÃ¨che <-
			if (labelVide == 0) {
				panCom.getLabel(labelVide).setText(e.getComponent().getName()); // on peut afficher la ligne cliquée
				operation[labelVide] = e.getComponent().getName();// on peut ajouter la ligne Ã Â  l'opération
			}
			if (labelVide == 2 && operation[0].equals(e.getComponent().getName())) {// si la premiÃ¨re ligne aprÃ¨s la
																					// flÃ¨che est la mÃªme que celle Ã 
																					// modifier
				panCom.getLabel(labelVide).setText(e.getComponent().getName()); // on peut afficher la ligne cliquée
				operation[labelVide] = e.getComponent().getName();// on peut ajouter la ligne Ã Â  l'opération
			}
			if (labelVide == 5 && !(operation[0].equals(e.getComponent().getName()))
					&& !Arrays.asList(Data.LIGNES).contains(operation[3])) { // si la troisiÃ¨me ligne du calcul est
																				// différente de la ligne Ã Â  modifier
																				// et que la deuxiÃ¨me ligne choisie ne
																				// prend pas de calcul
				panCom.getLabel(labelVide).setText(e.getComponent().getName()); // on peut afficher la ligne cliquée
				operation[labelVide] = e.getComponent().getName();// on peut ajouter la ligne Ã  l'opération
			}

		} else if (labelVide == 2 && operation[1].equals(Data.FLECHES[1])) {// s'il s'agit de la flÃ¨che <-> et qu'on
																			// choisit la ligne avec laquelle on va
																			// intervertir la premiÃ¨re
			if (!(e.getComponent().getName().equals(operation[0]))) { // si la ligne Ã  échanger est différente de la
																		// premiÃ¨re ligne choisie
				panCom.getLabel(labelVide).setText(e.getComponent().getName()); // on peut afficher la ligne cliquée
				operation[labelVide] = e.getComponent().getName();// on peut ajouter la ligne Ã  l'opération
			}
		}
		// ces if permettent d'éviter le calculs comme L2<-> L2 L3 ou L1 <- L2 L3 L2,
		// etc.

	}

	/**
	 * Met le composant survolé en rouge lorsque le survole avec la souris
	 * 
	 * @param MouseEvent
	 *            e
	 */
	// pour le over sur une ligne
	@Override
	public void mouseEntered(MouseEvent e) {
		e.getComponent().setForeground(Color.RED);
	}

	/**
	 * Met le composant en noir lorsque l'on ne le survole plus
	 * 
	 * @param MouseEvent
	 *            e
	 */
	// quand on quitte le over
	@Override
	public void mouseExited(MouseEvent e) {
		e.getComponent().setForeground(Color.BLACK);
	}

	/**
	 * RécupÃ¨re un evÃ¨nement lorsque l'on clique avec la souris
	 * 
	 * @param MouseEvent
	 *            e
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	/**
	 * RécupÃ¨re un évÃ¨nement lorsque l'on lÃ¢che le clique de la souris
	 * 
	 * @param MouseEvent
	 *            e
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	/**
	 * Méthode permettant de retourner l'index correspondant Ã  la ligne
	 * 
	 * @param String
	 *            ligne
	 */
	// retourne l'index correspondant Ã  la ligne
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