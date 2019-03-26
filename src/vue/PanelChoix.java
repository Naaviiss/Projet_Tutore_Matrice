package vue;

import java.awt.CardLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import modele.*;

import Controleur.Controleur2;

public class PanelChoix extends JPanel{

	private static final long serialVersionUID = 1L;
	private PanelTaille panTaille;
	private PanelGauss panGauss;
	private PanelMatrice panMatrice;
	private CardLayout cardLayout;
	private Controleur2 chControleur;
	public GridBagConstraints contrainte = new GridBagConstraints() ;
	private JButton didacticiel = new JButton("Mode didacticiel");
	private JButton autonome = new JButton("Mode autonome");

	
	public PanelChoix() {
		cardLayout = new CardLayout();
		this.setLayout(cardLayout);
		chControleur = new Controleur2(this);

		
		JPanel pan = new JPanel();
		pan.setLayout(new GridBagLayout());
			
		contrainte.fill = GridBagConstraints.BOTH; contrainte.insets = new Insets(10,10,10,10);
		contrainte.ipady = contrainte.anchor = GridBagConstraints.CENTER;
			
		didacticiel.addActionListener(chControleur);
		didacticiel.setActionCommand(Data.VALIDER_DIDACTICIEL);
			
		autonome.addActionListener(chControleur);
		autonome.setActionCommand(Data.VALIDER_AUTONOME);
			
		contrainte.gridx = 0; contrainte.gridy = 0;
		contrainte.gridheight = 2; contrainte.gridwidth = 2;
		pan.add(new JLabel("Veuillez choisir un mode à suivre",SwingConstants.CENTER),contrainte);
			
		contrainte.gridx = 0; contrainte.gridy = 2;
		contrainte.gridheight = 1; contrainte.gridwidth = 1;
		pan.add(didacticiel,contrainte);
			
		contrainte.gridx = 1; contrainte.gridy = 2;
		contrainte.gridheight = 1; contrainte.gridwidth = 1;
		pan.add(autonome,contrainte);
			
		this.add(pan);
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

	public Controleur2 getChControleur() {
		return chControleur;
	}

	public void setChControleur(Controleur2 chControleur) {
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
