package Modele;

/**
 * ExceptEntreFraction est une classe qui va permettre de g�rer le cas o� le
 * matrice contient des divisions par z�ro
 */
public class ExceptZeroDivision extends Exception {

	/**
	 * Cl� de hachage SHA qui identifie de mani�re unique ExceptZeroDivision
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Exception qui va �tre lev�e lorsqu'on divise un nombre par z�ro
	 */
	public ExceptZeroDivision() {
	}
}