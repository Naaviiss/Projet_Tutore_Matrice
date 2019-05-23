package vue;

import javax.swing.JFrame;

/**
 * FenetreDemo est une classe qui permet le lancement d'une démonstration pour
 * l'application simplexe
 */
public class FenetreDemo extends JFrame {

	/**
	 * Clé de hachage SHA qui identifie de manière unique FenetreDemo
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Construction d'une nouvelle fenêtre qui peut être fermée sans terminer le
	 * programme
	 */
	public FenetreDemo() {
		super("Démonstration");
		PanelDemo panelDemo = new PanelDemo();
		this.setContentPane(panelDemo);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setSize(1500, 1000);
		this.setVisible(true);
		this.setLocation(100, 100);
	}
}