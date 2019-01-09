package vue;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Controleur.Controleur;
import modele.Data;

public class PanelChoix extends JPanel implements ActionListener{

	private static final long serialVersionUID = 1L;
	//	private PanelAffichage panAffichage;
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
	public void actionPerformed(ActionEvent pEvt){

		if (pEvt.getActionCommand().equals(Data.TITRE_MATRICE_LISTE[0])){
			String texte = new String("Devra passer à la matrice suivante");
			JOptionPane.showMessageDialog(null, texte, "Aide d'utilisation", JOptionPane.INFORMATION_MESSAGE);
		}
		
		
		if (pEvt.getActionCommand().equals(Data.TITRE_MATRICE_LISTE[1])){
			String texte = new String("Devra passer à la matrice précédente");
			JOptionPane.showMessageDialog(null, texte, "Aide d'utilisation", JOptionPane.INFORMATION_MESSAGE);
		}
		
		
		if (pEvt.getActionCommand().equals(Data.TITRE_MATRICE[2])){
			String texte = new String("Texte pour comprendre matrice");
			JOptionPane.showMessageDialog(null, texte, "Aide d'utilisation", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	public CardLayout getCardLayout() {
		return cardLayout;
	}

	public void setCardLayout(CardLayout cardLayout) {
		this.cardLayout = cardLayout;
	}

}
