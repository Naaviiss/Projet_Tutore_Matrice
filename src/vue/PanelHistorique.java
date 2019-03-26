package vue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import Modele.Historique;

public class PanelHistorique extends JPanel {
	
	private Historique historique;
	public final Font police = new Font("Times New Roman", Font.PLAIN, 17);
	
	public PanelHistorique(){
		JLabel vide = new JLabel("L'historique est vide.",JLabel.CENTER);
		this.add(vide);
		this.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.RED,2, true), "Historique"));
	}
	
	public PanelHistorique(Historique histo) {
		this.setLayout(new GridLayout(3, 1));
		historique = histo;
		
		JPanel contenu = new JPanel();
		contenu.setLayout(new GridBagLayout());

		GridBagConstraints contrainte = new GridBagConstraints() ;
		contrainte.gridx=0;
		contrainte.gridy=0;
		contrainte.insets = new Insets(5,25,5,25);
		
		for(int i =0 ; i<histo.getListeSimplexe().size() ; i++) {
			JLabel simp = new JLabel("<html><head></head><body><p> Dictionnaire n°"+Integer.toString(i)+":</p>"
			+histo.getListeSimplexe().get(i).toString()+"</body></html>");
			
			simp.setFont(police);
			contenu.add(simp, contrainte);
			contrainte.gridy++;
		}

		JScrollPane scrollPane = new JScrollPane(contenu, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
		ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setPreferredSize(new Dimension(300,1000));
		this.add(scrollPane);
	}

	public Historique getHistorique() {
		return historique;
	}

	public void setHistorique(Historique historique) {
		this.historique = historique;
	}
	
}