package vue;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Controleur.Controleur;

public class PanelChargerSimplexe extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton charger;
	private JComboBox<String> fichiers;
	private String[] listeFichiers;
	
	/**
	 * Construit un objet PanelChargerSimplexe permettant à l'utilisateur de charger un Simplexe déjà existant
	 */
	public PanelChargerSimplexe() {
		
		//GridBagLayout
		
		this.setLayout(new GridBagLayout());
		GridBagConstraints contrainteEvenement = new GridBagConstraints();
		contrainteEvenement.insets = new Insets(3,3,3,3);
		
		//Création d'une etiquette
	
		JLabel nomDuFichier=new JLabel("Nom du fichier");
		
		//Création d'un fichier qui va indiquer où chercher les chronologies enregistrées
		File repertoire = new File("simplexes");
		
		//Tableau instancié avec les fichiers dans le File déterminé avant
		listeFichiers = repertoire.list();
		
		//Instantiation d'une JComboBox à partir de ce tableau
		fichiers = new JComboBox<String>(listeFichiers);
		
		//Bouton et ActionCommand
		charger=new JButton("Charger");	
		charger.setActionCommand("Charger");
		

		
		
		//Ajout des éléments dans le GridBagLayout
		contrainteEvenement.gridx=0;
		contrainteEvenement.gridy=0;
		this.add(nomDuFichier,contrainteEvenement);
		
		contrainteEvenement.gridx=1;
		contrainteEvenement.gridy=0;
		this.add(fichiers,contrainteEvenement);
		
		contrainteEvenement.gridx=0;
		contrainteEvenement.gridy=1;
		contrainteEvenement.insets = new Insets(20,3,3,3);
		this.add(charger,contrainteEvenement);
		
	}
	
	
	//getter
	/**
	 * Renvoie le nom du fichier choisi dans la JComboBox
	 * @return String
	 */
	public String getNomFichier(){
		int i = fichiers.getSelectedIndex();
		return (String) fichiers.getItemAt(i);
		
	}
	
	
	
	//ActionListener sur le bouton ajouter
	/**
	 * Met le controleur en paramètre à l'écoute du bouton Charger 
	 * @param parControleur
	 */
	public void enregistreEcouteur(Controleur parControleur){
		charger.addActionListener(parControleur);
	}
}
