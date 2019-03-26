package vue;


import javax.swing.JFrame;


public class FenetreDemo extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Construction d'une nouvelle fenêtre qui peut être fermée sans terminer le programme
	 */
	
	public FenetreDemo() {
		super("Démonstration");
		
		PanelDemo panelDemo = new PanelDemo();
		
		this.setContentPane(panelDemo);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setSize(1500,1000);
		this.setVisible(true);
		this.setLocation(100,100);
		
	}
}
