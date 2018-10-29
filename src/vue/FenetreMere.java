package vue;

import javax.swing.JFrame;

public class FenetreMere extends JFrame{
	public FenetreMere() {
		super();
		this.pack();
		this.setDefaultLookAndFeelDecorated(true);
		this.setExtendedState(this.MAXIMIZED_BOTH);
		PanelMatrice panelMatrice = new PanelMatrice();
		this.setContentPane(panelMatrice);
		setVisible(true);
	}
	
	public static void main (String []args) {
		FenetreMere fen = new FenetreMere();
	}
}
