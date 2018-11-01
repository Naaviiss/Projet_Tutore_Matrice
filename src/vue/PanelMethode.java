package vue;

import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JPanel;

public abstract class PanelMethode extends JPanel{
	private JButton suivant;
	private JButton precedant;
	
	
	public void setControleur(Controleur newControleur) { 
		/* donne au controleur la posibilité d'écouter ce que font les 
		boutons */
		suivant.enregistreEcouteur(newControleur);
		precedant.enregistreEcouteur(newControleur);
	}
}
