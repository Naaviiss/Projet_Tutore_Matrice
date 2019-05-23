package Modele;

/**
 * ExceptEntreFraction est une classe qui va permettre de gérer le cas où le
 * signe "-" est mal placé
 */
public class ExceptNegatifMalPlace extends Exception {

	/**
	 * Clé de hachage SHA qui identifie de manière unique ExceptNegatifMalPlace
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Exception qui va être levée quand le signe "-" est mal placé dans la fraction
	 */
	public ExceptNegatifMalPlace() {
	}
}