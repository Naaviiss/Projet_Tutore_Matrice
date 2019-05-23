package vue;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Controleur.Controleur;

public class PanelChoixNombresMonomesContraintes extends JPanel {

	/**
	 * Choix du nombre de monomes
	 */
	JComboBox <Integer> nbMonome = new JComboBox <Integer>();
	
	/**
	 * Choix du nombre de contraintes
	 */
	JComboBox <Integer> nbContraintes = new JComboBox <Integer>();
	Integer [] box = new Integer[10];
	
	public GridBagConstraints contrainte = new GridBagConstraints() ;
	
	private Integer nombreMonome;
	private Integer nombreContraintes;
	JButton ok;
	
	/**
	 * Construit un objet PanelChoixNombresMonomesContraintes permettant à l'utilisateur de choisir le nombre de contraintes et de 
	 * monomes souhaité dans le Simplexe
	 */
	public PanelChoixNombresMonomesContraintes() {
		//On ajoute nbMonome, nbContrainte et leur label dans la partie Nord
		//du formulaire
		
		this.setLayout(new GridBagLayout());
		
		contrainte.fill = GridBagConstraints.BOTH; contrainte.insets = new Insets(20,10,20,10);
		
		JLabel labelTitre = new JLabel("Formulaire de crÃ©ation du Simplexe",JLabel.CENTER);
		contrainte.gridx = 0; contrainte.gridy = 0;
		contrainte.gridwidth = 1; contrainte.gridheight = 1;
		this.add(labelTitre, contrainte);
		
		JLabel labelNb = new JLabel("Nombre de monomes : ", JLabel.CENTER);
		contrainte.gridy = 1;
		this.add(labelNb, contrainte);
		for(int i=0; i<10; i++) {
			nbMonome.addItem(i+1);
			nbContraintes.addItem(i+1);
			box[i]=i+1;
		}
		
		contrainte.gridx = 1; contrainte.gridwidth = 2;
		this.add(nbMonome,contrainte);
		
		JLabel labelNbContraintes = new JLabel("Nombre de contraintes : ", JLabel.CENTER);
		contrainte.gridx = 0; contrainte.gridy = 2;
		contrainte.gridwidth = 1;
		this.add(labelNbContraintes,contrainte);
		contrainte.gridx = 1; contrainte.gridwidth = 2;
		this.add(nbContraintes,contrainte);
		ok = new JButton("Ok");
		contrainte.gridx = 0; contrainte.gridwidth = 3;
		contrainte.gridy = 3;
		contrainte.anchor = GridBagConstraints.CENTER;
		this.add(ok,contrainte);
	}
	
	/**
	 * Met le contrôleur en paramétre à l'écoute du bouton ok
	 * @param parControleur le contrôleur
	 */
	public void enregistreEcouteur(Controleur parControleur) {
		ok.setActionCommand("ok");
		ok.addActionListener(parControleur);
	}
	
	/**
	 * Renvoie le champ ok de this
	 * @return le bouton ok
	 */
	public JButton getBouton() {
		return ok;
	}
	
	/**
	 * Remet le nombre de monomes et de contraintes souhaité à 0
	 */
	public void viderFormulaire() {
		nbMonome.setSelectedIndex(0);
		nbContraintes.setSelectedIndex(0);
	}

	/**
	 * Renvoie le nombre de monome choisi dans la JComboBox
	 * @return JComboBox<Integer> nbMonome
	 */
	public JComboBox<Integer> getNbMonome() {
		return nbMonome;
	}

	/**
	 * Renvoie le nombre de contrainte choisi dans la JComboBox
	 * @return JComboBox<Integer> nbContraintes
	 */
	public JComboBox<Integer> getNbContraintes() {
		return nbContraintes;
	}

	/**
	 * Renvoie le champ nombreMonome de this
	 * @return Integer nombreMonome
	 */
	public Integer getNombreMonome() {
		return nombreMonome;
	}

	/**
	 * Renvoie le champ nombreContraintes de this
	 * @return Integer nombreContraintes
	 */
	public Integer getNombreContraintes() {
		return nombreContraintes;
	}
	
}
