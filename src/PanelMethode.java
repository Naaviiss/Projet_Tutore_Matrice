import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JPanel;

public abstract class PanelMethode extends JPanel{
	private JButton suivant;
	private JButton precedant;
	
	public JButton getBouton(String bouton) { 
		// recupere le bouton correspondant a la string choisis
		if(bouton=="suivant") { 
			// bouton symplex
			return suivant;
			}
		else if (bouton=="precedant") { 
			// bouton gauss
			return precedant;
		}
		return null;
	}
	
	
	public void setControleur(Controleur newControleur) { 
		/* donne au controleur la posibilité d'écouter ce que font les 
		boutons */
		suivant.enregistreEcouteur(newControleur);
		precedant.enregistreEcouteur(newControleur);
	}
}
