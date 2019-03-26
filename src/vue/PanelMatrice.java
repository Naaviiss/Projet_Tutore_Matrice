package vue;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modele.Data;
import modele.ExceptCaseVide;
import modele.ExceptEntreFraction;
import modele.ExceptNegatifMalPlace;
import modele.ExceptZeroDivision;
import modele.Fraction;
import modele.Matrice;
import Controleur.Controleur2;

public class PanelMatrice extends JPanel{
	private static final long serialVersionUID = 1L;
	private JPanel panelMatrice; //la partie où on entre la matrice
	private JPanel panelInstructions; // la partie avec les instructions et  le bouton valider
	private JLabel instruction;//instruction
	private Controleur2 chControleur; //le controleur
	private JButton boutonValider = new JButton("Valider");
	private int pTailleMatrice;	
	
	//le tableau avec tous les champs pour remplir la matrice
	private InputField[][] champsInput = new InputField[9][9];
	
	private Matrice matrice;
	
	public PanelMatrice(int taille) {
		pTailleMatrice = taille;
		matrice = new Matrice(pTailleMatrice);
		panelMatrice = new JPanel();
		panelInstructions = new JPanel();
		instruction = new JLabel("Veuillez compléter votre Matrice");
		
		//ce panel est divisé en 2
		this.setLayout(new BorderLayout());
		this.setBorder(new EmptyBorder(100, 100, 100, 100));

		
		//le bouton valider
		boutonValider.setPreferredSize(new Dimension(300, 100));
		boutonValider.setFont(new Font(Font.SERIF, 20, 60));
		
		//on met les boutons à l'écoute
		boutonValider.setActionCommand(Data.VALIDER_PANEL_MATRICE);
		boutonValider.addActionListener(chControleur);
		
		//prend en paramètre une matrice afin de pouvoir créer le nombre de champs nécessaires pour remplir la matrice
		panelMatrice.setLayout(new GridLayout(pTailleMatrice,pTailleMatrice,40,40));
		
		//on ajoute les champs au panel pour remplir la matrice
		for (int i=0;i<pTailleMatrice;i++) {
			for (int j=0;j<pTailleMatrice;j++) {
				champsInput[i][j] = new InputField();
				panelMatrice.add(champsInput[i][j]);
			}
		}
		
		//le panel instruction sera géré par un border layout
		panelInstructions.setLayout(new BorderLayout(20,20));
		
		//on personnalise l'instruction
		instruction.setFont(new Font(Font.SERIF, 20, 30));
		
		//on lui ajoute le bouton valider et l'instruction
		panelInstructions.add(instruction, BorderLayout.CENTER);
		panelInstructions.add(boutonValider,BorderLayout.SOUTH);
		
		//on ajoute les panel au panelMatrice
		this.add(panelMatrice, BorderLayout.WEST);
		this.add(panelInstructions, BorderLayout.EAST);
	}
	
	public void enregistreEcouteur(Controleur2 parControleur){
		boutonValider.addActionListener(parControleur);
	}
	
	public void setTaille(int taille){
		pTailleMatrice=taille;
	}
	
	public Matrice getMatriceSaisi() throws ExceptEntreFraction,ExceptZeroDivision,ExceptCaseVide,ExceptNegatifMalPlace{
		for (int i=0; i<matrice.getTaille();i++){
			for(int j=0;j<matrice.getTaille();j++){
				matrice.setCase(i,j,new Fraction(champsInput[i][j].getText()));
			}
		}
		return matrice;
	}//getMatriceSaisi()
	
}