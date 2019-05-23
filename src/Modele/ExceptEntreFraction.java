package Modele;

/**
 * ExceptEntreFraction est une classe qui va permettre de gérer le cas où la
 * matrice contient des caractères non admis.
 */
public class ExceptEntreFraction extends Exception {

	/**
	 * Clé de hachage SHA qui identifie de manière unique ExceptEntreFraction
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Exception qui va être levée quand la matrice est remplie avec des lettres ou
	 * des caractères spéciaux
	 */
	public ExceptEntreFraction() {
	}
}