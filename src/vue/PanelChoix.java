package vue;

import java.awt.CardLayout;
import javax.swing.JPanel;
import Controleur.Controleur;

public class PanelChoix extends JPanel{

	private static final long serialVersionUID = 1L;
	private PanelTaille panTaille;
	private PanelGauss panGauss;
	private PanelMatrice panMatrice;
	private CardLayout cardLayout;
	private Controleur chControleur;
	
	public PanelChoix() {
		cardLayout = new CardLayout();
		this.setLayout(cardLayout);
		chControleur = new Controleur(this);
		panTaille = new PanelTaille();
		this.add(panTaille, "panel_taille");
		panTaille.enregistreEcouteur(chControleur);
		cardLayout.show(this,"panel_taille");

	}

	public PanelTaille getPanTaille() {
		return panTaille;
	}

	public void setPanTaille(PanelTaille panTaille) {
		this.panTaille = panTaille;
	}

	public PanelGauss getPanGauss() {
		return panGauss;
	}

	public void setPanGauss(PanelGauss panGauss) {
		this.panGauss = panGauss;
	}

	public PanelMatrice getPanMatrice() {
		return panMatrice;
	}

	public void setPanMatrice(PanelMatrice panMatrice) {
		this.panMatrice = panMatrice;
	}

	public Controleur getChControleur() {
		return chControleur;
	}

	public void setChControleur(Controleur chControleur) {
		this.chControleur = chControleur;
	}

	public CardLayout getCardLayout1() {
		return cardLayout;
	}

	public void setCardLayout1(CardLayout cardLayout) {
		this.cardLayout = cardLayout;
	}
	
	public CardLayout getCardLayout() {
		return cardLayout;
	}

	public void setCardLayout(CardLayout cardLayout) {
		this.cardLayout = cardLayout;
	}

}
