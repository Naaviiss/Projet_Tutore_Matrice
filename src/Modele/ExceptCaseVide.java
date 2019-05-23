package Modele;

/**
 * ExceptCaseVide est une classe qui va permettre de gérer le cas où la matrice
 * n'est pas correctement remplie
 */
public class ExceptCaseVide extends Exception {
	
	/**
	 * Clé de hachage SHA qui identifie de manière unique ExceptCaseVide
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Exception qui va être levée quand la matrice n'est pas entièrement remplie
	 */
	public ExceptCaseVide() {
	}
}