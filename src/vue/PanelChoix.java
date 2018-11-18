package vue;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import Controleur.Controleur;

public class PanelChoix extends JPanel{
	private PanelAffichage panAffichage;
	private PanelTaille panTaille;
	private PanelGauss panGauss;
	private PanelMatrice panMatrice;
	private Controleur chControleur;
	private JPanel panelDiapo;
	private CardLayout cardLayout = new CardLayout();
	
	public PanelChoix(Controleur parControleur) {
		panelDiapo = new JPanel();
		chControleur = parControleur;
		panelDiapo.setLayout(cardLayout);
		panTaille = new PanelTaille();
		panGauss = new PanelGauss();
		panMatrice = new PanelMatrice(3, chControleur);
		panelDiapo.add(panTaille, "panel_taille");
		panelDiapo.add(panGauss, "panel_gauss");
		panelDiapo.add(panMatrice, "panel_matrice");
		
		panTaille.enregistreEcouteur(chControleur);
		panMatrice.enregistreEcouteur(chControleur);
		
		cardLayout.show(panelDiapo,"panel_taille");
	}
	
//	public void actionPerformed(ActionEvent e) {
//		Object source = e.getSource();
//		   if (source == panTaille.valider)   {
//			   System.out.println(panTaille.getTaille());
//			   	if (panTaille.getTaille() == 3 || panTaille.getTaille() == 4 || panTaille.getTaille() == 5) {
//			   		((CardLayout) this.getLayout()).show(this,"panel_matrice");	
//		          }
//		          
//		      }
//		   if (source == panMatrice.valider)   {
//		          ((CardLayout) this.getLayout()).show(this,"panel_gauss");
//		      }
//		  }//actionPerformed()
}
