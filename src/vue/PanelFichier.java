package vue;

import java.awt.CardLayout;

import javax.swing.JPanel;

import Controleur.Controleur;

public class PanelFichier extends JPanel{
	private PanelFormulaire panelFormulaire;
	private PanelChargerSimplexe panelCharger;
	private CardLayout gestionnaireDeCartes;

	/**
	 * Construit un objet PanelFichier permettant à l'utilisateur de rentrer un fichier à charger en tant que Simplexe
	 */
	public PanelFichier(){
		
		//Création des panel fils
		
		panelFormulaire =new PanelFormulaire();
		panelCharger=new PanelChargerSimplexe();
		
		//Layout
		
		gestionnaireDeCartes = new CardLayout(3,3);
		this.setLayout(gestionnaireDeCartes);
		
		//Ajout des Layout
		
		this.add(panelFormulaire, "Nouveau Simplexe");
		this.add(panelCharger, "Charger Simplexe");

	}
	

	/**
	 * Renvoie le champ gestionnaireDeCartes de this
	 * @return CardLayout gestionnaireDeCartes
	 */
	public CardLayout getGestionnaireDeCartes() {
		return gestionnaireDeCartes;
	}
	
	//EnregistreEcouteur qui appelle les enregistreEcouteurs des panelFils
	/**
	 * permet de mettre le controleur en paramètre à l'écoute de tous les panels fils
	 * @param parControleur:Controleur
	 */
	public void enregistreEcouteur(Controleur parControleur){
		panelFormulaire.enregistreEcouteur(parControleur);
		panelCharger.enregistreEcouteur(parControleur);
		
	}


	/**
	 * Renvoie le champ panelformulaire de this
	 * @return PanelFormulaire panelFormulaire
	 */
	public PanelFormulaire getPanelFormulaire() {
		return panelFormulaire;
	}


	/**
	 * Renvoie le champ panelCharger de this
	 * @return PanelChargerSimplexe panelCharger
	 */
	public PanelChargerSimplexe getPanelCharger() {
		return panelCharger;
	}


	/**
	 * Définit le champ panelCharger de this avec le PanelChargerSimplexe fourni en paramètre
	 * @param PanelChargerSimplexe panelCharger
	 */
	public void setPanelCharger(PanelChargerSimplexe panelCharger) {
		this.panelCharger = panelCharger;
	}


	/**
	 * Défini le champs panelFormulaire de this avec le PanelFormulaire fourni en paramètre
	 * @param panelFormulaire
	 */
	public void setPanelFormulaire(PanelFormulaire panelFormulaire) {
		this.panelFormulaire = panelFormulaire;
	}
}
