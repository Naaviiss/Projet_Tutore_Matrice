package vue;

import javax.swing.JFrame;

/**
 * FenetreDemo est une classe qui permet le lancement d'une d�monstration pour
 * l'application simplexe
 */
public class FenetreDemo extends JFrame {

	/**
	 * Cl� de hachage SHA qui identifie de mani�re unique FenetreDemo
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Construction d'une nouvelle fen�tre qui peut �tre ferm�e sans terminer le
	 * programme
	 */
	public FenetreDemo() {
		super("D�monstration");
		PanelDemo panelDemo = new PanelDemo();
		this.setContentPane(panelDemo);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setSize(1500, 1000);
		this.setVisible(true);
		this.setLocation(100, 100);
	}
}