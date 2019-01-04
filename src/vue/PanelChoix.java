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

//	public PanelAffichage getPanAffichage() {
//		return panAffichage;
//	}
//
//	public void setPanAffichage(PanelAffichage panAffichage) {
//		this.panAffichage = panAffichage;
//	}

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
		if(pEvt.getActionCommand().equals(Data.TITRE_MENU[2])) {
			System.exit(0);
		}
		else if (pEvt.getActionCommand().equals(Data.TITRE_MATRICE_LISTE[0])){
			String texte = new String("Devra revenir en arrière");
			JOptionPane.showMessageDialog(null, texte, "Aide d'utilisation", JOptionPane.INFORMATION_MESSAGE);
		}
		else if (pEvt.getActionCommand().equals(Data.TITRE_MATRICE_LISTE[1])){
			String texte = new String("Devra agrandir le texte");
			JOptionPane.showMessageDialog(null, texte, "Aide d'utilisation", JOptionPane.INFORMATION_MESSAGE);
		}
		else if (pEvt.getActionCommand().equals(Data.TITRE_MATRICE_LISTE[2])){
			String texte = new String("Devra retrecir le texte");
			JOptionPane.showMessageDialog(null, texte, "Aide d'utilisation", JOptionPane.INFORMATION_MESSAGE);
		}
		else if (pEvt.getActionCommand().equals(Data.TITRE_MATRICE_LISTE[3])){
			String texte = new String("Devra recommencer le calcul");
			JOptionPane.showMessageDialog(null, texte, "Aide d'utilisation", JOptionPane.INFORMATION_MESSAGE);
		}
		
		else if (pEvt.getActionCommand().equals(Data.TITRE_MATRICE[0])){
			String texte = new String("RETOUR AU MENU PRINCIPAL");
			JOptionPane.showMessageDialog(null, texte, "Aide d'utilisation", JOptionPane.INFORMATION_MESSAGE);
			
		}
		else if (pEvt.getActionCommand().equals(Data.TITRE_MATRICE[2])){
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
