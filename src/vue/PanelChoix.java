package vue;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

public class PanelChoix extends JPanel implements ActionListener{
	PanelTaille panTaille;
	PanelGauss gauss;
	PanelMatrice panMatrice;
	int taille;
	
	public PanelChoix() {
		this.setLayout(new CardLayout());
		panTaille = new PanelTaille();
		gauss = new PanelGauss();
		panMatrice = new PanelMatrice();
		this.add(panTaille, "panel_taille");
		this.add(gauss, "panel_gauss");
		this.add(panMatrice, "panel_matrice");
		
		panTaille.enregistreEcouteur(this);
		panMatrice.enregistreEcouteur(this);
	}
	
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		   if (source == panTaille.valider)   {
			   System.out.println(panTaille.getTaille());
			   	if (panTaille.getTaille() == 3 || panTaille.getTaille() == 4 || panTaille.getTaille() == 5) {
			   		((CardLayout) this.getLayout()).show(this,"panel_matrice");	
		          }
		          
		      }
		   if (source == panMatrice.valider)   {
		          ((CardLayout) this.getLayout()).show(this,"panel_gauss");
		      }
		  }//actionPerformed()
}
