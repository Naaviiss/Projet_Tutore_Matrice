package vue;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Controleur.Controleur;
import modele.Data;

public class PanelChoix extends JPanel implements ActionListener{
	private PanelAffichage panAffichage;
	private PanelTaille panTaille;
	private PanelGauss panGauss;
	private PanelMatrice panMatrice;
	private CardLayout cardLayout;
	private Controleur chControleur;
	
	public PanelChoix() {
		cardLayout = new CardLayout();
		this.setLayout(cardLayout);
		chControleur = new Controleur(this);
		panAffichage = new PanelAffichage();
		panTaille = new PanelTaille();
		panGauss = new PanelGauss();
		this.add(panAffichage,"panel_affichage");
		this.add(panTaille, "panel_taille");
		this.add(panGauss, "panel_gauss");

		panAffichage.enregistreEcouteur(chControleur);
		panTaille.enregistreEcouteur(chControleur);
		panGauss.enregistreEcouteur(chControleur);
		
		cardLayout.show(this,"panel_affichage");

	}

	public PanelAffichage getPanAffichage() {
		return panAffichage;
	}

	public void setPanAffichage(PanelAffichage panAffichage) {
		this.panAffichage = panAffichage;
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

		//L'action concernant l'outils matrice : avancer
		if (pEvt.getActionCommand().equals(Data.Titre_MATRICE_LISTE[0])){
			String texte = new String("Devra passer à la matrice suivante");
			JOptionPane.showMessageDialog(null, texte, "Aide d'utilisation", JOptionPane.INFORMATION_MESSAGE);
		}

		//L'action concernant l'outils matrice : reculer
		if (pEvt.getActionCommand().equals(Data.Titre_MATRICE_LISTE[1])){
			String texte = new String("Devra passer à la matrice précédente");
			JOptionPane.showMessageDialog(null, texte, "Aide d'utilisation", JOptionPane.INFORMATION_MESSAGE);
		}
		
		//L'action concernant l'outils matrice : reculer
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
	
	public void enregistreEcouteur(FenetreMere parControleur){
		bouton_simplex.addActionListener(parControleur);
		bouton_matrice.addActionListener(parControleur);
	}//enregistreEcouteur()
}
