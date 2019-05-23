package Modele;

/**
 * ExceptEntreFraction est une classe qui va permettre de g�rer le cas o� la
 * matrice contient des caract�res non admis.
 */
public class ExceptEntreFraction extends Exception {

	/**
	 * Cl� de hachage SHA qui identifie de mani�re unique ExceptEntreFraction
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Exception qui va �tre lev�e quand la matrice est remplie avec des lettres ou
	 * des caract�res sp�ciaux
	 */
	public ExceptEntreFraction() {
	}
}