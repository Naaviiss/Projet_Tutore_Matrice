package vue;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Iterator;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Controleur.Controleur;
import Modele.ContrainteExplicite;
import Modele.Monome;
import Modele.Simplexe;

public class PanelSimplex extends JPanel {
	
	private Simplexe simplexe;
	public GridBagConstraints gridLim = new GridBagConstraints() ;
	public final Font police = new Font("Times New Roman", Font.PLAIN, 20);
	
	JButton b;
	JButton[][] tabBoutonsInconnues;
	
	public PanelSimplex() {
		tabBoutonsInconnues=new JButton[0][0];
		b=new JButton("");
		this.add(new JLabel("Panel Simplexe"),JLabel.CENTER);
		this.setBorder(BorderFactory.createLoweredBevelBorder());
	}

	/**
	 * Construit un objet PanelSimplexe à partir d'un objet Simplexe permettant l'affichage d'un Simplexe
	 * @param Simplexe simp
	 */
	public PanelSimplex(Simplexe simp) {
		
		simplexe = simp;
		
		int nbContraintes = 0;
		int nbBoutons = 0;
		
		tabBoutonsInconnues=new JButton[simplexe.getContraintes().size()][simplexe.getFonctionEco().getMonomes().size()];
		this.setLayout(new GridBagLayout());
		
		gridLim.gridwidth = 1;
		gridLim.gridheight = 1;
		gridLim.insets = new Insets(10,5,10,5);
		gridLim.gridy = 0;
		gridLim.gridx = 0;

		for(Iterator<?> j = simplexe.getContraintes().iterator(); j.hasNext();) {
			gridLim.gridx = 0;
			ContrainteExplicite ce = (ContrainteExplicite) j.next();
			JLabel lab = new JLabel(ce.getNom()+" =",JLabel.LEFT);
			lab.setFont(police);
			this.add(lab,gridLim);
			gridLim.gridx++;
			for(Object obj : ce.getMonomes().values()) {
				Monome m = (Monome) obj;
				String textLabel = "";
				
				//SIGNES
				if(m.getCoefficient().getNumerateur()>0 && gridLim.gridx!=1) {
					textLabel+="+";
				}
				//COEFF
				if(Double.compare(m.getCoefficient().FMath(), 1)!=0 || Double.compare(m.getCoefficient().FMath(), -1)!=0 || m.getCoefficient().getNumerateur()!=0) {
					textLabel+=m.getCoefficient().toString();
				}
				if(m.getCoefficient().getNumerateur()!=0) {
					lab = new JLabel(textLabel);
					lab.setFont(police);
					this.add(lab,gridLim);
					gridLim.gridx++;
				}
				
				
				//BOUTON
				
				if(!m.getInconnue().equals(" ") && m.getCoefficient().getNumerateur()!=0) {
					tabBoutonsInconnues[nbContraintes][nbBoutons] = new JButton(m.getInconnue());
					tabBoutonsInconnues[nbContraintes][nbBoutons].setToolTipText("Une variable hors base à échanger");
					tabBoutonsInconnues[nbContraintes][nbBoutons].setFont(police);
					this.add(tabBoutonsInconnues[nbContraintes][nbBoutons],gridLim);
					nbBoutons++;
				}
				
				
				gridLim.gridx++;
			}
			gridLim.gridy++;
			gridLim.gridx = 0;
			nbBoutons=0;
			nbContraintes++;
		}
		
		gridLim.gridy++;
		gridLim.gridx = 0;
		JLabel lab = new JLabel(simplexe.getFonctionEco().toString());
		lab.setFont(police);
		this.add(lab,gridLim);
		
		this.setBorder(BorderFactory.createLoweredBevelBorder());
	}

	/**
	 * Renvoie le champ simplexe de this
	 * @return Simplexe simplexe
	 */
	public Simplexe getSimplexe() {
		return simplexe;
	}

	/**
	 * Définit le champ simplexe de this à partir du Simplexe donné en paramètre
	 * @param simplexe
	 */
	public void setSimplexe(Simplexe simplexe) {
		this.simplexe = simplexe;
	}

	/**
	 * Renvoie le champ tabBoutonsInconnues de this
	 * @return JButton[][] tabBoutonsInconnues
	 */
	public JButton[][] getTabBoutonsInconnues() {
		return tabBoutonsInconnues;
	}

	/**
	 * Définit le champ tabBoutonsInconnues de this avec le JButton[][] donné en paramètre
	 * @param JButton[][] tabBoutonsInconnues
	 */
	public void setTabBoutonsInconnues(JButton[][] tabBoutonsInconnues) {
		this.tabBoutonsInconnues = tabBoutonsInconnues;
	}
	
	/**
	 * Met le controleur donné en paramètre à l'écoute de chaque monome pour pouvoir les échanger
	 * @param Controleur controleur
	 */
	public void enregistreEcouteur(Controleur controleur) {
		for(int i = 0; i < tabBoutonsInconnues.length; i++){
			for(int j = 0; j < tabBoutonsInconnues[i].length; j++){
				if(tabBoutonsInconnues[i][j]!=null) {
					tabBoutonsInconnues[i][j].setActionCommand("monomes "+Integer.toString(i));
					tabBoutonsInconnues[i][j].addActionListener(controleur);
				}
			}
		}
	}
	
	
}
