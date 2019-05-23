package vue;

import java.awt.CardLayout;
import javax.swing.JPanel;

import Controleur.Controleur;

public class PanelFormulaire extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CardLayout gestionnaireDeCartes ;
	PanelContraintes panelC;
	PanelChoixNombresMonomesContraintes panelCMC;
	
	/**
	 * Construit un objet PanelFormulaire contenant un CardLayout et s'ajoute au PanelChoixNombresMonomesContraintes
	 */
	public PanelFormulaire() {
		/*LAYOUT*/
		gestionnaireDeCartes = new CardLayout(5,5);
		this.setLayout(gestionnaireDeCartes);
		
		panelCMC = new PanelChoixNombresMonomesContraintes();
		this.add(panelCMC, "ChoixMonomesContraintes");
	
	}

	/**
	 * Met le Controleur en paramètre à l'écoute de panelCMC
	 * @param Controleur controleur
	 */
	public void enregistreEcouteur(Controleur controleur) {
		panelCMC.enregistreEcouteur(controleur);
	}
	
	/**
	 * Appelle les méthodes viderFormulaire de panelC et panelCMC
	 */
	public void viderFormulaire() {
		panelC.viderFormulaire();
		panelCMC.viderFormulaire();
		gestionnaireDeCartes.show(this, "ChoixMonomesContraintes");
	}
	
	/**
	 * Met le Controleur en paramètre à l'écoute de panelC
	 * @param controleur
	 */
	public void enregistreEcouteurC(Controleur controleur) {
		panelC.enregistreEcouteur(controleur);
	}

	/**
	 * Renvoie le champ gestionnaireDeCartes de this
	 * @return CardLayout gestionnaireDeCartes
	 */
	public CardLayout getGestionnaireDeCartes() {
		return gestionnaireDeCartes;
	}

	/**
	 * Renvoie le champ panelC de this
	 * @return PanelContraintes panelC
	 */
	public PanelContraintes getPanelC() {
		return panelC;
	}
	
	/**
	 * Définit le champ panelC de this avec le PanelContraintes fourni en paramètre
	 * @param PanelContraintes panC
	 */
	public void setPanelC(PanelContraintes panC) {
		this.panelC = panC;
	}

	/**
	 * Renvoie le champ panelCMC de this
	 * @return PanelChoixNombresMonomesContraintes panelCMC
	 */
	public PanelChoixNombresMonomesContraintes getPanelCMC() {
		return panelCMC;
	}

}