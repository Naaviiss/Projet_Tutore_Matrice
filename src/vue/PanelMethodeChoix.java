//package vue;
//
//import java.awt.event.ActionEvent;
//import javax.swing.JButton;
//import javax.swing.JPanel;
//
//import controleur.Controleur;
//
//public class PanelMethodeChoix extends JPanel{
//	private JButton [] boutons; 
//	/* tableau de boutons qui contiens les différents
//	boutons pour choisir le logiciel a utilisé */
//
//	private PanelMethode panel; 
//	// panel choisis par l'utilisateur
//
//
//	public PanelMethodeChoix(){
//		JButton symplex = new JButton("Interface Lineaire");
//		boutons[0]  = symplex;
//		JButton gauss = new JButton("Methode de Gauss");
//		boutons[1]  = gauss;
//	}
//
//
//	public PanelMethode getPanelMethode() { 
//		// retourne le panel choisis par l'utilisateur
//		return panel;
//	}
//
//
//	public void setPanelMethode(PanelMethode newPanel) { 
//		//change le panel choisis
//		panel = newPanel;
//	}
//
//
//	public JButton getBouton(String bouton) { 
//		// recupere le bouton correspondant a la string choisis
//		if(bouton=="symplex") { 
//			// bouton symplex
//			return boutons[0];
//			}
//		else if (bouton=="gauss") { 
//			// bouton gauss
//			return boutons[1];
//		}
//	}
//
//
//	public void setControleur(Controleur newControleur) { 
//		/* donne au controleur la posibilité d'écouter ce que font les 
//		boutons */
//		for(int i=0; i<boutons.length ; i++) {
//    		boutons[i].enregistreEcouteur(newControleur);
//    	}
//	}
//
//
//	public void actionPerformed(ActionEvent event) {
//		if(event.getSource() == this.getBouton("symplex")) {
//			this.setPanelMethode(new PanelSymplex());
//			// penser a modifier les arguments de PanelSympex()
//		}
//		else if(event.getSource() == this.getBouton("gauss")) {
//			this.setPanelMethode(new PanelGaussTaille());
//			// penser a modifier les arguments de PanelGaussTaille()
//		}
//	}
//}
