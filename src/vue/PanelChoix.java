package vue;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

public class PanelChoix extends JPanel implements ActionListener{
	PanelTaille taille;
	PanelGauss gauss;
	PanelMatrice matrice;
	
	public PanelChoix() {
		this.setLayout(new CardLayout());
		
		taille = new PanelTaille();
		gauss = new PanelGauss();
		matrice = new PanelMatrice();
		this.add(taille, "panel taille");
		this.add(gauss, "panel_gauss");
		this.add(matrice, "panel_matrice");
		
		taille.enregistreEcouteur(this);
		matrice.enregistreEcouteur(this);
	}
	
	
	
	
	
	
	
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		   if (source == taille.valider)   {
			   System.out.println(taille.getTaille());
			   	if (taille.getTaille() == 3 || taille.getTaille() == 4 || taille.getTaille() == 5) {
			   		((CardLayout) this.getLayout()).show(this,"panel_matrice");	
		          }
		          
		      }
		   if (source == matrice.valider)   {
		          ((CardLayout) this.getLayout()).show(this,"panel_gauss");
		      }
		  }//actionPerformed()
}
