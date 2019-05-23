package vue;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelDemo extends JPanel implements ActionListener{
	
	JPanel diapositives;
	JLabel instructions;
	CardLayout gestionnaireDeCartes;
	int indice=0;
	
	String[] titres= {"Affichage", "Charger un Simplexe (Fichier > Charger Simplexe","Création d'un Simplexe (Fichier > Nouveau Simplexe)","Création d'un Simplexe (partie 2) (Fichier > Nouveau Simplexe)"};
	
	JLabel titre;

	JButton retour;
	JButton suivant;

	
	/**
	 * Génère un panel contenant un diaporama d'images du dossier "images"
	 */
	public PanelDemo() {

		gestionnaireDeCartes=new CardLayout(5,5);
		diapositives=new JPanel();
		diapositives.setLayout(gestionnaireDeCartes);
		retour = new JButton("<");
		retour.addActionListener(this);
		retour.setActionCommand("retour");
		suivant = new JButton(">");
		suivant.addActionListener(this);
		suivant.setActionCommand("suivant");
		
		instructions = new JLabel("Cliquez sur les flèches de défilement pour parcourir le mode d'emploi");
		JPanel container =new JPanel();
		container.setLayout(new BorderLayout());
		
		
		ImageIcon image = new ImageIcon("images"+File.separator+"Simplexe.png");
		JLabel affichage=new JLabel(image);
		diapositives.add(affichage, "Affichage");
		
		ImageIcon image3 = new ImageIcon("images"+File.separator+"chargerSimplexe.png");
		JLabel affichage3=new JLabel(image3);
		diapositives.add(affichage3, "Charger un Simplexe (Fichier > Charger Simplexe)");

		ImageIcon image2 = new ImageIcon("images"+File.separator+"creerSimplexe.png");
		JLabel affichage2=new JLabel(image2);
		diapositives.add(affichage2, "Création d'un Simplexe (Fichier > Nouveau Simplexe)");
		
		ImageIcon image4 = new ImageIcon("images"+File.separator+"creerSimplexe2.png");
		JLabel affichage4=new JLabel(image4);
		diapositives.add(affichage4, "Création d'un Simplexe (partie 2) (Fichier > Nouveau Simplexe)");
		
		
		
		titre=new JLabel("Affichage");
		container.add(diapositives, BorderLayout.CENTER);
		container.add(titre, BorderLayout.SOUTH);
		container.add(instructions, BorderLayout.NORTH);
		
		
		this.add(retour);
		this.add(container);
		this.add(suivant);
		
		gestionnaireDeCartes.show(diapositives, "Affichage");
		
	}

	/**
	 * Permet le défilement du diaporama
	 */
	@Override
	public void actionPerformed(ActionEvent evt) {
		if(evt.getActionCommand().equals("retour")) {
			gestionnaireDeCartes.previous(diapositives);
			indice--;
			if(indice==-1) {
				indice=titres.length -1;
			}
		}
		
		if(evt.getActionCommand().equals("suivant")) {
			gestionnaireDeCartes.next(diapositives);
			indice++;
			if(indice==titres.length) {
				indice=0;
			}
			titre.setText(titres[indice]);
			titre.setText(titres[indice]);
			
		}
		
	}
}
