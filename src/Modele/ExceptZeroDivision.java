package Modele;

/**
 * ExceptEntreFraction est une classe qui va permettre de gérer le cas où le
 * matrice contient des divisions par zéro
 */
public class ExceptZeroDivision extends Exception {

	/**
	 * Clé de hachage SHA qui identifie de manière unique ExceptZeroDivision
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Exception qui va être levée lorsqu'on divise un nombre par zéro
	 */
	public ExceptZeroDivision() {
	}
}