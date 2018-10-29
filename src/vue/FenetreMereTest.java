package vue;

import javax.swing.JFrame;

public class FenetreMereTest extends JFrame{
	public FenetreMereTest() {
		super();
		PanelGauss panel = new PanelGauss();
		this.setContentPane(panel);
		this.pack();
		this.setDefaultLookAndFeelDecorated(true);
		this.setExtendedState(this.MAXIMIZED_BOTH);
		setVisible(true);
	}
	
	public static void main(String []args) {
		FenetreMereTest fen = new FenetreMereTest();
	}
}
