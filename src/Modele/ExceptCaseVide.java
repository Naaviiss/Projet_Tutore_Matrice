package Modele;

/**
 * ExceptCaseVide est une classe qui va permettre de g�rer le cas o� la matrice
 * n'est pas correctement remplie
 */
public class ExceptCaseVide extends Exception {
	
	/**
	 * Cl� de hachage SHA qui identifie de mani�re unique ExceptCaseVide
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Exception qui va �tre lev�e quand la matrice n'est pas enti�rement remplie
	 */
	public ExceptCaseVide() {
	}
}