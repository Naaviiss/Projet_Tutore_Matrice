package vue;

import java.awt.BorderLayout;
import javax.swing.JPanel;

import Controleur.Controleur;
import Modele.Historique;

public class PanelGeneralSimplex extends JPanel{
	
	PanelSimplex panelSimp;
	PanelHistorique panelH;
	PanelIndications panelIndi;
	Historique historique;
	
	/**
	 * Construit un objet PanelGeneralSimplex à partir d'un Historique fourni en paramètre
	 * @param Historique historique
	 */
	public PanelGeneralSimplex(Historique historique) {
		this.setLayout(new BorderLayout(20,5));
		this.historique=historique;
		
		
		panelH = new PanelHistorique(historique);
		panelIndi = new PanelIndications();
		
		if(historique.getListeSimplexe().size() == 0) {
			panelSimp = new PanelSimplex();
			
		}
		else {
			panelSimp = new PanelSimplex(historique.getListeSimplexe().get(historique.getListeSimplexe().size()-1));
		}
		

		this.add(panelSimp,BorderLayout.CENTER);

		this.add(panelH, BorderLayout.EAST);
		
		this.add(panelIndi,BorderLayout.SOUTH);

		
	}

	/**
	 * Renvoie le champ panelSimp de this
	 * @return PanelSimplex panelSimp
	 */
	public PanelSimplex getPanelSimp() {
		return panelSimp;
	}
	
	/**
	 * Renvoie le champ panelIndi de this
	 * @return PanelIndication panelIndi
	 */
	public PanelIndications getPanelIndi() {
		return panelIndi;
	}

	/**
	 * Définit le champ panelIndi avec le PanelIndications fourni en paramètre
	 * @param PanelIndication panelIndi
	 */
	public void setPanelIndi(PanelIndications panelIndi) {
		this.panelIndi = panelIndi;
	}
	
	/**
	 * Crée un nouveau panelIndi,le remplit d'un enonce(String) donné en paramètre et l'ajoute au panelGeneralSimplex
	 * @param enonce
	 */
	public void setPanelIndi(String enonce) {
		this.remove(panelIndi);
		panelIndi=new PanelIndications(enonce);
		this.add(panelIndi, BorderLayout.SOUTH);
		
	}
	
	/**
	 * Définit le champ panelSimp de this avec le PanelSimplex donné en paramètre
	 * @param PanelSimplex panelSimp
	 */
	public void setPanelSimp(PanelSimplex panelSimp) {
		this.panelSimp = panelSimp;
	}


	/**
	 * Renvoie le champ panelH de this 
	 * @return PanelHistorique panelH
	 */
	public PanelHistorique getPanelH() {
		return panelH;
	}

	/**
	 * Définit le champ panelH de this avec le PanelHistorique donné en paramètre
	 * @param panelH
	 */
	public void setPanelH(PanelHistorique panelH) {
		this.panelH = panelH;
	}

	/**
	 * Renvoie le champ historique de this 
	 * @return Historique historique
	 */
	public Historique getHistorique() {
		return historique;
	}

	/**
	 * Définit le champ historique de this avec l'Historique donné en paramètre
	 * @param Historique historique
	 */
	public void setHistorique(Historique historique) {
		this.historique = historique;
	}

	/**
	 * Met le controleur donné en paramètre à l'écoute des champs panelSimp et panelIndi
	 * @param Controleur controleur
	 */
	public void enregistreEcouteur(Controleur controleur) {
		panelSimp.enregistreEcouteur(controleur);
		panelIndi.enregistreEcouteur(controleur);
	}
	
}

