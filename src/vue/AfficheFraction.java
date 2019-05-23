package vue;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * AfficheFraction est une classe qui permet d'afficher correctement une
 * fraction.
 */
public class AfficheFraction extends JPanel {

	/**
	 * Clé de hachage SHA qui identifie de manière unique AfficheFraction
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Construit un objet AfficheFraction correspondant à un BorderLayout dans
	 * lequel sera affichée une fraction
	 * 
	 * @param num
	 *            le numérateur de la fraction
	 * @param denom
	 *            le dénominateur de la fraction
	 */
	public AfficheFraction(int num, int denom) {
		this.setLayout(new BorderLayout());
		JLabel frac = new JLabel("<html><head></head><body>" + "<center><p>" + Integer.toString(num)
				+ "</p></center><hr>" + "<center><p>" + Integer.toString(denom) + "</center></p></body></html>",
				JLabel.CENTER);
		this.add(frac, BorderLayout.CENTER);
	}

	public static void main(String[] args) {
		JFrame f = new JFrame("Afficher une fraction");
		f.setContentPane(new AfficheFraction(14, 5));
		f.setSize(50, 100);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}