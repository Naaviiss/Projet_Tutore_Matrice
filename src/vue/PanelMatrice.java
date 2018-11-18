package vue;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JPanel;

public class PanelMatrice extends JPanel{
	JButton valider = new JButton("valider");
	int taille;

	public PanelMatrice(){
		valider.setPreferredSize(new Dimension(50, 75));
		valider.setFont(new Font(Font.SERIF, 20, 60));
		
		//ajout des champs au panel
		this.add(valider, "valider");
	}
	
	public void enregistreEcouteur(PanelChoix parControleur){
		valider.addActionListener(parControleur);
	}

	public void setTaille(int taille) {
		this.taille = taille;
		
	}

}
