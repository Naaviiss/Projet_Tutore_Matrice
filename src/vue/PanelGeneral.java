package vue;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

import Controleur.Controleur;
import Modele.GenerePdf;
import Modele.Historique;
import Modele.LectureEcriture;

public class PanelGeneral extends JPanel implements ActionListener {
	
	String[] intitulesPanneaux = {"Fichier","Affichage", "Annuler", "Quitter", "?"};
	String[] itemsCreation = {"Nouveau Simplexe", "Charger Simplexe", "Enregistrer", "Enregistrer sous", "Mode Démo"};
	private CardLayout gestionnaireCartes;
	Controleur controleur;
	private Historique historique;
	private File fichierEnregistrement;
	private PanelGeneralSimplex panelSimplex;
	private PanelFichier panelFichier;
	
	/**
	 * Construit un objet PanelGeneral qui contient tous les autres panels
	 */
	
	
	public PanelGeneral(){
		
		//Instantiation de la chronologie
		historique=new Historique(); 
		
		//Layout : Empilement de tous les panels
		gestionnaireCartes=new CardLayout(2,2);
		this.setLayout(gestionnaireCartes);
		
		//Ajout des deux panels empilés
		
		panelFichier=new PanelFichier();
		this.add(panelFichier, intitulesPanneaux[0]);
		panelSimplex = new PanelGeneralSimplex(historique);
		controleur= new Controleur(panelFichier, this);
		panelSimplex.enregistreEcouteur(controleur);
		this.add(panelSimplex, intitulesPanneaux[1]);
		
		
	}
/**
 * Renvoie le panelGeneralSimplex du champ panelSimplex
 * @return
 */


	public PanelGeneralSimplex getPanelSimplex() {
		return panelSimplex;
	}


/**
 * Remplace le panelGeneralSimplex du champ panelSimplex par un nouveau panel passé en paramètre
 * @param panelSimplex
 */
	public void setPanelSimplex(PanelGeneralSimplex panelSimplex) {
		this.panelSimplex = panelSimplex;
	}


/**
 * Renvoie le panelFichier du champ PanelFichier
 * @return
 */
	public PanelFichier getPanelFichier() {
		return panelFichier;
	}

/**
 * Remplace le panel du champ panelFichier par le panel passé en paramètre
 * @param panelFichier
 */

	public void setPanelFichier(PanelFichier panelFichier) {
		this.panelFichier = panelFichier;
	}

	/**
	 * Renvoie l'Historique du champ historique
	 * @return
	 */
	public Historique getHistorique() {
		return historique;
	}
	
	/**
	 * Met Ã  jour le panel Indications dans le panelSimplexe, puis recharge le panelSimplexe.
	 * @param message
	 */

	public void miseAJourIndication(String message) {
		panelSimplex.setPanelIndi(message);
		panelSimplex.getPanelIndi().enregistreEcouteur(controleur);
		this.add(panelSimplex, intitulesPanneaux[1]);
		gestionnaireCartes.show(this, intitulesPanneaux[1]);
		
	}

	/**
	 * Remplace l'historique dans le champ historique par l'Historique passé en paramètre. Met ensuite à jour le panelSimplex
	 * @param historique
	 */
	public void setHistorique(Historique historique) {
		
		this.historique=historique;
		panelSimplex=new PanelGeneralSimplex(historique);
		panelSimplex.enregistreEcouteur(controleur);
		this.add(panelSimplex, intitulesPanneaux[1]);
		gestionnaireCartes.show(this, intitulesPanneaux[1]);
	}
	
	/**
	 * Recharge un nouveau panelFichier, et remet le controleur à l'écoute de ce panel
	 */
	
	public void miseAJourEnregistrement() {
		panelFichier=new PanelFichier();
		panelFichier.enregistreEcouteur(controleur);
		this.add(panelFichier, intitulesPanneaux[0]);
	}


	/**
	 * Permet d'être à l'écoute de tous les boutons de la fenêtre
	 */
	public void actionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		if(evt.getActionCommand() == "Nouveau Simplexe"){
			gestionnaireCartes.show(this, intitulesPanneaux[0]);
			panelFichier.getGestionnaireDeCartes().show(panelFichier, itemsCreation[0]);
			//Montre d'abord le panelCreation puis get son cardLayout pour afficher le bon panel fils de panelCreation
		}
		
		if(evt.getActionCommand() == "Charger Simplexe"){
			gestionnaireCartes.show(this, intitulesPanneaux[0]);
			panelFichier.getGestionnaireDeCartes().show(panelFichier, itemsCreation[1]);
			//Montre d'abord le panelCreation puis get son cardLayout pour afficher le bon panel fils de panelCreation
		}
	
		else if(evt.getActionCommand() == "Annuler") {
			historique.etapePrecedente();
			this.setHistorique(historique);
		}
		
