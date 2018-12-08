package vue;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import Controleur.Controleur;
import modele.Data;
import modele.Matrice;

public class PanelCommandes extends JPanel implements Data{
	private JButton valider;
	private JButton[] operations;//boutons pour les opérateurs
	//	private JButton[] lignes; //boutons pour les lignes
	private JLabel entete;
	private JButton constante;
	//	private GridBagLayout gestionnaire;
	//	private GridBagConstraints contraintes;
	private JTextField zoneCommentaire;
	private JLabel labelZoneCommentaire;
	private JLabel[] calcul;//labels avec le futur calcul de l'étudiant
	private JPanel panelGlobal;//panel qui va contenir les autres panels
	private JPanel []panels; //tableau de panels avec tous les elements graphiques
	private JButton[]fleches; //boutons pour les flèches
	private ChoixLigneMatrice chChoixMatrice;//panel affichant la matrice en cours d'utilisation
	private Matrice chMatrice;//matrice en cours d'utilisation
	public PanelCommandes(Matrice pMatrice) {
		//la taille du panel et les bordures
		this.setPreferredSize(new Dimension(700, 850));
		Border raisedbevel = BorderFactory.createRaisedBevelBorder();
		Border loweredbevel = BorderFactory.createLoweredBevelBorder();
		this.setBorder(BorderFactory.createCompoundBorder(raisedbevel,loweredbevel));

		chMatrice = pMatrice;
		entete = new JLabel("Veuillez choisir la ligne à  modifier");
		entete.setFont(new Font(Font.SERIF, 0, 25));
		valider = new JButton("Valider");
		valider.setFont(new Font(Font.SERIF, 0, 20));
		operations = new JButton[Data.OPERATIONS.length];
		calcul = new JLabel[7];
		panels = new JPanel[6];
		fleches = new JButton[Data.FLECHES.length];
		//		lignes = new JButton[4];
		zoneCommentaire = new JTextField(50);
		labelZoneCommentaire = new JLabel("Un commentaire ?");
		labelZoneCommentaire.setFont(new Font(Font.SERIF, 0, 20));
		zoneCommentaire.setFont(new Font(Font.SERIF, 0, 20));
		constante = new JButton("Constante");
		constante.setFont(new Font(Font.SERIF, 0, 20));
		chChoixMatrice = new ChoixLigneMatrice(chMatrice);

		//		//instance des boutons lignes à supprimer
		//		for (int i = 0; i<lignes.length;i++) {
		//			lignes[i] = new JButton("L"+(i+1));
		//		}


		/*//mise en place du gestionnaire
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

		//disposition des labels avec les Ã©lÃ©ments du calcul
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
			else if (i == 1) { //correspond Ã  l'emplacement de la flÃ¨che donc on saute cette Ã©tape
				contraintes.gridx += 1;
				contraintes.gridwidth = 1;//on rÃ©duit la largeur des Ã©lÃ©ments suivants la flÃ¨che
				contraintes.fill = GridBagConstraints.NONE;
			}
			else {
				calcul[i] = new JLabel(i+"");
				calcul[i].setFont(new Font(Font.SERIF, 0, 24));
				this.add(calcul[i], contraintes);
			}
			contraintes.gridx += 1;
		}
		//disposition du choix de la flÃ¨che
		contraintes.fill = GridBagConstraints.HORIZONTAL;
		contraintes.gridx = 3;
		choixFleche.setFont(new Font(Font.SERIF, 0, 24));
		this.add(choixFleche,contraintes);

		contraintes.gridwidth = 1;
		contraintes.gridx = 4;
		contraintes.gridy = 3;
		constante.setFont(new Font(Font.SERIF, 0, 20));
		this.add(constante,contraintes);

		//disposition des boutons d'opÃ©rations
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

		//prÃ©paration des boutons Ã  l'Ã©coute
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
		this.add(zoneCommentaire,contraintes);*/

		//mise en place des pannels pour chaque ligne
		//ligne avec l'entete
		panels[0] = new JPanel();
		panels[0].setLayout(new BoxLayout(panels[0], BoxLayout.LINE_AXIS));
		panels[0].add(entete);

		//ligne avec la matrice
		panels[1] = new JPanel();
		panels[1].setLayout(new BoxLayout(panels[1], BoxLayout.LINE_AXIS));
		panels[1].add(Box.createRigidArea(new Dimension(110,0)));
		panels[1].add(chChoixMatrice);

		//ligne avec l'opération
		panels[2] = new JPanel();
		panels[2].setLayout(new BoxLayout(panels[2], BoxLayout.LINE_AXIS));
		//instnce et ajout des labels
		for (int i = 0;i<calcul.length;i++) {
			calcul[i] = new JLabel("la");
			calcul[i].setFont(new Font(Font.SERIF, 0, 24));
			panels[2].add(calcul[i]);
			panels[2].add(Box.createRigidArea(new Dimension(50,0)));
		}
		panels[2].add(valider);

		//ligne avec le bouton constante et le choix de la flèche
		panels[3] = new JPanel();
		panels[3].setLayout(new BoxLayout(panels[3], BoxLayout.LINE_AXIS));
		//instance et ajoutdes boutons flèches
		for (int i = 0; i<fleches.length;i++) {
			fleches[i] = new JButton(Data.FLECHES[i]);
			fleches[i].setFont(new Font(Font.SERIF, 0, 20));
			fleches[i].setActionCommand(Data.FLECHES[i]);
			panels[3].add(fleches[i]);
			panels[3].add(Box.createRigidArea(new Dimension(120,0)));
		}
		//ajout du bouton constante
		panels[3].add(constante);

		//ligne avec les boutons pour les opérations
		panels[4] = new JPanel();
		panels[4].setLayout(new BoxLayout(panels[4], BoxLayout.LINE_AXIS));
		panels[4].add(Box.createRigidArea(new Dimension(100,0)));
		//instance et ajout des boutons d'opéraion
		for (int i = 0; i<Data.OPERATIONS.length;i++) {
			operations[i] = new JButton(Data.OPERATIONS[i]);
			operations[i].setActionCommand(Data.OPERATIONS[i]);
			operations[i].setFont(new Font(Font.SERIF, 0, 20));
			panels[4].add(operations[i]);
			panels[4].add(Box.createRigidArea(new Dimension(100,0)));
		}

		//ligne avec la section commentaire
		panels[5] = new JPanel();
		panels[5].setLayout(new BoxLayout(panels[5], BoxLayout.LINE_AXIS));
		panels[5].add(Box.createRigidArea(new Dimension(30,0)));
		panels[5].add(labelZoneCommentaire);
		panels[5].add(Box.createRigidArea(new Dimension(30,0)));
		panels[5].add(zoneCommentaire);
		panels[5].add(Box.createRigidArea(new Dimension(30,0)));
		
		//on regroupe les panels et on les aligne
		panelGlobal = new JPanel();
		panelGlobal.setLayout(new BoxLayout(panelGlobal, BoxLayout.PAGE_AXIS));
		panelGlobal.add(Box.createVerticalStrut(30));
		//on y ajoute les panels créés précédemment
		for (int i=0;i<panels.length;i++) {
			panelGlobal.add(panels[i]);
			panelGlobal.add(Box.createVerticalStrut(50));
		}
		this.setLayout(new BorderLayout());
		this.add(panelGlobal,BorderLayout.CENTER);
	}


	public void setChMatrice(Matrice pMatrice) {
		this.chMatrice = pMatrice;
	}


	public void enregistreEcouteur(Controleur pControleur) {
		valider.addActionListener(pControleur);//bouton valider
		constante.addActionListener(pControleur);
		//		for (int i = 0; i<lignes.length;i++) { 
		//			lignes[i].addActionListener(pControleur); //boutons de lignes
		//		}
		for (int i = 0; i<Data.OPERATIONS.length;i++) {
			operations[i].addActionListener(pControleur);//boutons des opérateurs
		}
		for (int i = 0; i<Data.FLECHES.length;i++) {
			fleches[i].addActionListener(pControleur);//boutons des flèches
		}		
	}
}
