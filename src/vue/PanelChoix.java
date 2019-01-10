package vue;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import Controleur.Controleur;
import modele.Data;
import modele.Matrice;

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

		if(pEvt.getActionCommand().equals(Data.TITRE_MENU[2])) {
			System.exit(0);
		}
		if (pEvt.getActionCommand().equals(Data.TITRE_MATRICE_LISTE[0])){
			String texte = new String("Devra revenir en arrière");
			JOptionPane.showMessageDialog(null, texte, "Aide d'utilisation", JOptionPane.INFORMATION_MESSAGE);
		}
		if (pEvt.getActionCommand().equals(Data.TITRE_MATRICE_LISTE[1])){
			String texte = new String("Devra agrandir le texte");
			JOptionPane.showMessageDialog(null, texte, "Aide d'utilisation", JOptionPane.INFORMATION_MESSAGE);
		}
		if (pEvt.getActionCommand().equals(Data.TITRE_MATRICE_LISTE[2])){
			String texte = new String("Devra retrecir le texte");
			JOptionPane.showMessageDialog(null, texte, "Aide d'utilisation", JOptionPane.INFORMATION_MESSAGE);
		}
		if (pEvt.getActionCommand().equals(Data.TITRE_MATRICE_LISTE[3])){
			String texte = new String("Devra recommencer le calcul");
			JOptionPane.showMessageDialog(null, texte, "Aide d'utilisation", JOptionPane.INFORMATION_MESSAGE);
		}
		
		if (pEvt.getActionCommand().equals(Data.TITRE_MATRICE[0])){
			String texte = new String("RETOUR AU MENU PRINCIPAL");
			JOptionPane.showMessageDialog(null, texte, "Aide d'utilisation", JOptionPane.INFORMATION_MESSAGE);
			
		}
		if (pEvt.getActionCommand().equals(Data.TITRE_MATRICE[2])){
			String texte = new String("Texte pour comprendre matrice");
		}
		
		if (pEvt.getActionCommand().equals(Data.TITRE_MATRICE[2])){
			String texte = new String("Pour bien utiliser ce logiciel, il faut suivre les étapes suivantes. Toutes les étapes nécessitent d'appuyer sur un bouton 'valider' à chaque fois.\n\n\nPremièrement, choisir la taille de sa matrice. Celle-ci peut être comprise entre 3 et 5 (Si on comprends le principe avec ces tailles-là, on comprend le principe avec des tailles encore plus grandes.\n\n"
					+ "Deuxièmement, remplir sa matrice. On peut remplir la matrice avec des entiers (positifs, négatifs, nuls) et des fractions (positives,négatives). Les fractions seront réduites automatiquement.\n\n"
					+ "Troisièmement, effectuer des calculs sur sa matrice pour trouver la matrice inverse. Les calculs doivent s'écrirent correctement. Les différents formes de calculs possibles sont les suivantes :\n\n"
					+ "Ligne_i ↔ Ligne_j\n"
					+ "Ligne_i ← lambda * ligne_i (Si lambda ≠ 0)\n"
					+ "Ligne_i ← ligne_i + lambda * ligne_j\n\n"
					+ "Une matrice identité correspond à : \n" + Matrice.identite(3).toString()
					+ "Bonne chance!");

			JOptionPane.showMessageDialog(null, texte, "Aide d'utilisation", JOptionPane.INFORMATION_MESSAGE);
		}		
		if (pEvt.getActionCommand().equals(Data.TITRE_MENU[2])){
             SwingUtilities.getWindowAncestor(this).dispose();
		}
	}
	
	public CardLayout getCardLayout() {
		return cardLayout;
	}

	public void setCardLayout(CardLayout cardLayout) {
		this.cardLayout = cardLayout;
	}

}
