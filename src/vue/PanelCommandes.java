package vue;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

import Controleur.Controleur;
import modele.Data;
import modele.Matrice;

public class PanelCommandes extends JPanel implements Data{
	private static final long serialVersionUID = 1L;
	private JButton valider;
	private JButton[] operations;//boutons pour les op�rateurs
	private JLabel entete;
	private JButton constante;
	private JTextField zoneCommentaire;
	private JLabel labelZoneCommentaire;
	private JButton effacer;//bouton pour effacer le calcul en cours
	private JLabel[] calcul;//labels avec le futur calcul de l'étudiant
	private JPanel panelGlobal;//panel qui va contenir les autres panels
	private JPanel []panels; //tableau de panels avec tous les elements graphiques
	private JButton[]fleches; //boutons pour les flèches
	private ChoixLigneMatrice chChoixMatrice;//panel affichant la matrice en cours d'utilisation
	private Matrice chMatrice;//matrice en cours d'utilisation
	private String[] operationChaine;//tableau correspondant au calcul de l'utilisateur sous forme de tableau
	
	public PanelCommandes(Matrice pMatrice) {
		//la taille du panel et les bordures
		this.setPreferredSize(new Dimension(700, 850));
		Border raisedbevel = BorderFactory.createRaisedBevelBorder();
		Border loweredbevel = BorderFactory.createLoweredBevelBorder();
		this.setBorder(BorderFactory.createCompoundBorder(raisedbevel,loweredbevel));

		chMatrice = pMatrice;

		entete = new JLabel("Veuillez choisir la ligne à modifier");
		entete.setFont(new Font(Font.SERIF, 0, 25));
		effacer = new JButton(Data.EFFACER);
		effacer.setFont(new Font(Font.SERIF, 0, 20));
		valider = new JButton("Valider");
		valider.setFont(new Font(Font.SERIF, 0, 20));
		operations = new JButton[Data.OPERATIONS.length];
		calcul = new JLabel[6]; //pour afficher le calcul de l'utilisateur
		panels = new JPanel[6];
		fleches = new JButton[Data.FLECHES.length];
		zoneCommentaire = new JTextField("",50);

		operations = new JButton[4];


		labelZoneCommentaire = new JLabel("Un commentaire ?");
		labelZoneCommentaire.setFont(new Font(Font.SERIF, 0, 20));
		zoneCommentaire.setFont(new Font(Font.SERIF, 0, 20));
		constante = new JButton("Constante");

		constante.setFont(new Font(Font.SERIF, 0, 20));
		chChoixMatrice = new ChoixLigneMatrice(chMatrice);

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

		//ligne avec l'op�ration
		panels[2] = new JPanel();
		panels[2].setLayout(new BoxLayout(panels[2], BoxLayout.LINE_AXIS));
		//instance et ajout des labels
		for (int i = 0;i<calcul.length;i++) {
			calcul[i] = new JLabel();
			calcul[i].setFont(new Font(Font.SERIF, 0, 24));
			panels[2].add(calcul[i]);
			panels[2].add(Box.createRigidArea(new Dimension(30,0)));
		}
		valider.setActionCommand(Data.VALIDER_PANEL_COMMANDES);
		panels[2].add(valider);
		panels[2].add(Box.createRigidArea(new Dimension(30,0)));
		//ajout du bouton effacer
		effacer.setActionCommand(Data.EFFACER);
		panels[2].add(effacer);
		panels[2].add(Box.createRigidArea(new Dimension(50,0)));

		//ligne avec le bouton constante et le choix de la flèche
		panels[3] = new JPanel();
		panels[3].setLayout(new BoxLayout(panels[3], BoxLayout.LINE_AXIS));
		//instance et ajoutdes boutons fl�ches
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
		//instance et ajout des boutons d'op�raion
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

	//change la matrice affichée dans le panel commande
	public void refresh(Matrice pMatrice) {
		panels[1].remove(chChoixMatrice); //on retire le panel
		ChoixLigneMatrice nouveauChoix = new ChoixLigneMatrice(pMatrice);
		setChChoixMatrice(nouveauChoix); //on créé le nouveau panel
		panels[1].add(chChoixMatrice); //on ajoute le nouveau panel
	}
	
	public ChoixLigneMatrice getChChoixLigneMatrice() {
		return chChoixMatrice;
	}
	
	public void setChChoixMatrice(ChoixLigneMatrice chChoixMatrice) {
		this.chChoixMatrice = chChoixMatrice;
	}

	public String[] getOperationChaine() {
		return operationChaine;
	}

	public void setOperationChaine(String[] operationChaine) {
		this.operationChaine = operationChaine;
	}

	public JLabel[] getCalcul() {
		return calcul;
	}

	public void setChMatrice(Matrice pMatrice) {
		this.chMatrice = pMatrice;
	}
	
	public void enregistreEcouteur(Controleur pControleur) {
		valider.addActionListener(pControleur);//bouton valider
		constante.addActionListener(pControleur);
		for (int i = 0; i<Data.OPERATIONS.length;i++) {
			operations[i].addActionListener(pControleur);//boutons des op�rateurs
		}
		for (int i = 0; i<Data.FLECHES.length;i++) {
			fleches[i].addActionListener(pControleur);//boutons des fl�ches
		}	
		chChoixMatrice.enregistreEcouteur(pControleur);
		effacer.addActionListener(pControleur);
	}
	
	public JTextField getZoneCommentaire() {
		return zoneCommentaire;
	}

	public JLabel getLabel(int i) {
		return calcul[i];
	}
	
	//retourne le premier emplacement disponible pour une ligne
	public int getLabelVideLigne() {
		int emplacementsLignes[] = {0,2,3,5} ;//indice des labels correspondant aux emplacement des lignes
		
		for (int i = 0;i<emplacementsLignes.length;i++) {
			if (getLabel(emplacementsLignes[i]).getText().equals(""))
				return emplacementsLignes[i];
		}
		return 0;
	}
	
	//retourne le premier emplacement disponible pour une ligne
	public int getLabelVideConstante() {
		int emplacementsConstante[] = {2,4} ;//indice des labels correspondant aux emplacement des constantes
		for (int i = 0;i<emplacementsConstante.length;i++) {
			if (getLabel(emplacementsConstante[i]).getText().equals(""))
				return emplacementsConstante[i];
			}
		return 4;
	}
	
	//retourne le bouton Effacer
	public JButton getEffacer() {
		return effacer;
	}

}

