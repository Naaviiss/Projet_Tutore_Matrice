package vue;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

import Controleur.Controleur;
import modele.Data;

public class PanelCommandes extends JPanel implements Data{
	private JButton valider;
	private JButton[] operations;//boutons pour les opérateurs
	private JButton[] lignes; //boutons pour les lignes
	private JLabel entete;
	private JButton constante;
	private GridBagLayout gestionnaire;
	private GridBagConstraints contraintes;
	private JTextArea zoneCommentaire;
	private JLabel labelZoneCommentaire;
	private JLabel[] calcul;//labels avec le futur calcul de l'étudiant
	private JComboBox choixFleche; //menu déroulant pour que l'utilisateur choisit
	private String []fleches = new String[]{"<-","<->"};
	
	public PanelCommandes() {
		this.setPreferredSize(new Dimension(700, 850));
		Border raisedbevel = BorderFactory.createRaisedBevelBorder();
		Border loweredbevel = BorderFactory.createLoweredBevelBorder();
		this.setBorder(BorderFactory.createCompoundBorder(raisedbevel,loweredbevel));
		
		entete = new JLabel("Veuillez choisir la ligne à modifier");
		valider = new JButton("Valider");
		operations = new JButton[4];
		lignes = new JButton[4];
		zoneCommentaire = new JTextArea();
		labelZoneCommentaire = new JLabel("Un commentaire ?");
		choixFleche = new JComboBox(fleches);
		
		for (int i = 0; i<Data.OPERATIONS.length;i++) {
			operations[i] = new JButton(Data.OPERATIONS[i]);
		}
		for (int i = 0; i<lignes.length;i++) {
			lignes[i] = new JButton("L"+(i+1));
		}
		constante = new JButton("Constante");
		
		//mise en place du gestionnaire
		gestionnaire = new GridBagLayout();
		contraintes = new GridBagConstraints();
		this.setLayout(gestionnaire);
		contraintes.fill = GridBagConstraints.NONE;
		contraintes.insets = new Insets(10, 10, 10, 10);
		
		//mise en place des composant avec les contraintes
		contraintes.gridx = 2;
		contraintes.gridy = 0;
		contraintes.gridwidth = 7;
		contraintes.gridheight = 1;
		contraintes.weightx = 5;
		contraintes.weighty = 1;
		entete.setFont(new Font(Font.SERIF, 0, 25));
		this.add(entete,contraintes);
		
		contraintes.gridx = 2;
		contraintes.gridy = 1;
		contraintes.gridwidth = 1;
		contraintes.gridheight = 1;
		contraintes.weightx = 1;
		contraintes.weighty = 1;
		for (int i = 0; i<lignes.length;i++) {
			contraintes.gridx ++;
			lignes[i].setFont(new Font(Font.SERIF, 0, 20));
			this.add(lignes[i],contraintes);
		}
		
		contraintes.gridy = 2;
		contraintes.gridwidth = 2;
		contraintes.weighty = 2;
		contraintes.fill = GridBagConstraints.HORIZONTAL;
		contraintes.gridx = 8;
		contraintes.gridwidth = 2;
		valider.setFont(new Font(Font.SERIF, 0, 18));
		this.add(valider,contraintes);

		//disposition des labels avec les éléments du calcul
		contraintes.gridx = 1;
		contraintes.gridy = 2;
		contraintes.gridheight = 1;
		contraintes.gridwidth = 2;
		contraintes.weighty = 1;
		calcul = new JLabel[6];
		for (int i =0;i<calcul.length;i++) {
			if(i==0) {
				calcul[i] = new JLabel("L3");
				calcul[i].setFont(new Font(Font.SERIF, 0, 24));
				this.add(calcul[i], contraintes);
			}
			else if (i == 1) { //correspond à l'emplacement de la flèche donc on saute cette étape
				contraintes.gridx += 1;
				contraintes.gridwidth = 1;//on réduit la largeur des éléments suivants la flèche
				contraintes.fill = GridBagConstraints.NONE;
			}
			else {
				calcul[i] = new JLabel(i+"");
				calcul[i].setFont(new Font(Font.SERIF, 0, 24));
				this.add(calcul[i], contraintes);
			}
			contraintes.gridx += 1;
		}
		//disposition du choix de la flèche
		contraintes.fill = GridBagConstraints.HORIZONTAL;
		contraintes.gridx = 3;
		choixFleche.setFont(new Font(Font.SERIF, 0, 24));
		this.add(choixFleche,contraintes);
		
		contraintes.gridwidth = 1;
		contraintes.gridx = 4;
		contraintes.gridy = 3;
		constante.setFont(new Font(Font.SERIF, 0, 20));
		this.add(constante,contraintes);
		
		//disposition des boutons d'opérations
		int x[] = {2,4,6,8};
		contraintes.gridy = 4;
		contraintes.fill = GridBagConstraints.NONE;
		contraintes.weightx = 1;
		contraintes.weighty = 2;
		for (int i = 0; i<operations.length;i++) {
			contraintes.gridx = x[i];
			operations[i].setFont(new Font(Font.SERIF, 0, 20));
			operations[i].setPreferredSize(new Dimension(100, 60));
			this.add(operations[i],contraintes);
		}
		
		//préparation des boutons à l'écoute
		valider.setActionCommand(Data.VALIDER_PANEL_COMMANDES);
		for (int i = 0; i<Data.OPERATIONS.length;i++) {
			operations[i].setActionCommand(Data.OPERATIONS[i]);
		}
		for (int i = 0; i<lignes.length;i++) {
			lignes[i].setActionCommand(Data.LIGNES[i]);
		}
		
		//instanciation, disposition et ajout de la zone de commentaire
		contraintes.gridy = 5;
		contraintes.gridx = 2;
		labelZoneCommentaire.setFont(new Font(Font.SERIF, 0, 20));
		this.add(labelZoneCommentaire,contraintes);
		contraintes.gridwidth = 6;
		contraintes.gridx = 4;
		contraintes.gridheight=2;
		contraintes.fill=GridBagConstraints.BOTH;
		zoneCommentaire.setFont(new Font(Font.SERIF, 0, 20));
		zoneCommentaire.setLineWrap(true);
		this.add(zoneCommentaire,contraintes);
	}
	
	
	public void enregistreEcouteur(Controleur pControleur) {
		valider.addActionListener(pControleur);//bouton valider
		for (int i = 0; i<lignes.length;i++) { 
			lignes[i].addActionListener(pControleur); //boutons de lignes
		}
		for (int i = 0; i<Data.OPERATIONS.length;i++) {
			operations[i].addActionListener(pControleur);//boutons des opérateurs
		}
		
		choixFleche.addActionListener(pControleur); //jcombobox pour le choix des flèches
	}
}
