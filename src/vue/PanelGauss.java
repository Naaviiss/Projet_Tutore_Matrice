package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Insets;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

public class PanelGauss extends JPanel{
	private PanelAffichageMatrices affichageMatrices; //là où les matrices seront affichées
	private JScrollPane defil;//panneau defilant
	
	public PanelGauss(/*Matrice pMatrice*/) {
		//gestionnaire et bordure
		this.setLayout(new BorderLayout());
		this.setBorder(new EmptyBorder(100, 100, 100, 100));
		
		affichageMatrices = new PanelAffichageMatrices(/*pMatrice*/);
		defil = new JScrollPane(affichageMatrices);
		
		//ajout de la scrollbar
		defil.setVerticalScrollBarPolicy(defil.VERTICAL_SCROLLBAR_ALWAYS);
		this.add(defil,BorderLayout.WEST);
	}
}
