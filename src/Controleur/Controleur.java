package Controleur;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.LinkedList;

import javax.swing.JTextField;

import Modele.ContrainteExplicite;
import Modele.FonctionEco;
import Modele.Fraction;
import Modele.Historique;
import Modele.LectureEcriture;
import Modele.Monome;
import Modele.Simplexe;
import vue.PanelContraintes;
import vue.PanelFichier;
import vue.PanelGeneral;
import javax.swing.JButton;
import javax.swing.JOptionPane;

public class Controleur implements ActionListener {
	private PanelGeneral panelG;
	
	public GridBagConstraints contrainte = new GridBagConstraints() ;
	private PanelFichier panelFichier;
	
	public Controleur(PanelFichier panelFichier, PanelGeneral panelSimplex) {
		this.panelFichier=panelFichier;
		this.panelFichier.enregistreEcouteur(this);
		this.panelG=panelSimplex;
		
	}

	/**
	 * @param ActionEvent evt : un évenement correspondant au choix de l'utilisateur
	 * Permet de gérer les choix de l'utilisateur lors de son utilisation du programme Simplexe à partir du démarrage du programme.
	 */
	public void actionPerformed(ActionEvent evt) {
		
		if(evt.getActionCommand().equals("Cr")) {
			
			JTextField[][] tabContraintes =panelFichier.getPanelFormulaire().getPanelC().getZonesEcrituresContraintes();
			JTextField[] tabLimites=panelFichier.getPanelFormulaire().getPanelC().getZonesEcrituresValeursMaxi();
			LinkedList<ContrainteExplicite> contraintes=new LinkedList<ContrainteExplicite>();
			for(int i=0;i<tabContraintes.length;i++) {
				if(tabLimites[i].getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Veuillez entrer des coefficients valides.", "Erreur", JOptionPane.ERROR_MESSAGE);
					return;
				}
				ContrainteExplicite ce = new ContrainteExplicite(new Fraction(Integer.parseInt(tabLimites[i].getText()),1), "x"+Integer.toString(i+1+panelFichier.getPanelFormulaire().getPanelC().getNombreMonome()));
				for(int j=0;j<tabContraintes[0].length;j++) {
					if(tabContraintes[i][j].getText().isEmpty()) {
						JOptionPane.showMessageDialog(null, "Veuillez entrer des coefficients valides", "Erreur", JOptionPane.ERROR_MESSAGE);
						return;
					}
					Monome m = new Monome(new Fraction(Integer.parseInt(tabContraintes[i][j].getText()),1), "x"+Integer.toString(j+1));
					ce.ajouterMonome(m);
				}
				contraintes.add(ce);
			}
			
			
			FonctionEco fonctionEco = new FonctionEco();
			JTextField[] tabMonomesFonctionEco = panelFichier.getPanelFormulaire().getPanelC().getZonesEcrituresFonctionEco();
			for(int i=0;i<tabMonomesFonctionEco.length;i++) {
				if(tabMonomesFonctionEco[i].getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Veuillez entrer des coefficients valides", "Erreur", JOptionPane.ERROR_MESSAGE);
					return;
				}
				Monome m = new Monome(new Fraction(Integer.parseInt(tabMonomesFonctionEco[i].getText()),1), "x"+Integer.toString(i+1));
				fonctionEco.ajouterMonome(m);
			}
			
			
			Simplexe simplexe = new Simplexe(contraintes, fonctionEco);
			simplexe.passageDico1();
			Historique histo = new Historique();
			histo.add(simplexe);
			panelG.setHistorique(histo);
			panelG.setNomFichier(null);
			panelFichier.getPanelFormulaire().viderFormulaire();
			
			
		}
		
		if(evt.getActionCommand().equals("ok")) {

			PanelContraintes choixContraintesPanel = new PanelContraintes(panelFichier.getPanelFormulaire().getPanelCMC().getNbMonome().getItemAt(panelFichier.getPanelFormulaire().getPanelCMC().getNbMonome().getSelectedIndex()), panelFichier.getPanelFormulaire().getPanelCMC().getNbContraintes().getItemAt(panelFichier.getPanelFormulaire().getPanelCMC().getNbContraintes().getSelectedIndex()));
			panelFichier.getPanelFormulaire().setPanelC(choixContraintesPanel);
			panelFichier.getPanelFormulaire().enregistreEcouteurC(this);
			panelFichier.getPanelFormulaire().add(choixContraintesPanel, "Contraintes");
			panelFichier.getPanelFormulaire().getGestionnaireDeCartes().show(panelFichier.getPanelFormulaire(), "Contraintes");
			
			this.panelFichier.requestFocusInWindow();
		}
		

		if(evt.getActionCommand().contains("monomes")){
			JButton b = (JButton) evt.getSource();
			String str = ""+b.getActionCommand().charAt(8);
			int indice = Integer.parseInt(str) ;
			String horsBaseindice= ((ContrainteExplicite)panelG.getPanelSimplex().getPanelSimp().getSimplexe().getContraintes().get(indice)).getNom();
			Simplexe temp = new Simplexe(panelG.getPanelSimplex().getPanelSimp().getSimplexe());
			temp.echanger(horsBaseindice, b.getText());
			Historique tempHisto = new Historique(panelG.getHistorique());
			tempHisto.add(temp);
			panelG.setHistorique(tempHisto);
			
		}
		
		if(evt.getActionCommand().equals("Charger")) {
			System.out.println("ok");
			File fichier = new File("simplexes"+File.separator+panelFichier.getPanelCharger().getNomFichier());
			panelG.getPanelSimplex().remove(panelG.getPanelSimplex().getPanelH());
			panelG.setHistorique((Historique) LectureEcriture.lecture(fichier));
			panelG.getPanelSimplex().getPanelH().setHistorique(panelG.getHistorique());
			
			panelG.getPanelSimplex().add(panelG.getPanelSimplex().getPanelH(),BorderLayout.EAST);
			panelG.setNomFichier(panelFichier.getPanelCharger().getNomFichier());
		}
		
		if(evt.getActionCommand().equals("indice")) {
			System.out.println(panelG.getPanelSimplex().getPanelSimp().getSimplexe().toString2());
			System.out.println(panelG.getPanelSimplex().getPanelSimp().getSimplexe().echangeJudicieux());
			panelG.miseAJourIndication(panelG.getPanelSimplex().getPanelSimp().getSimplexe().echangeJudicieux());
		}

	}
	
}