		else if(evt.getActionCommand() == "Enregistrer") {
			if(fichierEnregistrement == null) {
				JFileChooser fichier = new JFileChooser(); //pour que l'utilisateur choisisse là où il veut crée son fichier
				fichier.setCurrentDirectory(new File(System.getProperty("user.home"))); //par défaut on se place dans le répertoire utilisateur
				FileNameExtensionFilter filtre = new FileNameExtensionFilter(null, "*ser");//on veut que le fichier soit uniquement au format pdf
				fichier.addChoosableFileFilter(filtre);
				
				//on regarde si l'utilisateur a bien choisi un fichier
				int resultat = fichier.showSaveDialog(null);
				
				if(resultat == JFileChooser.APPROVE_OPTION && fichier.getSelectedFile().getName().contains(".ser")) {//si c'est bon
					
					LectureEcriture.ecriture(fichier.getSelectedFile(), this.historique);
					fichierEnregistrement=fichier.getSelectedFile();

					
				}
				else if(resultat == JFileChooser.CANCEL_OPTION) {
					JOptionPane.showMessageDialog(null, "Erreur, votre fichier doit avoir pour extension .ser","Erreur",JOptionPane.ERROR_MESSAGE);
				}
				
			}
			
			else {
				LectureEcriture.ecriture(fichierEnregistrement, historique);
			}
			

			
		}
		
		else if(evt.getActionCommand() == "Enregistrer sous") {
		    
			JFileChooser fichier = new JFileChooser(); //pour que l'utilisateur choisisse là où il veut crée son fichier
			fichier.setCurrentDirectory(new File(System.getProperty("user.home"))); //par défaut on se place dans le répertoire utilisateur
			FileNameExtensionFilter filtre = new FileNameExtensionFilter(null, "*ser");//on veut que le fichier soit uniquement au format pdf
			fichier.addChoosableFileFilter(filtre);
			
			//on regarde si l'utilisateur a bien choisi un fichier
			int resultat = fichier.showSaveDialog(null);
			
			if(resultat == JFileChooser.APPROVE_OPTION && fichier.getSelectedFile().getName().contains(".ser")) {//si c'est bon
				
				LectureEcriture.ecriture(fichier.getSelectedFile(), this.historique);
				fichierEnregistrement=fichier.getSelectedFile();

				
			}
			else if(resultat == JFileChooser.CANCEL_OPTION) {
				JOptionPane.showMessageDialog(null, "Erreur, votre fichier doit avoir pour extension .ser","Erreur",JOptionPane.ERROR_MESSAGE);
			}
			
		}
		
		else if(evt.getActionCommand() == "Mode DÃ©mo") {
			new FenetreDemo();
		}
		
		else if(evt.getActionCommand() == "Affichage"){
			gestionnaireCartes.show(this, intitulesPanneaux[1]);
		}
		
		else if(evt.getActionCommand() == "PDF") {
			JFileChooser fichier = new JFileChooser(); //pour que l'utilisateur choisisse là où il veut crée son fichier
			fichier.setCurrentDirectory(new File(System.getProperty("user.home"))); //par défaut on se place dans le répertoire utilisateur
			FileNameExtensionFilter filtre = new FileNameExtensionFilter(null, "*pdf");//on veut que le fichier soit uniquement au format pdf
			fichier.addChoosableFileFilter(filtre);
			
			//on regarde si l'utilisateur a bien choisi un fichier
			int resultat = fichier.showSaveDialog(null);
			
			if(resultat == JFileChooser.APPROVE_OPTION) {//si c'est bon
				new GenerePdf(this.historique, fichier.getSelectedFile());
				
			}
			else if(resultat == JFileChooser.CANCEL_OPTION) {
				JOptionPane.showMessageDialog(null, "Erreur, mauvais fichier sélectionné","Erreur",JOptionPane.ERROR_MESSAGE);
			}
		}

		
		
		else if(evt.getActionCommand() == "Quitter"){
			
			int code= JOptionPane.showConfirmDialog(null, "Voulez vous vraiment quitter?","Arrêt du programme",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
			if(code== JOptionPane.OK_OPTION) {
				System.exit(code);
			} // Pop up avec confirmation du choix
			
		}
		
		else if(evt.getActionCommand() == "?") {
			JOptionPane.showMessageDialog(null, "Cette application vous permet de manipuler des Simplexes.\n"
					+ "Pour créer ou charger un simplexe, déroulez le menu Fichier et sélectionnez une option.\n"
					+ "Pour effectuer des échanges de variables, cliquez sur les boutons dans votre simplexe.\n"
					+ "Pour obtenir des indications quant à l'échange le plus judicieux, appuyez sur le bouton ? dans l'Affichage", "Aide", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	public File getFichierEnregistrement() {
		return fichierEnregistrement;
	}
	public void setFichierEnregistrement(File fichierEnregistrement) {
		this.fichierEnregistrement = fichierEnregistrement;
	}
}
